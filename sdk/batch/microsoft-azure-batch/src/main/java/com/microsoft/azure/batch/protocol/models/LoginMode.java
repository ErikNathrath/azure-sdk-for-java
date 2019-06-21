// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator

package com.microsoft.azure.batch.protocol.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for LoginMode.
 */
public enum LoginMode {
    /** The LOGON32_LOGON_BATCH Win32 login mode. The batch login mode is recommended for long running parallel processes. */
    BATCH("batch"),

    /** The LOGON32_LOGON_INTERACTIVE Win32 login mode. UAC is enabled on Windows VirtualMachineConfiguration pools. If this option is used with an elevated user identity in a Windows VirtualMachineConfiguration pool, the user session will not be elevated unless the application executed by the task command line is configured to always require administrative privilege or to always require maximum privilege. */
    INTERACTIVE("interactive");

    /** The actual serialized value for a LoginMode instance. */
    private String value;

    LoginMode(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LoginMode instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed LoginMode object, or null if unable to parse.
     */
    @JsonCreator
    public static LoginMode fromString(String value) {
        LoginMode[] items = LoginMode.values();
        for (LoginMode item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}