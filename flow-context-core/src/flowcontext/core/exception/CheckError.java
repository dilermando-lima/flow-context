package flowcontext.core.exception;

public enum CheckError implements CheckErrorBase {
    
    THROWER_ERROR_ON_THROW_NEW_BASE_EXCEPTION("error when throwing new BaseException",BaseException.class),
    SETUP_REGISTER_STEP_REQUIRES_NON_NULL("step register cannot be null",FlowContextSetupStepException.class),
    SETUP_STEP_NAME_REQUIRES_NON_NULL("step name cannot be null",FlowContextSetupStepException.class),
    SETUP_STEP_NAME_HAS_WRONG_FORMAT("step name '%s' must only contains [a-z A-Z 0-9 - _ .] and be less then 60 caract",FlowContextSetupStepException.class)
    ;

    private final String message;
    private final Class<? extends BaseException> exception;

    private CheckError(String message, Class<? extends BaseException> exception) {
        this.message = message;
        this.exception = exception;
    }

    public Thrower valid(boolean condition){
        return new Thrower(this, null).valid(condition);
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Class<? extends BaseException> getException() {
        return exception;
    }
    
    @Override
    public Thrower replace(Object... replacement) {
        return new Thrower(this, replacement);
    }
}
