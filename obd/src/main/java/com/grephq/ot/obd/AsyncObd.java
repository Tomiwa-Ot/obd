package com.grephq.ot.obd;

import java.io.IOException;
import java.util.Map;

/**
 * Send OBD commands asynchronously
 */
public class AsyncObd implements Runnable {

    /**
     * OBD connection instance
     */
    private Obd obd;

    /**
     * List of commands to register
     */
    private Map<String, AsyncCommand> commands;

    /**
     * Enable/disable headers in OBD response
     */
    private boolean showHeaders;

    /**
     * Start/stop execution
     */
    private boolean execute = true;

    /**
     * Class constructor
     *
     * @param obd OBD connection instance
     * @param commands commands to register
     * @param showHeaders enable/disable headers in OBD response
     * @throws Exception if commands is null or empty
     */
    public AsyncObd(Obd obd, Map<String, AsyncCommand> commands, boolean showHeaders) throws Exception {
        // Check if commands is null or empty
        if(commands == null || commands.size() == 0)
            throw new Exception("AsyncCommands cannot be null or empty");

        this.obd = obd;
        this.commands = commands;
        this.showHeaders = showHeaders;
    }

    /**
     * Terminate execution
     */
    public void stopExecuting() {
        execute = false;
    }


    @Override
    public void run() {
        try {
            obd.enableHeaders(showHeaders);

            while(execute) {
                for(AsyncCommand command : commands.values()) {
                    String response = obd.sendCommand(command.getCommand());
                    command.setResponse(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
