package flowcontext.core.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import flowcontext.core.base.FlowBase;
import flowcontext.core.base.FlowBase.StepBase;
import flowcontext.core.base.FlowBase.RegisterStepBase;
import flowcontext.core.base.FlowBase.TypeStepEnum;
import flowcontext.core.context.StepContext;
import flowcontext.core.function.ConsumerContext;
import flowcontext.core.function.FunctionContext;
import flowcontext.core.function.PredictContext;


public class RegisterStepDefault<I,O> implements RegisterStepBase<I,O> {

    private LinkedList<StepBase<I>> stepQueue = new LinkedList<>();

    public static final int VALUE_ORDER_FOR_GOST_STEP = 0;
 
    @Override
    public List<StepBase<I>> stepQueue(){
        return stepQueue;
    }

    @Override
    public <T extends FlowBase<I, O>> T collectOutput(String name, T flowContext, FunctionContext<Object, I> function) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.COLLECT_OUTPUT, stepQueue.size() + 1).functionContext(function)
            );
        return flowContext;
    }

    public RegisterStepDefault<I, O> stepValidate(String name, PredictContext<I> predict) {
        stepQueue.add(
                new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.VALIDATION, stepQueue.size() + 1 ).predictContext(predict)
                );
        return this;
    }


    public RegisterStepDefault<I, O> stepRetrive(String name, FunctionContext<Object, I> function) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.RETRIEVE, stepQueue.size() + 1).functionContext(function)
            );
        return this;
    }


    public RegisterStepDefault<I, O> stepMap(String name, FunctionContext<Object, I> function) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.MAP, stepQueue.size() + 1).functionContext(function)
            );
        return this;
    }

    public RegisterStepDefault<I, O> stepCheckSkipNext(String name, PredictContext<I> predict) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.CHECK_SKIP_NEXT, stepQueue.size() + 1).predictContext(predict)
            );
        return this;
    }

    public RegisterStepDefault<I, O> stepExecute(String name, FunctionContext<Object, I> function) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.EXECUTE, stepQueue.size() + 1).functionContext(function)
            );
        return this;
    }

    public RegisterStepDefault<I, O> stepOnAbortFlow(String name, ConsumerContext<I> consumer) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.ON_ABORTED_FLOW, stepQueue.size() + 1).consumerContext(consumer)
            );
        return this;
    }

    public RegisterStepDefault<I, O> stepGost(String name, ConsumerContext<I> consumer) {
        stepQueue.add(
            new StepContext<I>(name, UUID.randomUUID().toString(), TypeStepEnum.GOST, VALUE_ORDER_FOR_GOST_STEP ).consumerContext(consumer)
            );
        return this;
    }



}
