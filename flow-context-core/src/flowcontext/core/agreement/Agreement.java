package flowcontext.core.agreement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Agreement<P> {

    private P payload;

    private Metadata metadata;

    private Map<String,String> header;

    private List<StepAgreement> stepList;

    public Agreement(){
        stepList = new LinkedList<>();
        header = new HashMap<>();
    }

    public static <P> Agreement<P> build(P payload){
        Agreement<P> agreement = new Agreement<>();
        agreement.setPayload(payload);
        return agreement;
    }

    public P getPayload() {
        return payload;
    }

    public void setPayload(P payload) {
        this.payload = payload;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public List<StepAgreement> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepAgreement> stepList) {
        this.stepList = stepList;
    }


}
