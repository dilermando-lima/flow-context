package flowcontext.core.run;

import java.time.LocalDateTime;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.config.ConfigProp;
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
            // .overrideConfig(Map.of(ConfigProp.STEP_IGNORE_LIST,"retrievasdfasdfe 1"))
            .register()
                .stepRetrive(   "retrieve 1", context -> {  
                    System.out.println(context.config().get(ConfigProp.STEP_IGNORE_LIST));
                     context.config().put(ConfigProp.STEP_IGNORE_LIST, "fasdf"); 
                     System.out.println(context.config().get(ConfigProp.STEP_IGNORE_LIST));
                     return LocalDateTime.now(); 
                    })
                .stepValidate(  "validate 1", context -> context.retriveOutputStep("retrieve 1") != null )
                .stepMap(       "map 1",      context -> context.retriveOutputStep("retrieve 1").toString()  )
                .collectOutput( "final ",   flowContext , context -> context.retriveOutputStep("map 1").toString() + "-map")
            .runFlow();

        System.out.println("payload : " + agreementOutput.getPayload());
        System.out.println("hasError : " +  agreementOutput.hasError());
        System.out.println("metadata : " + agreementOutput.getMetadata());
    

    }
    
}
