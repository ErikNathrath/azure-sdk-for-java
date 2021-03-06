/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.servicebus.v2015_08_01;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for Policykey.
 */
public enum Policykey {
    /** Enum value PrimaryKey. */
    PRIMARY_KEY("PrimaryKey"),

    /** Enum value SecondaryKey. */
    SECONDARY_KEY("SecondaryKey");

    /** The actual serialized value for a Policykey instance. */
    private String value;

    Policykey(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Policykey instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed Policykey object, or null if unable to parse.
     */
    @JsonCreator
    public static Policykey fromString(String value) {
        Policykey[] items = Policykey.values();
        for (Policykey item : items) {
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
