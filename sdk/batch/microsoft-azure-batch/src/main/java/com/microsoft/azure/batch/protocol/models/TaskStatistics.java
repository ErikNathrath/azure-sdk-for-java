/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import org.joda.time.DateTime;
import org.joda.time.Period;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource usage statistics for a Task.
 */
public class TaskStatistics {
    /**
     * The URL of the statistics.
     */
    @JsonProperty(value = "url", required = true)
    private String url;

    /**
     * The start time of the time range covered by the statistics.
     */
    @JsonProperty(value = "startTime", required = true)
    private DateTime startTime;

    /**
     * The time at which the statistics were last updated. All statistics are
     * limited to the range between startTime and lastUpdateTime.
     */
    @JsonProperty(value = "lastUpdateTime", required = true)
    private DateTime lastUpdateTime;

    /**
     * The total user mode CPU time (summed across all cores and all Compute
     * Nodes) consumed by the Task.
     */
    @JsonProperty(value = "userCPUTime", required = true)
    private Period userCPUTime;

    /**
     * The total kernel mode CPU time (summed across all cores and all Compute
     * Nodes) consumed by the Task.
     */
    @JsonProperty(value = "kernelCPUTime", required = true)
    private Period kernelCPUTime;

    /**
     * The total wall clock time of the Task.
     * The wall clock time is the elapsed time from when the Task started
     * running on a Compute Node to when it finished (or to the last time the
     * statistics were updated, if the Task had not finished by then). If the
     * Task was retried, this includes the wall clock time of all the Task
     * retries.
     */
    @JsonProperty(value = "wallClockTime", required = true)
    private Period wallClockTime;

    /**
     * The total number of disk read operations made by the Task.
     */
    @JsonProperty(value = "readIOps", required = true)
    private long readIOps;

    /**
     * The total number of disk write operations made by the Task.
     */
    @JsonProperty(value = "writeIOps", required = true)
    private long writeIOps;

    /**
     * The total gibibytes read from disk by the Task.
     */
    @JsonProperty(value = "readIOGiB", required = true)
    private double readIOGiB;

    /**
     * The total gibibytes written to disk by the Task.
     */
    @JsonProperty(value = "writeIOGiB", required = true)
    private double writeIOGiB;

    /**
     * The total wait time of the Task. The wait time for a Task is defined as
     * the elapsed time between the creation of the Task and the start of Task
     * execution. (If the Task is retried due to failures, the wait time is the
     * time to the most recent Task execution.).
     */
    @JsonProperty(value = "waitTime", required = true)
    private Period waitTime;

    /**
     * Get the url value.
     *
     * @return the url value
     */
    public String url() {
        return this.url;
    }

    /**
     * Set the url value.
     *
     * @param url the url value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the startTime value.
     *
     * @return the startTime value
     */
    public DateTime startTime() {
        return this.startTime;
    }

    /**
     * Set the startTime value.
     *
     * @param startTime the startTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withStartTime(DateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get the lastUpdateTime value.
     *
     * @return the lastUpdateTime value
     */
    public DateTime lastUpdateTime() {
        return this.lastUpdateTime;
    }

    /**
     * Set the lastUpdateTime value.
     *
     * @param lastUpdateTime the lastUpdateTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withLastUpdateTime(DateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    /**
     * Get the userCPUTime value.
     *
     * @return the userCPUTime value
     */
    public Period userCPUTime() {
        return this.userCPUTime;
    }

    /**
     * Set the userCPUTime value.
     *
     * @param userCPUTime the userCPUTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withUserCPUTime(Period userCPUTime) {
        this.userCPUTime = userCPUTime;
        return this;
    }

    /**
     * Get the kernelCPUTime value.
     *
     * @return the kernelCPUTime value
     */
    public Period kernelCPUTime() {
        return this.kernelCPUTime;
    }

    /**
     * Set the kernelCPUTime value.
     *
     * @param kernelCPUTime the kernelCPUTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withKernelCPUTime(Period kernelCPUTime) {
        this.kernelCPUTime = kernelCPUTime;
        return this;
    }

    /**
     * Get the wall clock time is the elapsed time from when the Task started running on a Compute Node to when it finished (or to the last time the statistics were updated, if the Task had not finished by then). If the Task was retried, this includes the wall clock time of all the Task retries.
     *
     * @return the wallClockTime value
     */
    public Period wallClockTime() {
        return this.wallClockTime;
    }

    /**
     * Set the wall clock time is the elapsed time from when the Task started running on a Compute Node to when it finished (or to the last time the statistics were updated, if the Task had not finished by then). If the Task was retried, this includes the wall clock time of all the Task retries.
     *
     * @param wallClockTime the wallClockTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withWallClockTime(Period wallClockTime) {
        this.wallClockTime = wallClockTime;
        return this;
    }

    /**
     * Get the readIOps value.
     *
     * @return the readIOps value
     */
    public long readIOps() {
        return this.readIOps;
    }

    /**
     * Set the readIOps value.
     *
     * @param readIOps the readIOps value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withReadIOps(long readIOps) {
        this.readIOps = readIOps;
        return this;
    }

    /**
     * Get the writeIOps value.
     *
     * @return the writeIOps value
     */
    public long writeIOps() {
        return this.writeIOps;
    }

    /**
     * Set the writeIOps value.
     *
     * @param writeIOps the writeIOps value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withWriteIOps(long writeIOps) {
        this.writeIOps = writeIOps;
        return this;
    }

    /**
     * Get the readIOGiB value.
     *
     * @return the readIOGiB value
     */
    public double readIOGiB() {
        return this.readIOGiB;
    }

    /**
     * Set the readIOGiB value.
     *
     * @param readIOGiB the readIOGiB value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withReadIOGiB(double readIOGiB) {
        this.readIOGiB = readIOGiB;
        return this;
    }

    /**
     * Get the writeIOGiB value.
     *
     * @return the writeIOGiB value
     */
    public double writeIOGiB() {
        return this.writeIOGiB;
    }

    /**
     * Set the writeIOGiB value.
     *
     * @param writeIOGiB the writeIOGiB value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withWriteIOGiB(double writeIOGiB) {
        this.writeIOGiB = writeIOGiB;
        return this;
    }

    /**
     * Get the waitTime value.
     *
     * @return the waitTime value
     */
    public Period waitTime() {
        return this.waitTime;
    }

    /**
     * Set the waitTime value.
     *
     * @param waitTime the waitTime value to set
     * @return the TaskStatistics object itself.
     */
    public TaskStatistics withWaitTime(Period waitTime) {
        this.waitTime = waitTime;
        return this;
    }

}
