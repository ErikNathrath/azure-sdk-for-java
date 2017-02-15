package com.microsoft.azure.servicebus;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.microsoft.azure.servicebus.BrokeredMessage;
import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.servicebus.IBrokeredMessage;
import com.microsoft.azure.servicebus.IMessageReceiver;
import com.microsoft.azure.servicebus.IMessageSender;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.MessageNotFoundException;
import com.microsoft.azure.servicebus.primitives.MessagingFactory;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class QueueSendReceiveTests {
	private ConnectionStringBuilder builder;
	private MessagingFactory factory;
	private IMessageSender sender;
	private IMessageReceiver receiver;
	private IMessageBrowser browser;
	
	@Before // Fix this. something goes wrong when we do this setup.
	public void setup() throws IOException, InterruptedException, ExecutionException, ServiceBusException
	{
		this.builder = TestUtils.getConnectionStringBuilder();
		this.factory = MessagingFactory.createFromConnectionStringBuilder(builder);
		this.sender = ClientFactory.createMessageSenderFromConnectionStringBuilder(builder);		
		
		this.drainAllMessages(builder);
		//Thread.sleep(60000);
	}
	
	@After
	public void tearDown() throws ServiceBusException
	{
		this.sender.close();
		if(this.receiver != null)
			this.receiver.close();
		if(this.browser != null)
			this.browser.close();
		this.factory.close();
	}
	
	@Test
	public void testBasicQueueSend() throws InterruptedException, ServiceBusException, IOException
	{		
		this.sender.send(new BrokeredMessage("AMQP message"));
	}
	
	@Test
	public void testBasicQueueSendBatch() throws InterruptedException, ServiceBusException, IOException
	{		
		List<BrokeredMessage> messages = new ArrayList<BrokeredMessage>();
		for(int i=0; i<10; i++)
		{
			messages.add(new BrokeredMessage("AMQP message"));
		}
		this.sender.sendBatch(messages);
	}
	
	@Test
	public void testBasicQueueReceiveAndDelete() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{	
		String messageId = UUID.randomUUID().toString();
		BrokeredMessage message = new BrokeredMessage("AMQP message");
		message.setMessageId(messageId);
		this.sender.send(message);		
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.ReceiveAndDelete);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("Message Id did not match", messageId, receivedMessage.getMessageId());
		receivedMessage = this.receiver.receive();
		Assert.assertNull("Message received again", receivedMessage);
	}
	
	@Test
	public void testBasicQueueReceiveBatchAndDelete() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{
		int numMessages = 10;		
		List<BrokeredMessage> messages = new ArrayList<BrokeredMessage>();
		for(int i=0; i<numMessages; i++)
		{
			messages.add(new BrokeredMessage("AMQP message"));
		}
		this.sender.sendBatch(messages);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.ReceiveAndDelete);
		int totalReceivedMessages = 0;
		Collection<IBrokeredMessage> receivedMessages = this.receiver.receiveBatch(numMessages);
		while(receivedMessages != null && receivedMessages.size() > 0)
		{
			totalReceivedMessages += receivedMessages.size();
			receivedMessages = this.receiver.receiveBatch(numMessages);
		}
		
		Assert.assertEquals("All messages not received", numMessages, totalReceivedMessages);
		receivedMessages = this.receiver.receiveBatch(numMessages);
		Assert.assertNull("Messages received again", receivedMessages);
	}
	
	@Test
	public void testBasicQueueReceiveAndComplete() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{		
		String messageId = UUID.randomUUID().toString();
		BrokeredMessage message = new BrokeredMessage("AMQP message");
		message.setMessageId(messageId);
		this.sender.send(message);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("Message Id did not match", messageId, receivedMessage.getMessageId());
		this.receiver.complete(receivedMessage.getLockToken());
		receivedMessage = this.receiver.receive();
		Assert.assertNull("Message was not properly completed", receivedMessage);
	}
	
	@Test
	public void testBasicQueueReceiveAndAbandon() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{		
		String messageId = UUID.randomUUID().toString();
		BrokeredMessage message = new BrokeredMessage("AMQP message");
		message.setMessageId(messageId);
		this.sender.send(message);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("Message Id did not match", messageId, receivedMessage.getMessageId());
		long deliveryCount = receivedMessage.getDeliveryCount();		
		this.receiver.abandon(receivedMessage.getLockToken());
		receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("DeliveryCount not incremented", deliveryCount+1, receivedMessage.getDeliveryCount());
		this.receiver.complete(receivedMessage.getLockToken());
	}
	
	@Test
	public void testBasicQueueReceiveAndDeadLetter() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{		
		String messageId = UUID.randomUUID().toString();
		BrokeredMessage message = new BrokeredMessage("AMQP message");
		message.setMessageId(messageId);
		this.sender.send(message);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("Message Id did not match", messageId, receivedMessage.getMessageId());
		String deadLetterReason = "java client deadletter test";
		this.receiver.deadLetter(receivedMessage.getLockToken(), deadLetterReason, null);
		receivedMessage = this.receiver.receive();
		Assert.assertNull("Message was not properly deadlettered", receivedMessage);
	}
	
	@Test
	public void testBasicQueueReceiveAndRenewLock() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{		
		String messageId = UUID.randomUUID().toString();
		BrokeredMessage message = new BrokeredMessage("AMQP message");
		message.setMessageId(messageId);
		this.sender.send(message);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		Assert.assertNotNull("Message not received", receivedMessage);
		Assert.assertEquals("Message Id did not match", messageId, receivedMessage.getMessageId());
		Instant oldLockedUntilTime = receivedMessage.getLockedUntilUtc();
		Thread.sleep(1000);
		Instant newLockedUntilUtc = this.receiver.renewMessageLock(receivedMessage);
		Assert.assertTrue("Lock not renewed. OldLockedUntilUtc:" + oldLockedUntilTime.toString() + ", newLockedUntilUtc:" + newLockedUntilUtc, newLockedUntilUtc.isAfter(oldLockedUntilTime));
		Assert.assertEquals("Renewed lockeduntil time not set in Message", newLockedUntilUtc, receivedMessage.getLockedUntilUtc());
		this.receiver.complete(receivedMessage.getLockToken());
	}
	
	@Test
	public void testBasicQueueReceiveAndRenewLockBatch() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{		
		int numMessages = 10;
		List<BrokeredMessage> messages = new ArrayList<BrokeredMessage>();
		for(int i=0; i<numMessages; i++)
		{
			messages.add(new BrokeredMessage("AMQP message"));
		}
		this.sender.sendBatch(messages);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		ArrayList<IBrokeredMessage> totalReceivedMessages = new ArrayList<>();		
		
		Collection<IBrokeredMessage> receivedMessages = this.receiver.receiveBatch(numMessages);
		totalReceivedMessages.addAll(receivedMessages);		
		while(receivedMessages != null && receivedMessages.size() > 0 && totalReceivedMessages.size() < numMessages)
		{						
			receivedMessages = this.receiver.receiveBatch(numMessages);
			totalReceivedMessages.addAll(receivedMessages);	
		}
		Assert.assertEquals("All messages not received", numMessages, totalReceivedMessages.size());	
		
		ArrayList<Instant> oldLockTimes = new ArrayList<Instant>();
		for(IBrokeredMessage message : totalReceivedMessages)
		{
			oldLockTimes.add(message.getLockedUntilUtc());
		}
		
		Thread.sleep(1000);
		Collection<Instant> newLockTimes = ((BrokeredMessageReceiver)this.receiver).renewMessageLockBatch(totalReceivedMessages);
		Assert.assertEquals("RenewLock didn't return one instant per message in the collection", totalReceivedMessages.size(), newLockTimes.size());
		Iterator<Instant> newLockTimeIterator = newLockTimes.iterator();
		Iterator<Instant> oldLockTimeIterator = oldLockTimes.iterator();
		for(IBrokeredMessage message : totalReceivedMessages)
		{	
			Instant oldLockTime = oldLockTimeIterator.next();
			Instant newLockTime = newLockTimeIterator.next();
			Assert.assertTrue("Lock not renewed. OldLockedUntilUtc:" + oldLockTime.toString() + ", newLockedUntilUtc:" + newLockTime.toString(), newLockTime.isAfter(oldLockTime));
			Assert.assertEquals("Renewed lockeduntil time not set in Message", newLockTime, message.getLockedUntilUtc());
			this.receiver.complete(message.getLockToken());			
		}		
	}
	
	@Test
	public void testBasicQueueReceiveBatchAndComplete() throws InterruptedException, ServiceBusException, IOException, ExecutionException
	{
		int numMessages = 10;		
		List<BrokeredMessage> messages = new ArrayList<BrokeredMessage>();
		for(int i=0; i<numMessages; i++)
		{
			messages.add(new BrokeredMessage("AMQP message"));
		}
		this.sender.sendBatch(messages);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, builder.getEntityPath(), ReceiveMode.PeekLock);
		int totalMessagesReceived = 0;
		Collection<IBrokeredMessage> receivedMessages = this.receiver.receiveBatch(numMessages);
		while(receivedMessages != null && receivedMessages.size() > 0)
		{
			totalMessagesReceived += receivedMessages.size();
			for(IBrokeredMessage message : receivedMessages)
			{
				//System.out.println(message.getLockToken());
				this.receiver.complete(message.getLockToken());
			}
			receivedMessages = this.receiver.receiveBatch(numMessages);
		}
		Assert.assertEquals("All messages not received", numMessages, totalMessagesReceived);		
		
		receivedMessages = this.receiver.receiveBatch(numMessages);
		Assert.assertNull("Messages received again", receivedMessages);
	}
	
	@Test
	public void testSendSceduledMessageAndReceive() throws InterruptedException, ServiceBusException, IOException
	{
		int secondsToWaitBeforeScheduling = 30;
		String msgId1 = UUID.randomUUID().toString();
		String msgId2 = UUID.randomUUID().toString();
		BrokeredMessage message1 = new BrokeredMessage("AMQP Scheduled message");
		message1.setMessageId(msgId1);
		BrokeredMessage message2 = new BrokeredMessage("AMQP Scheduled message2");
		message2.setMessageId(msgId2);
		
		this.sender.scheduleMessage(message1, Instant.now().plusSeconds(secondsToWaitBeforeScheduling));
		this.sender.scheduleMessage(message2, Instant.now().plusSeconds(secondsToWaitBeforeScheduling));
		Thread.sleep(secondsToWaitBeforeScheduling * 1000);
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.ReceiveAndDelete);
		Collection<IBrokeredMessage> allReceivedMessages = new LinkedList<IBrokeredMessage>();
		Collection<IBrokeredMessage> receivedMessages = this.receiver.receiveBatch(10);
				
		while(receivedMessages != null && receivedMessages.size() > 0)
		{
			allReceivedMessages.addAll(receivedMessages);
			receivedMessages = this.receiver.receiveBatch(10);
		}
		
		boolean firstMessageReceived = false;
		boolean secondMessageReceived = false;
		for(IBrokeredMessage message : allReceivedMessages)
		{
			if(message.getMessageId().equals(msgId1))
				firstMessageReceived = true;
			else if(message.getMessageId().equals(msgId2))
				secondMessageReceived = true;
		}
		
		Assert.assertTrue("Scheduled messages not received", firstMessageReceived && secondMessageReceived);
	}
	
	@Test
	public void testSendSceduledMessageAndCancel() throws InterruptedException, ServiceBusException, IOException
	{
		int secondsToWaitBeforeScheduling = 30;
		String msgId1 = UUID.randomUUID().toString();
		String msgId2 = UUID.randomUUID().toString();
		BrokeredMessage message1 = new BrokeredMessage("AMQP Scheduled message");
		BrokeredMessage message2 = new BrokeredMessage("AMQP Scheduled message2");
		message1.setMessageId(msgId1);
		message2.setMessageId(msgId2);
		
		this.sender.scheduleMessage(message1, Instant.now().plusSeconds(secondsToWaitBeforeScheduling));
		long sequnceNumberMsg2 = this.sender.scheduleMessage(message2, Instant.now().plusSeconds(secondsToWaitBeforeScheduling));
		this.sender.cancelScheduledMessage(sequnceNumberMsg2);
		Thread.sleep(secondsToWaitBeforeScheduling * 1000);
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.ReceiveAndDelete);
		Collection<IBrokeredMessage> allReceivedMessages = new LinkedList<IBrokeredMessage>();
		Collection<IBrokeredMessage> receivedMessages = this.receiver.receiveBatch(10);
		while(receivedMessages != null && receivedMessages.size() > 0)
		{
			allReceivedMessages.addAll(receivedMessages);
			receivedMessages = this.receiver.receiveBatch(10);
		}
		
		Assert.assertTrue("Scheduled messages not received", allReceivedMessages.removeIf(msg -> msg.getMessageId().equals(msgId1)));
		Assert.assertFalse("Cancelled scheduled messages also received", allReceivedMessages.removeIf(msg -> msg.getMessageId().equals(msgId2)));
	}
	
	@Test
	public void testPeekMessage() throws InterruptedException, ServiceBusException, IOException
	{			
		this.sender.send(new BrokeredMessage("AMQP Scheduled message"));
		this.sender.send(new BrokeredMessage("AMQP Scheduled message2"));
		
		this.browser = ClientFactory.createMessageBrowserFromEntityPath(factory, this.builder.getEntityPath());
		IBrokeredMessage peekedMessage1 = this.browser.peek();
		long firstMessageSequenceNumber = peekedMessage1.getSequenceNumber();
		IBrokeredMessage peekedMessage2 = this.browser.peek();
		Assert.assertNotEquals("Peek returned the same message again.", firstMessageSequenceNumber, peekedMessage2.getSequenceNumber());		
		
		// Now peek with fromSequnceNumber.. May not work for partitioned entities
		IBrokeredMessage peekedMessage5 = this.browser.peek(firstMessageSequenceNumber);
		Assert.assertEquals("Peek with sequence number failed.", firstMessageSequenceNumber, peekedMessage5.getSequenceNumber());
	}
	
	@Test
	public void testPeekMessageBatch() throws InterruptedException, ServiceBusException, IOException
	{			
		this.sender.send(new BrokeredMessage("AMQP Scheduled message"));
		this.sender.send(new BrokeredMessage("AMQP Scheduled message2"));
		
		this.browser = ClientFactory.createMessageBrowserFromEntityPath(factory, this.builder.getEntityPath());
		Collection<IBrokeredMessage> peekedMessages = this.browser.peekBatch(10);
		Assert.assertEquals("PeekBatch didnot return all messages.", 2, peekedMessages.size());
		long firstMessageSequenceNumber = peekedMessages.iterator().next().getSequenceNumber();
		
		// Now peek with fromSequnceNumber.. May not work for partitioned entities
		Collection<IBrokeredMessage> peekedMessagesBatch2 = this.browser.peekBatch(firstMessageSequenceNumber, 10);
		Assert.assertEquals("PeekBatch with sequence number didnot return all messages.", 2, peekedMessagesBatch2.size());		
		Assert.assertEquals("PeekBatch with sequence number failed.", firstMessageSequenceNumber, peekedMessagesBatch2.iterator().next().getSequenceNumber());
	}
	
	@Test
	public void testReceiveBySequenceNumberAndComplete() throws InterruptedException, ServiceBusException, IOException
	{			
		this.sender.send(new BrokeredMessage("AMQP message"));
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		long sequenceNumber = receivedMessage.getSequenceNumber();
		String messageId = receivedMessage.getMessageId();
		this.receiver.defer(receivedMessage.getLockToken());		
		
		// Now receive by sequence number
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", sequenceNumber, receivedMessage.getSequenceNumber());
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", messageId, receivedMessage.getMessageId());		
		this.receiver.complete(receivedMessage.getLockToken());
		
		// Try to receive by sequence number again
		try
		{
			receivedMessage = this.receiver.receive(sequenceNumber);
			Assert.fail("Message recieved by sequnce number was not properly completed.");
		}
		catch(MessageNotFoundException e)
		{
			// Expected
		}		
	}
	
	@Test
	public void testReceiveBySequenceNumberAndAbandon() throws InterruptedException, ServiceBusException, IOException
	{			
		this.sender.send(new BrokeredMessage("AMQP message"));
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		long sequenceNumber = receivedMessage.getSequenceNumber();
		String messageId = receivedMessage.getMessageId();
		this.receiver.defer(receivedMessage.getLockToken());		
		
		// Now receive by sequence number
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", sequenceNumber, receivedMessage.getSequenceNumber());
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", messageId, receivedMessage.getMessageId());
		long deliveryCount = receivedMessage.getDeliveryCount();
		this.receiver.abandon(receivedMessage.getLockToken());
		
		// Try to receive by sequence number again
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("Abandon didn't increase the delivery count for the message received by sequence number.", deliveryCount + 1, receivedMessage.getDeliveryCount());
		this.receiver.complete(receivedMessage.getLockToken());		
	}
	
	@Test
	public void testReceiveBySequenceNumberAndDefer() throws InterruptedException, ServiceBusException, IOException
	{			
		BrokeredMessage sentMessage = new BrokeredMessage("AMQP message");
		HashMap customProperties = new HashMap();
		customProperties.put("phase", "before defer");
		sentMessage.setProperties(customProperties);
		this.sender.send(sentMessage);
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		long sequenceNumber = receivedMessage.getSequenceNumber();
		String messageId = receivedMessage.getMessageId();
		this.receiver.defer(receivedMessage.getLockToken());	
		
		// Now receive by sequence number
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", sequenceNumber, receivedMessage.getSequenceNumber());
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", messageId, receivedMessage.getMessageId());		
		customProperties.put("phase", "after defer");
		this.receiver.defer(receivedMessage.getLockToken(), customProperties);
		
		// Try to receive by sequence number again
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message after deferrring", sequenceNumber, receivedMessage.getSequenceNumber());
		Assert.assertEquals("Defer didn't update properties of the message received by sequence number", "after defer", receivedMessage.getProperties().get("phase"));
		this.receiver.complete(receivedMessage.getLockToken());		
	}
	
	@Test
	public void testReceiveBySequenceNumberAndDeadletter() throws InterruptedException, ServiceBusException, IOException
	{			
		this.sender.send(new BrokeredMessage("AMQP message"));
		
		this.receiver = ClientFactory.createMessageReceiverFromEntityPath(factory, this.builder.getEntityPath(), ReceiveMode.PeekLock);
		IBrokeredMessage receivedMessage = this.receiver.receive();
		long sequenceNumber = receivedMessage.getSequenceNumber();
		String messageId = receivedMessage.getMessageId();
		this.receiver.defer(receivedMessage.getLockToken());		
		
		// Now receive by sequence number
		receivedMessage = this.receiver.receive(sequenceNumber);
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", sequenceNumber, receivedMessage.getSequenceNumber());
		Assert.assertEquals("ReceiveBySequenceNumber didn't receive the right message.", messageId, receivedMessage.getMessageId());
		String deadLetterReason = "java client deadletter test";		
		this.receiver.deadLetter(receivedMessage.getLockToken(), deadLetterReason, null);
				
		// Try to receive by sequence number again
		try
		{
			receivedMessage = this.receiver.receive(sequenceNumber);
			Assert.fail("Message received by sequence number was not properly deadlettered");
		}
		catch(MessageNotFoundException e)
		{
			// Expected
		}
	}
	
	private void drainAllMessages(ConnectionStringBuilder builder) throws IOException, InterruptedException, ExecutionException, ServiceBusException
	{
		Duration waitTime = Duration.ofSeconds(5);
		final int batchSize = 10;		
		IMessageReceiver receiver = ClientFactory.createMessageReceiverFromEntityPath(this.factory, this.builder.getEntityPath(), ReceiveMode.ReceiveAndDelete);
		Collection<IBrokeredMessage> messages = receiver.receiveBatch(batchSize, waitTime);
		while(messages !=null && messages.size() > 0)
		{
			messages = receiver.receiveBatch(batchSize, waitTime);
		}
		
		receiver.close();
	}
	
	
	// Test send batch
	// Test send and expect timeout
	
	// receive with timeout
	// receive batch
	// receive batch with timeout
	// timed out receive should return null
	// Send message with Id, receive and verify Id
	// Send message with various properties like ttl.., receive it and verify them
	
}
