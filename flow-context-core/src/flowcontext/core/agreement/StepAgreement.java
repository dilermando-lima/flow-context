package flowcontext.core.agreement;

import java.util.ArrayList;
import java.util.List;

import flowcontext.core.base.FlowBase.StatusStepEnum;
import flowcontext.core.base.FlowBase.TypeStepEnum;

public class StepAgreement {

    private String key;
    private String name;
    private TypeStepEnum type;
    private StatusStepEnum statusStepEnum;
    private List<Error> errorList;
    private int order;

    @Override
    public String toString() {
        return "StepAgreement [" + (errorList != null ? "errorList=" + errorList + ", " : "")
                + (key != null ? "key=" + key + ", " : "") + (name != null ? "name=" + name + ", " : "") + "order="
                + order + ", " + (statusStepEnum != null ? "statusStepEnum=" + statusStepEnum + ", " : "")
                + (type != null ? "type=" + type : "") + "]";
    }

    public StepAgreement(){
        errorList = new ArrayList<>();
    }

    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TypeStepEnum getType() {
        return type;
    }
    public void setType(TypeStepEnum type) {
        this.type = type;
    }
    public StatusStepEnum getStatusStepEnum() {
        return statusStepEnum;
    }
    public void setStatusStepEnum(StatusStepEnum statusStepEnum) {
        this.statusStepEnum = statusStepEnum;
    }
    public List<Error> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
  
    
}
