package flowcontext.core.agreement;

import flowcontext.core.base.FlowBase.StatusFlowEnum;

public class Metadata {
    
    private String nameFlow;
    private String keyFlow;
    private String ownerFlow;
    private String nameOrigin;
    private String keyOrigin;
    private String ownerOrigin;
    private String startTime;
    private String endTime;
    private StatusFlowEnum status;

    
    @Override
    public String toString() {
        return "Metadata [" + (endTime != null ? "endTime=" + endTime + ", " : "")
                + (keyFlow != null ? "keyFlow=" + keyFlow + ", " : "")
                + (keyOrigin != null ? "keyOrigin=" + keyOrigin + ", " : "")
                + (nameFlow != null ? "nameFlow=" + nameFlow + ", " : "")
                + (nameOrigin != null ? "nameOrigin=" + nameOrigin + ", " : "")
                + (ownerFlow != null ? "ownerFlow=" + ownerFlow + ", " : "")
                + (ownerOrigin != null ? "ownerOrigin=" + ownerOrigin + ", " : "")
                + (startTime != null ? "startTime=" + startTime + ", " : "")
                + (status != null ? "status=" + status : "") + "]";
    }


    public String getNameFlow() {
        return nameFlow;
    }
    public void setNameFlow(String nameFlow) {
        this.nameFlow = nameFlow;
    }
    public String getKeyFlow() {
        return keyFlow;
    }
    public void setKeyFlow(String keyFlow) {
        this.keyFlow = keyFlow;
    }
    public String getOwnerFlow() {
        return ownerFlow;
    }
    public void setOwnerFlow(String ownerFlow) {
        this.ownerFlow = ownerFlow;
    }
    public String getNameOrigin() {
        return nameOrigin;
    }
    public void setNameOrigin(String nameOrigin) {
        this.nameOrigin = nameOrigin;
    }
    public String getKeyOrigin() {
        return keyOrigin;
    }
    public void setKeyOrigin(String keyOrigin) {
        this.keyOrigin = keyOrigin;
    }
    public String getOwnerOrigin() {
        return ownerOrigin;
    }
    public void setOwnerOrigin(String ownerOrigin) {
        this.ownerOrigin = ownerOrigin;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public StatusFlowEnum getStatus() {
        return status;
    }
    public void setStatus(StatusFlowEnum status) {
        this.status = status;
    }

    
}
