/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cosmosdb.v2019_08_01_preview.implementation;

import com.microsoft.azure.management.cosmosdb.v2019_08_01_preview.GremlinGraphGetResults;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;
import com.microsoft.azure.management.cosmosdb.v2019_08_01_preview.GremlinGraphCreateUpdateParameters;
import java.util.Map;
import com.microsoft.azure.management.cosmosdb.v2019_08_01_preview.GremlinGraphGetPropertiesResource;
import com.microsoft.azure.management.cosmosdb.v2019_08_01_preview.GremlinGraphResource;
import rx.functions.Func1;

class GremlinGraphGetResultsImpl extends CreatableUpdatableImpl<GremlinGraphGetResults, GremlinGraphGetResultsInner, GremlinGraphGetResultsImpl> implements GremlinGraphGetResults, GremlinGraphGetResults.Definition, GremlinGraphGetResults.Update {
    private final CosmosDBManager manager;
    private String resourceGroupName;
    private String accountName;
    private String databaseName;
    private String graphName;
    private GremlinGraphCreateUpdateParameters createOrUpdateParameter;

    GremlinGraphGetResultsImpl(String name, CosmosDBManager manager) {
        super(name, new GremlinGraphGetResultsInner());
        this.manager = manager;
        // Set resource name
        this.graphName = name;
        //
        this.createOrUpdateParameter = new GremlinGraphCreateUpdateParameters();
    }

    GremlinGraphGetResultsImpl(GremlinGraphGetResultsInner inner, CosmosDBManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.graphName = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.accountName = IdParsingUtils.getValueFromIdByName(inner.id(), "databaseAccounts");
        this.databaseName = IdParsingUtils.getValueFromIdByName(inner.id(), "gremlinDatabases");
        this.graphName = IdParsingUtils.getValueFromIdByName(inner.id(), "graphs");
        //
        this.createOrUpdateParameter = new GremlinGraphCreateUpdateParameters();
    }

    @Override
    public CosmosDBManager manager() {
        return this.manager;
    }

    @Override
    public Observable<GremlinGraphGetResults> createResourceAsync() {
        GremlinResourcesInner client = this.manager().inner().gremlinResources();
        return client.createUpdateGremlinGraphAsync(this.resourceGroupName, this.accountName, this.databaseName, this.graphName, this.createOrUpdateParameter)
            .map(new Func1<GremlinGraphGetResultsInner, GremlinGraphGetResultsInner>() {
               @Override
               public GremlinGraphGetResultsInner call(GremlinGraphGetResultsInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<GremlinGraphGetResults> updateResourceAsync() {
        GremlinResourcesInner client = this.manager().inner().gremlinResources();
        return client.createUpdateGremlinGraphAsync(this.resourceGroupName, this.accountName, this.databaseName, this.graphName, this.createOrUpdateParameter)
            .map(new Func1<GremlinGraphGetResultsInner, GremlinGraphGetResultsInner>() {
               @Override
               public GremlinGraphGetResultsInner call(GremlinGraphGetResultsInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<GremlinGraphGetResultsInner> getInnerAsync() {
        GremlinResourcesInner client = this.manager().inner().gremlinResources();
        return client.getGremlinGraphAsync(this.resourceGroupName, this.accountName, this.databaseName, this.graphName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.createOrUpdateParameter = new GremlinGraphCreateUpdateParameters();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String location() {
        return this.inner().location();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public GremlinGraphGetPropertiesResource resource() {
        return this.inner().resource();
    }

    @Override
    public Map<String, String> tags() {
        return this.inner().getTags();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public GremlinGraphGetResultsImpl withExistingGremlinDatabasis(String resourceGroupName, String accountName, String databaseName) {
        this.resourceGroupName = resourceGroupName;
        this.accountName = accountName;
        this.databaseName = databaseName;
        return this;
    }

    @Override
    public GremlinGraphGetResultsImpl withLocation(String location) {
        this.createOrUpdateParameter.withLocation(location);
        return this;
    }

    @Override
    public GremlinGraphGetResultsImpl withOptions(Map<String, String> options) {
        this.createOrUpdateParameter.withOptions(options);
        return this;
    }

    @Override
    public GremlinGraphGetResultsImpl withResource(GremlinGraphResource resource) {
        this.createOrUpdateParameter.withResource(resource);
        return this;
    }

    @Override
    public GremlinGraphGetResultsImpl withTags(Map<String, String> tags) {
        this.createOrUpdateParameter.withTags(tags);
        return this;
    }

}
