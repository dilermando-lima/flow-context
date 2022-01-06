package flowcontext.core.exception;

public interface CheckErrorBase {
    public String getMessage();
    public Class<? extends BaseException> getException();
    public Thrower replace(Object... replacement);
}
