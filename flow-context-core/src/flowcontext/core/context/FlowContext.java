package flowcontext.core.context;

import java.util.Collections;
import java.util.Map;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase;
import flowcontext.core.config.ConfigProp;
import flowcontext.core.config.FlowConfigApplicationScope;
import flowcontext.core.config.MapConfig;
import flowcontext.core.exception.CheckError;
import flowcontext.core.implementation.ConfigLoadDefault;
import flowcontext.core.implementation.RegisterStepDefault;
import flowcontext.core.implementation.RunFlowDefault;

public class FlowContext<I,O> implements FlowBase<I,O> {
    
    private Agreement<I> flowInput;
    private Context<I> context;
    private RegisterStepBase<I, O> stepRegister;

    private MapConfig config;
    private ConfigLoadBase configLoad;
    private boolean reloadConfigInEachFlow;
    private Map<ConfigProp, Object> mapConfigOverride;

    public RegisterStepDefault<I, O> register() {
        return register(new RegisterStepDefault<I, O>());
    }

    private void handleConfigLoading(){
        
        if( reloadConfigInEachFlow ){
            config = configLoad.loadConfig();

        }else{

            config = FlowConfigApplicationScope.config();
            if( config == null ){

                if( configLoad == null ) configLoad = new ConfigLoadDefault();

                config = configLoad.loadConfig();
                FlowConfigApplicationScope.config(config);
            }
        }
        config.handleOverriding(mapConfigOverride);

    }

    private void handleContext(){
        handleConfigLoading();

        if( flowInput == null ) flowInput = Agreement.build(null);

        this.context =  new Context<>(flowInput, Collections.unmodifiableMap(config));
    }

    @Override
    public <T extends RegisterStepBase<I, O>> T register( T stepRegister) {

        CheckError.SETUP_REGISTER_STEP_REQUIRES_NON_NULL.valid(stepRegister == null).throwIfTrue();

        this.stepRegister = stepRegister;
        return stepRegister;
    }

    @Override
    public FlowContext<I, O> input(Agreement<I> flowInput) {
        this.flowInput = flowInput;
        return this;
    }

    @Override
    public Agreement<I> input() {
        return flowInput == null ? Agreement.build(null) : flowInput;
    }

    @Override
    public Agreement<O> runFlow(RunFlowBase<I, O> runFlow) {
        return runFlow
                    .addStepQueueToProcess(stepRegister.stepQueue())
                    .run()
                    .collectOutput();
    }

    public Agreement<O> runFlow() {
        handleContext();
        return runFlow(new RunFlowDefault<>(context));
    }

    @Override
    public FlowContext<I, O> config(ConfigLoadBase configLoad, boolean reloadConfigInEachFlow) {
        this.configLoad = configLoad;
        this.reloadConfigInEachFlow = reloadConfigInEachFlow;
        return this;
    }

    public FlowContext<I, O> config(ConfigLoadBase configLoad) {
        return config(configLoad, false);
    }

    @Override
    public FlowContext<I, O> overrideConfig(Map<ConfigProp, Object> mapConfigOverride) {
        this.mapConfigOverride = mapConfigOverride;
        return this;
    }

}
