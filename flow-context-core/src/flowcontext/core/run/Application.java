package flowcontext.core.run;

import java.time.LocalDateTime;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.context.FlowContext;

public class Application {

    public static void main(String[] args) {

        var flowContext =  new FlowContext<LocalDateTime,String>();

        var agreementInput = new Agreement<LocalDateTime>();
        agreementInput.setPayload((LocalDateTime) null);
        agreementInput.getHeader().put("header1", "value");
        agreementInput.getMetadata().setOwnerFlow("application-1");


        var agreementOutput = flowContext
            .input(agreementInput)
            .register()
                .stepRetrive(   "retrieve 1",  context ->  LocalDateTime.now() )
                .stepValidate(  "validate 1", context -> context.retriveOutputStep("retrieve 1") != null )
                .stepMap(       "map 1",           context -> context.retriveOutputStep("retrieve 1").toString()  )
                .collectOutput( "final ",    flowContext , context -> (String) context.retriveOutputStep("map 1") )
            .runFlow();

        
        System.out.println(agreementOutput);



    }
    
}
