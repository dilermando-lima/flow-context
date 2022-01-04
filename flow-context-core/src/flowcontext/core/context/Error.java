package flowcontext.core.context;

import java.time.temporal.Temporal;

import flowcontext.core.base.FlowBase.ErrorBase;

public class Error implements ErrorBase {

    private final String message;
    private final Temporal datetime;
    private final String type;

    public Error(String message, Temporal datetime, String type) {
        this.message = message;
        this.datetime = datetime;
        this.type = type;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public Temporal datetime() {
        return datetime;
    }

    @Override
    public String type() {
        return type;
    }

    
    
}
