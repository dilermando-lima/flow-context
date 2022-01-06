package flowcontext.core.exception;

import java.time.temporal.Temporal;

public class FlowContextInputException extends BaseException {

    public FlowContextInputException(String message, String type, Temporal datetime) {
        super(message, type, datetime);
    }


}
