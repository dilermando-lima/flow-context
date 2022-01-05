package flowcontext.core.context;

import java.util.HashMap;
import java.util.Map;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.base.FlowBase.ContextBase;

public class Context<I> implements ContextBase<I> {

    private final Agreement<I> flowInput;
    private final Map<String,Object> values;
    private Map<String,Object> outputStepMap;
    
    public Context(Agreement<I> flowInput) {
        this.flowInput = flowInput;
        this.values = new HashMap<>(3);
        this.outputStepMap = new HashMap<>(3);
    }

    public Object addOutputStep(String nameStep, Object output) {
        return outputStepMap.put(nameStep,output);
    }

    @Override
    public Object retriveOutputStep(String nameStep) {
        return outputStepMap.get(nameStep);
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
    public ContextBase<I> saveVal(String key, Object value) {
        values.put(key, value);
        return this;
    }

    @Override
    public boolean containsVal(String key) {
        return key != null && values.containsKey(key);
    }




    
}
