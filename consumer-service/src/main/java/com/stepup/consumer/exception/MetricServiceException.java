package com.stepup.consumer.exception;

/**
 * Custom runtime exception specific to the MetricService.
 *
 * @see java.lang.RuntimeException
 */
public class MetricServiceException extends RuntimeException{

    /**
     * Constructs a new MetricServiceException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public MetricServiceException(String message) {
        super(message);
    }
}
