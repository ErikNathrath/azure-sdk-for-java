/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql.v2014_04_01.implementation;

import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.management.sql.v2014_04_01.ServerConnectionType;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in ServerConnectionPolicies.
 */
public class ServerConnectionPoliciesInner {
    /** The Retrofit service to perform REST calls. */
    private ServerConnectionPoliciesService service;
    /** The service client containing this operation class. */
    private SqlManagementClientImpl client;

    /**
     * Initializes an instance of ServerConnectionPoliciesInner.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public ServerConnectionPoliciesInner(Retrofit retrofit, SqlManagementClientImpl client) {
        this.service = retrofit.create(ServerConnectionPoliciesService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for ServerConnectionPolicies to be
     * used by Retrofit to perform actually REST calls.
     */
    interface ServerConnectionPoliciesService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.sql.v2014_04_01.ServerConnectionPolicies createOrUpdate" })
        @PUT("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/connectionPolicies/{connectionPolicyName}")
        Observable<Response<ResponseBody>> createOrUpdate(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("connectionPolicyName") String connectionPolicyName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Body ServerConnectionPolicyInner parameters, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.sql.v2014_04_01.ServerConnectionPolicies get" })
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/connectionPolicies/{connectionPolicyName}")
        Observable<Response<ResponseBody>> get(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("connectionPolicyName") String connectionPolicyName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * Creates or updates the server's connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @param connectionType The server connection type. Possible values include: 'Default', 'Proxy', 'Redirect'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the ServerConnectionPolicyInner object if successful.
     */
    public ServerConnectionPolicyInner createOrUpdate(String resourceGroupName, String serverName, ServerConnectionType connectionType) {
        return createOrUpdateWithServiceResponseAsync(resourceGroupName, serverName, connectionType).toBlocking().single().body();
    }

    /**
     * Creates or updates the server's connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @param connectionType The server connection type. Possible values include: 'Default', 'Proxy', 'Redirect'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<ServerConnectionPolicyInner> createOrUpdateAsync(String resourceGroupName, String serverName, ServerConnectionType connectionType, final ServiceCallback<ServerConnectionPolicyInner> serviceCallback) {
        return ServiceFuture.fromResponse(createOrUpdateWithServiceResponseAsync(resourceGroupName, serverName, connectionType), serviceCallback);
    }

    /**
     * Creates or updates the server's connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @param connectionType The server connection type. Possible values include: 'Default', 'Proxy', 'Redirect'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ServerConnectionPolicyInner object
     */
    public Observable<ServerConnectionPolicyInner> createOrUpdateAsync(String resourceGroupName, String serverName, ServerConnectionType connectionType) {
        return createOrUpdateWithServiceResponseAsync(resourceGroupName, serverName, connectionType).map(new Func1<ServiceResponse<ServerConnectionPolicyInner>, ServerConnectionPolicyInner>() {
            @Override
            public ServerConnectionPolicyInner call(ServiceResponse<ServerConnectionPolicyInner> response) {
                return response.body();
            }
        });
    }

    /**
     * Creates or updates the server's connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @param connectionType The server connection type. Possible values include: 'Default', 'Proxy', 'Redirect'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ServerConnectionPolicyInner object
     */
    public Observable<ServiceResponse<ServerConnectionPolicyInner>> createOrUpdateWithServiceResponseAsync(String resourceGroupName, String serverName, ServerConnectionType connectionType) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (this.client.apiVersion() == null) {
            throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
        }
        if (connectionType == null) {
            throw new IllegalArgumentException("Parameter connectionType is required and cannot be null.");
        }
        final String connectionPolicyName = "default";
        ServerConnectionPolicyInner parameters = new ServerConnectionPolicyInner();
        parameters.withConnectionType(connectionType);
        return service.createOrUpdate(this.client.subscriptionId(), resourceGroupName, serverName, connectionPolicyName, this.client.apiVersion(), this.client.acceptLanguage(), parameters, this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ServerConnectionPolicyInner>>>() {
                @Override
                public Observable<ServiceResponse<ServerConnectionPolicyInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<ServerConnectionPolicyInner> clientResponse = createOrUpdateDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<ServerConnectionPolicyInner> createOrUpdateDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<ServerConnectionPolicyInner, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ServerConnectionPolicyInner>() { }.getType())
                .register(201, new TypeToken<ServerConnectionPolicyInner>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Gets the server's secure connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the ServerConnectionPolicyInner object if successful.
     */
    public ServerConnectionPolicyInner get(String resourceGroupName, String serverName) {
        return getWithServiceResponseAsync(resourceGroupName, serverName).toBlocking().single().body();
    }

    /**
     * Gets the server's secure connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<ServerConnectionPolicyInner> getAsync(String resourceGroupName, String serverName, final ServiceCallback<ServerConnectionPolicyInner> serviceCallback) {
        return ServiceFuture.fromResponse(getWithServiceResponseAsync(resourceGroupName, serverName), serviceCallback);
    }

    /**
     * Gets the server's secure connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ServerConnectionPolicyInner object
     */
    public Observable<ServerConnectionPolicyInner> getAsync(String resourceGroupName, String serverName) {
        return getWithServiceResponseAsync(resourceGroupName, serverName).map(new Func1<ServiceResponse<ServerConnectionPolicyInner>, ServerConnectionPolicyInner>() {
            @Override
            public ServerConnectionPolicyInner call(ServiceResponse<ServerConnectionPolicyInner> response) {
                return response.body();
            }
        });
    }

    /**
     * Gets the server's secure connection policy.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value from the Azure Resource Manager API or the portal.
     * @param serverName The name of the server.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ServerConnectionPolicyInner object
     */
    public Observable<ServiceResponse<ServerConnectionPolicyInner>> getWithServiceResponseAsync(String resourceGroupName, String serverName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (this.client.apiVersion() == null) {
            throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
        }
        final String connectionPolicyName = "default";
        return service.get(this.client.subscriptionId(), resourceGroupName, serverName, connectionPolicyName, this.client.apiVersion(), this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ServerConnectionPolicyInner>>>() {
                @Override
                public Observable<ServiceResponse<ServerConnectionPolicyInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<ServerConnectionPolicyInner> clientResponse = getDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<ServerConnectionPolicyInner> getDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<ServerConnectionPolicyInner, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ServerConnectionPolicyInner>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

}
