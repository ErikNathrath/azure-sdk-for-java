/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.automation.v2018_06_30;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.automation.v2018_06_30.implementation.AutomationManager;
import com.microsoft.azure.management.automation.v2018_06_30.implementation.TestJobInner;
import org.joda.time.DateTime;
import java.util.Map;

/**
 * Type representing TestJob.
 */
public interface TestJob extends HasInner<TestJobInner>, HasManager<AutomationManager> {
    /**
     * @return the creationTime value.
     */
    DateTime creationTime();

    /**
     * @return the endTime value.
     */
    DateTime endTime();

    /**
     * @return the exception value.
     */
    String exception();

    /**
     * @return the lastModifiedTime value.
     */
    DateTime lastModifiedTime();

    /**
     * @return the lastStatusModifiedTime value.
     */
    DateTime lastStatusModifiedTime();

    /**
     * @return the logActivityTrace value.
     */
    Integer logActivityTrace();

    /**
     * @return the parameters value.
     */
    Map<String, String> parameters();

    /**
     * @return the runOn value.
     */
    String runOn();

    /**
     * @return the startTime value.
     */
    DateTime startTime();

    /**
     * @return the status value.
     */
    String status();

    /**
     * @return the statusDetails value.
     */
    String statusDetails();

}
