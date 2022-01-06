package flowcontext.core.exception;

import java.time.temporal.Temporal;

public class FlowContextSetupStepException extends BaseException {
    public FlowContextSetupStepException(String message, String type, Temporal datetime) {
        super(message, type, datetime);
    }
}