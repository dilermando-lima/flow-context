package flowcontext.core.context;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase;
import flowcontext.core.implementation.RunFlowDefault;
import flowcontext.core.implementation.StepRegisterDefault;

public class FlowContext<I,O> implements FlowBase<I,O> {
    
    private Agreement<I> flowInput;
    private Context<I> context;
    private StepRegisterBase<I, O> stepRegister;
    
    public StepRegisterDefault<I, O> register() {
        return register(new StepRegisterDefault<I, O>());
    }

    @Override
    public <T extends StepRegisterBase<I, O>> T register(T stepRegister) {
        this.stepRegister = stepRegister;
        return stepRegister;
    }

    @Override
    public FlowContext<I, O> input(Agreement<I> flowInput) {
        this.flowInput = flowInput;
        this.context =  new Context<>(flowInput);
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
        return runFlow(new RunFlowDefault<>(context));
    }


}
