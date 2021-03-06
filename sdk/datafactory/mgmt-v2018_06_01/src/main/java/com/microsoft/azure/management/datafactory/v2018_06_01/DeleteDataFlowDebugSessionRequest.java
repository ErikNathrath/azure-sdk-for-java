/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.datafactory.v2018_06_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body structure for deleting data flow debug session.
 */
public class DeleteDataFlowDebugSessionRequest {
    /**
     * The ID of data flow debug session.
     */
    @JsonProperty(value = "sessionId")
    private String sessionId;

    /**
     * Get the ID of data flow debug session.
     *
     * @return the sessionId value
     */
    public String sessionId() {
        return this.sessionId;
    }

    /**
     * Set the ID of data flow debug session.
     *
     * @param sessionId the sessionId value to set
     * @return the DeleteDataFlowDebugSessionRequest object itself.
     */
    public DeleteDataFlowDebugSessionRequest withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

}
