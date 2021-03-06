/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cognitiveservices.v2017_04_18.implementation;

import com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl;
import com.microsoft.azure.management.cognitiveservices.v2017_04_18.CognitiveServicesAccount;
import rx.Observable;
import com.microsoft.azure.management.cognitiveservices.v2017_04_18.CognitiveServicesAccountProperties;
import com.microsoft.azure.management.cognitiveservices.v2017_04_18.Sku;

class CognitiveServicesAccountImpl extends GroupableResourceCoreImpl<CognitiveServicesAccount, CognitiveServicesAccountInner, CognitiveServicesAccountImpl, CognitiveServicesManager> implements CognitiveServicesAccount, CognitiveServicesAccount.Definition, CognitiveServicesAccount.Update {
    CognitiveServicesAccountImpl(String name, CognitiveServicesAccountInner inner, CognitiveServicesManager manager) {
        super(name, inner, manager);
    }

    @Override
    public Observable<CognitiveServicesAccount> createResourceAsync() {
        AccountsInner client = this.manager().inner().accounts();
        return client.createAsync(this.resourceGroupName(), this.name(), this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<CognitiveServicesAccount> updateResourceAsync() {
        AccountsInner client = this.manager().inner().accounts();
        return client.updateAsync(this.resourceGroupName(), this.name(), this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<CognitiveServicesAccountInner> getInnerAsync() {
        AccountsInner client = this.manager().inner().accounts();
        return client.getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public String etag() {
        return this.inner().etag();
    }

    @Override
    public String kind() {
        return this.inner().kind();
    }

    @Override
    public CognitiveServicesAccountProperties properties() {
        return this.inner().properties();
    }

    @Override
    public Sku sku() {
        return this.inner().sku();
    }

    @Override
    public CognitiveServicesAccountImpl withKind(String kind) {
        this.inner().withKind(kind);
        return this;
    }

    @Override
    public CognitiveServicesAccountImpl withProperties(CognitiveServicesAccountProperties properties) {
        this.inner().withProperties(properties);
        return this;
    }

    @Override
    public CognitiveServicesAccountImpl withSku(Sku sku) {
        this.inner().withSku(sku);
        return this;
    }

}
