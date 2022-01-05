package flowcontext.core.config;

public class FlowConfigApplicationScope {

    private FlowConfigApplicationScope(){}

    private static MapConfig config;

    public static void config(MapConfig config){
        FlowConfigApplicationScope.config = config;
    }

    public static MapConfig config(){
        return FlowConfigApplicationScope.config;
    }
    
}
