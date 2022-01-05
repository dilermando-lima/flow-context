package flowcontext.core.context;

import java.util.Collections;
import java.util.Map;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase;
import flowcontext.core.config.ConfigProp;
import flowcontext.core.config.FlowConfigApplicationScope;
import flowcontext.core.config.MapConfig;
import flowcontext.core.implementation.ConfigLoadDefault;
import flowcontext.core.implementation.RunFlowDefault;
import flowcontext.core.implementation.StepRegisterDefault;

public class FlowContext<I,O> implements FlowBase<I,O> {
    
    private Agreement<I> flowInput;
    private Context<I> context;
    private StepRegisterBase<I, O> stepRegister;

    private MapConfig config;
    private ConfigLoadBase configLoad;
    private boolean reloadConfigInEachFlow;
    private Map<ConfigProp, Object> mapConfigOverride;

    public StepRegisterDefault<I, O> register() {
        return register(new StepRegisterDefault<I, O>());
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
        this.context =  new Context<>(flowInput, Collections.unmodifiableMap(config));
    }

    @Override
    public <T extends StepRegisterBase<I, O>> T register(T stepRegister) {
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
        return flowInput;
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
