package flowcontext.core.context;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase;

public class FlowContext<I,O> implements FlowBase<I,O> {
    
    private Agreement<I> flowInput;
    private Agreement<O> flowOutput;

    @Override
    public StepContextBase<I,O> start() {
        return new StepContext<>(this, flowInput);
    }

    @Override
    public FlowBase<I, O> input(Agreement<I> flowInput) {
        this.flowInput = flowInput;
        return this;
    }

    @Override
    public Agreement<I> input() {
        return flowInput;
    }

    @Override
    public FlowBase<I, O> output(Agreement<O> flowOutput) {
        this.flowOutput = flowOutput;
        return this;
    }

    @Override
    public Agreement<O> output() {
        return flowOutput;
    }



}
