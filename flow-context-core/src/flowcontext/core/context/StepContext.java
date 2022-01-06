package flowcontext.core.context;

import flowcontext.core.base.FlowBase.StatusStepEnum;
import flowcontext.core.base.FlowBase.StepBase;
import flowcontext.core.base.FlowBase.TypeStepEnum;
import flowcontext.core.exception.CheckError;
import flowcontext.core.function.ConsumerContext;
import flowcontext.core.function.FunctionContext;
import flowcontext.core.function.PredictContext;

public class StepContext<I> implements StepBase<I>{

    private final String name;
    private final String key;
    private final TypeStepEnum type;
    private final int order;
   
    private PredictContext<I> predictContext;
    private FunctionContext<Object, I> functionContext;
    private ConsumerContext<I> consumerContext;

    private StatusStepEnum statusStepEnum;

    public StepContext( String name, String key, TypeStepEnum type, int order) {

        CheckError.SETUP_STEP_NAME_REQUIRES_NON_NULL.valid(name == null).throwIfTrue();
        CheckError.SETUP_STEP_NAME_HAS_WRONG_FORMAT.replace(name).valid(!name.matches("^[a-zA-Z0-9_\\-.]{0,60}+$")).throwIfTrue();

        this.name = name;
        this.key = key;
        this.type = type;
        this.order = order;
    }

    @Override
    public TypeStepEnum type() {
        return type;
    }

    @Override
    public StatusStepEnum status() {
        return statusStepEnum;
    }

    @Override
    public StepBase<I> status(StatusStepEnum statusStepEnum) {
        this.statusStepEnum = statusStepEnum;
        return this;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public PredictContext<I> predictContext() {
        return predictContext;
    }

    @Override
    public FunctionContext<Object, I> functionContext() {
        return functionContext;
    }

    @Override
    public ConsumerContext<I> consumerContext() {
        return consumerContext;
    }

    @Override
    public StepBase<I> consumerContext(ConsumerContext<I> consumerContext) {
        this.consumerContext = consumerContext;
        return this;
    }

    @Override
    public StepBase<I> functionContext(FunctionContext<Object, I> functionContext) {
        this.functionContext = functionContext;
        return this;
    }

    @Override
    public StepBase<I> predictContext(PredictContext<I> predictContext) {
        this.predictContext = predictContext;
        return this;
    }

    @Override
    public int order() {
        return order;
    }


    
}
