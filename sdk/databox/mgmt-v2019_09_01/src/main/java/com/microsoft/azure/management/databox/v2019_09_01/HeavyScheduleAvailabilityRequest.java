/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.databox.v2019_09_01;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Request body to get the availability for scheduling heavy orders.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "skuName", defaultImpl = HeavyScheduleAvailabilityRequest.class)
@JsonTypeName("DataBoxHeavy")
public class HeavyScheduleAvailabilityRequest extends ScheduleAvailabilityRequest {
}
