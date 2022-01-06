package flowcontext.core.exception;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class Thrower {

    private final CheckErrorBase checkError;
    private final Object[] replacementMessage;
    private boolean condition;

    public Thrower(CheckErrorBase checkError, Object[] replacementMessage) {
        this.checkError = checkError;
        this.replacementMessage = replacementMessage;
    }

    public Thrower valid(boolean condition){
        this.condition = condition;
        return this;
    }

    public void throwIfTrue(){
        if( condition ){
            try {
                this.checkError
                    .getException()
                    .getConstructor(String.class,Temporal.class)
                    .newInstance(String.format(this.checkError.getMessage(), replacementMessage),LocalDateTime.now());

            } catch (ReflectiveOperationException e) {
                throw new BaseException(
                            CheckError.THROWER_ERROR_ON_THROW_NEW_BASE_EXCEPTION.getMessage(), 
                            CheckError.THROWER_ERROR_ON_THROW_NEW_BASE_EXCEPTION.getException().getName(), 
                            LocalDateTime.now());
            }
        }
    }
    
    
}
