package flowcontext.core.exception;

import java.time.temporal.Temporal;

public class FlowContextConfigException extends BaseException {

    public FlowContextConfigException(String message, String type, Temporal datetime) {
        super(message, type, datetime);
    }

}
