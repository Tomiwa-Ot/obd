package com.grephq.ot.obd;

/**
 * Representation for OBD command sent asynchronously
 *
 * @param <T> Callback return type
 */
public class AsyncCommand<T> {

    /**
     * Command to be executed
     */
    private String command;

    /**
     * Callback to be executed
     */
    private AsyncCallback<T> callback;

    /**
     * Callback parameters
     */
    private Object[] callbackParams;

    /**
     * Callback output
     */
    private T callbackResult;

    /**
     * OBD command response
     */
    private String response;

    /**
     * AsyncCommand with no callback
     *
     * @param command OBD command
     * @throws Exception if command is null or empty
     */
    public AsyncCommand(String command) throws Exception {
        // Check if command is null or empty
        if(command == null || command.isEmpty())
            throw new Exception("Command cannot be null or empty");

        this.command = command;
        callback = null;
    }

    /**
     * AsyncCommand with callback
     *
     * @param command OBD command
     * @param callback callback to be executed upon receiving OBD response
     * @param callbackParams parameters for callback
     * @throws Exception if command is null or empty
     */
    public AsyncCommand(String command, AsyncCallback<T> callback, Object... callbackParams) throws Exception {
        // Check if command is null or empty
        if(command == null || command.isEmpty())
            throw new Exception("Command cannot be null or empty");

        this.command = command;
        this.callback = callback;
        this.callbackParams = callbackParams;
    }

    /**
     * Get OBD command to be sent asynchronously
     *
     * @return OBD command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Set OBD command response
     *
     * @param response OBD response
     */
    public synchronized void setResponse(String response) {
        this.response = response;

        // Execute registered callback
        if(!isCallbackNull())
            executeCallback();
    }

    /**
     * Get OBD command response
     *
     * @return OBD response
     */
    public synchronized String getResponse() {
        return response;
    }

    /**
     * Checks if callback is null
     *
     * @return true if callback is null, otherwise; false
     */
    public boolean isCallbackNull() {
        return callback == null;
    }

    /**
     * Get callback method result
     *
     * @return callback method result
     */
    public T getCallbackResult() {
        return callbackResult;
    }

    /**
     * Execute callback registered to asynchronous OBD call
     */
    private void executeCallback() {
        callbackResult = callback.run(callbackParams);
    }
}
