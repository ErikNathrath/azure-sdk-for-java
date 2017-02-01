/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.servicebus.primitives;

import java.time.*;
import java.util.UUID;

import org.apache.qpid.proton.amqp.*;

import com.microsoft.azure.servicebus.amqp.AmqpConstants;

public final class ClientConstants
{
	private ClientConstants() { }
	
	public static final int LOCKTOKENSIZE = 16;
	public static final String ENQUEUEDTIMEUTCNAME = "x-opt-enqueued-time";
	public static final String SCHEDULEDENQUEUETIMENAME = "x-opt-scheduled-enqueue-time";
	public static final String SEQUENCENUBMERNAME = "x-opt-sequence-number";
	//public static final String LOCKTOKENNAME = "x-opt-lock-token";
	public static final String LOCKEDUNTILNAME = "x-opt-locked-until";
	public static final String PARTITIONKEYNAME = "x-opt-partition-key";
	public static final String DEADLETTERSOURCENAME = "x-opt-deadletter-source";
	public static final UUID ZEROLOCKTOKEN = new UUID(0l, 0l);	

	public final static int AMQPS_PORT = 5671;
	public final static int MAX_PARTITION_KEY_LENGTH = 128;

	public final static Symbol SERVER_BUSY_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":server-busy");
	public final static Symbol ARGUMENT_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":argument-error");
	public final static Symbol ARGUMENT_OUT_OF_RANGE_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":argument-out-of-range");
	public final static Symbol ENTITY_DISABLED_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":entity-disabled");
	public final static Symbol PARTITION_NOT_OWNED_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":partition-not-owned");
	public final static Symbol STORE_LOCK_LOST_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":store-lock-lost");
	public final static Symbol PUBLISHER_REVOKED_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":publisher-revoked");
	public final static Symbol TIMEOUT_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":timeout");
	public final static Symbol TRACKING_ID_PROPERTY = Symbol.getSymbol(AmqpConstants.VENDOR + ":tracking-id");
	public static final Symbol DEADLETTERNAME = Symbol.valueOf(AmqpConstants.VENDOR + ":dead-letter");
    public static final Symbol MESSAGE_LOCK_LOST_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":message-lock-lost");
    public static final Symbol SESSION_LOCK_LOST_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":session-lock-lost");
    public static final Symbol SESSIONS_CANNOT_BE_LOCKED_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":session-cannot-be-locked");
    public static final Symbol MESSAGE_NOT_FOUND_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":message-not-found");
    public static final Symbol SESSION_NOT_FOUND_ERROR = Symbol.getSymbol(AmqpConstants.VENDOR + ":session-not-found");
	
	public static final String DEADLETTER_REASON_HEADER = "DeadLetterReason";
    public static final String DEADLETTER_ERROR_DESCRIPTION_HEADER = "DeadLetterErrorDescription";

	public static final int MAX_MESSAGE_LENGTH_BYTES = 256 * 1024;
	public static final int MAX_FRAME_SIZE_BYTES = 64 * 1024;
	public static final int MAX_EVENTHUB_AMQP_HEADER_SIZE_BYTES = 512;

	public final static Duration TIMER_TOLERANCE = Duration.ofSeconds(1);

	public final static Duration DEFAULT_RERTRY_MIN_BACKOFF = Duration.ofSeconds(0);
	public final static Duration DEFAULT_RERTRY_MAX_BACKOFF = Duration.ofSeconds(30);

	public final static int DEFAULT_MAX_RETRY_COUNT = 10;

	public final static String SERVICEBUS_CLIENT_TRACE = "servicebus.trace";

	public final static boolean DEFAULT_IS_TRANSIENT = true;

	public final static int REACTOR_IO_POLL_TIMEOUT = 20;
	public final static int SERVER_BUSY_BASE_SLEEP_TIME_IN_SECS = 4;

	public final static String NO_RETRY = "NoRetry";
	public final static String DEFAULT_RETRY = "Default";
	
	public static final String REQUEST_RESPONSE_OPERATION_NAME = "operation";
	public static final String REQUEST_RESPONSE_TIMEOUT = AmqpConstants.VENDOR + ":server-timeout";
	public static final String REQUEST_RESPONSE_RENEWLOCK_OPERATION = AmqpConstants.VENDOR + ":renew-lock";
	public static final String SCHEDULE_MESSAGE_OPERATION = AmqpConstants.VENDOR + ":schedule-message";
    public static final String CANCEL_CHEDULE_MESSAGE_OPERATION = AmqpConstants.VENDOR + ":cancel-scheduled-message";
	public static final String REQUEST_RESPONSE_LOCKTOKENS = "lock-tokens";
	public static final String REQUEST_RESPONSE_EXPIRATIONS = "expirations";
	public static final String REQUEST_RESPONSE_SESSIONID = "session-id";
	public static final String REQUEST_RESPONSE_SEQUENCE_NUMBERS = "sequence-numbers";
	public static final String REQUEST_RESPONSE_MESSAGES = "messages";
	public static final String REQUEST_RESPONSE_MESSAGE = "message";
	public static final String REQUEST_RESPONSE_MESSAGE_ID = "message-id";
	public static final String REQUEST_RESPONSE_SESSION_ID = "session-id";
	public static final String REQUEST_RESPONSE_PARTITION_KEY = "partition-key";
	public static final String REQUEST_RESPONSE_STATUS_CODE = "statusCode";
    public static final String REQUEST_RESPONSE_STATUS_DESCRIPTION = "statusDescription";
    public static final String REQUEST_RESPONSE_ERROR_CONDITION = "errorCondition";
    
    public static final int REQUEST_RESPONSE_OK_STATUS_CODE = 200;
    public static final int REQUEST_RESPONSE_UNDEFINED_STATUS_CODE = -1;
    
	
}
