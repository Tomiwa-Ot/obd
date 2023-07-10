package com.grephq.ot.obd;

/**
 * Asynchronous OBD callback interface
 *
 * @param <T> callback return type
 */
public interface AsyncCallback<T> {

    /**
     * Asynchronous OBD callback method
     *
     * @param params function parameters
     * @return T
     */
    public T run(Object... params);

}
