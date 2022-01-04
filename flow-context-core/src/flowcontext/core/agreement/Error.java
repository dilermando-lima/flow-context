package flowcontext.core.agreement;

import java.time.temporal.Temporal;

public class Error {

    private String message;
    private Temporal datetime;
    private String type;


    @Override
    public String toString() {
        return "Error [" + (datetime != null ? "datetime=" + datetime + ", " : "")
                + (message != null ? "message=" + message + ", " : "") + (type != null ? "type=" + type : "") + "]";
    }

    public Error(String message, Temporal datetime, String type) {
        this.message = message;
        this.datetime = datetime;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public Temporal getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatetime(Temporal datetime) {
        this.datetime = datetime;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
