package flowcontext.core.run;

import java.time.LocalDateTime;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.context.FlowContext;

public class Application {

    public static void main(String[] args) {

        var flowContext =  new FlowContext<LocalDateTime,String>();

        flowContext
            .input(Agreement.build((LocalDateTime) null))
            .start()
                .stepRetrive("retrieve 1", context ->  LocalDateTime.now() )
                .stepValidate("validate 1",context -> context.lastOutputStep() != null )
                .stepMap("map 1", Object::toString)
            .runFlow();
        
        System.out.println(flowContext.output().getPayload());
        System.out.println(flowContext.output().getHeader());
        System.out.println(flowContext.output().getMetadata());


    }
    
}
