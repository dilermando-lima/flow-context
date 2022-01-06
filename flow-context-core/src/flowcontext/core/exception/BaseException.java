package flowcontext.core.exception;

import java.time.temporal.Temporal;

class BaseException extends RuntimeException {

    private final String type;
    private final transient Temporal datetime;

    public BaseException(String message, String type, Temporal datetime) {
        super(message);
        this.type = type;
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public Temporal getDatetime() {
        return datetime;
    }

}
