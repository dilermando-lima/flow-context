package flowcontext.core.context;

import java.util.HashMap;
import java.util.Map;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase.ContextBase;

public class Context<I> implements ContextBase<I> {

    private final Agreement<I> flowInput;
    private final Map<String,Object> values;
    private Object lastOutputStep;
    
    public Context(Agreement<I> flowInput) {
        this.flowInput = flowInput;
        this.values = new HashMap<>(3);
    }

    @Override
    public Agreement<I> flowInput() {
        return flowInput;
    }

    @Override
    public Object val(String key) {
        return values.get(key);
    }

    @Override
    public Object lastOutputStep() {
        return lastOutputStep;
    }

    @Override
    public ContextBase<I> saveVal(String key, Object value) {
        values.put(key, value);
        return this;
    }

    @Override
    public boolean containsVal(String key) {
        return key != null && values.containsKey(key);
    }

    @Override
    public ContextBase<I> lastOutputStep(Object lastOutputStep) {
        this.lastOutputStep = lastOutputStep;
        return this;
    }
    
}
