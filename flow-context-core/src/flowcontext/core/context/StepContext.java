package flowcontext.core.context;

import java.util.LinkedList;
import java.util.UUID;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase;
import flowcontext.core.base.FlowBase.RunFlowBase;
import flowcontext.core.base.FlowBase.StepBase;
import flowcontext.core.base.FlowBase.StepContextBase;
import flowcontext.core.base.FlowBase.TypeStepEnum;
import flowcontext.core.function.FunctionContext;
import flowcontext.core.function.PredictContext;


public class StepContext<I,O> implements StepContextBase<I,O> {

    private LinkedList<StepBase<I>> stepQueue = new LinkedList<>();
    private final FlowBase<I,O> flowContext;
    private Context<I> context;

    public StepContext(FlowBase<I, O> flowContext,Agreement<I> flowInput) {
        this.flowContext = flowContext;
        this.context = new Context<>(flowInput);
    }
    

    @Override
    public FlowBase<I, O> runFlow() {
        return runFlow(new RunFlow<>(context));
    }

    @Override
    public FlowBase<I, O> runFlow(RunFlowBase<I,O>  runFlow) {
        
        runFlow
            .addStepQueueToProcess(stepQueue)
            .run();

        flowContext.output(runFlow.collectOutput());

        return flowContext;
    }


    @Override
    public StepContextBase<I, O> stepValidate(String name, PredictContext<I> predict) {
        stepQueue.add(
                new Step<I>(name, UUID.randomUUID().toString(), TypeStepEnum.VALIDATION, stepQueue.size() + 1 ).predictContext(predict)
                );
        return this;
    }

    @Override
    public StepContextBase<I, O> stepRetrive(String name, FunctionContext<Object, I> function) {
        stepQueue.add(
            new Step<I>(name, UUID.randomUUID().toString(), TypeStepEnum.RETRIEVE, stepQueue.size() + 1).functionContext(function)
            );
        return this;
    }

    @Override
    public StepContextBase<I, O> stepMap(String name, FunctionContext<Object, I> function) {
        stepQueue.add(
            new Step<I>(name, UUID.randomUUID().toString(), TypeStepEnum.MAP, stepQueue.size() + 1).functionContext(function)
            );
        return this;
    }


  
}
