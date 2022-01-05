package flowcontext.core.implementation;

import java.time.LocalDateTime;
import java.util.List;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.agreement.Error;
import flowcontext.core.agreement.StepAgreement;
import flowcontext.core.base.FlowBase.RunFlowBase;
import flowcontext.core.base.FlowBase.StatusStepEnum;
import flowcontext.core.base.FlowBase.StepBase;
import flowcontext.core.base.FlowBase.TypeStepEnum;
import flowcontext.core.context.Context;

public class RunFlowDefault<I,O> implements RunFlowBase<I,O> {


    private List<StepBase<I>> stepQueue;
    private Agreement<O> flowOutput;
    private final Context<I> context;

    public RunFlowDefault(Context<I> context) {
        this.context = context;
    }

    @Override
    public RunFlowBase<I,O> addStepQueueToProcess(List<StepBase<I>> stepQueue) {
        this.stepQueue = stepQueue;
        return this;
    }

    @Override
    public RunFlowBase<I,O> run() {
        loadAgreementtInputIntoOutput();
        stepQueue.forEach(this::processStep);
        return this;
    }

    private void processStep(StepBase<I> step){

        try{

            if( step.type() == TypeStepEnum.VALIDATION || step.type() == TypeStepEnum.CHECK_SKIP_NEXT ){
                context.addOutputStep(step.name(),step.predictContext().test(context));

            }else if( step.type() == TypeStepEnum.RETRIEVE || step.type() == TypeStepEnum.MAP || step.type() == TypeStepEnum.EXECUTE ){
                context.addOutputStep(step.name(),step.functionContext().apply(context));

            }else if( step.type() == TypeStepEnum.ON_ABORTED_FLOW || step.type() == TypeStepEnum.GOST ){
                step.consumerContext().accept(context);

            }else if( step.type() == TypeStepEnum.COLLECT_OUTPUT ){
                // create error to check this before
                flowOutput.setPayload( (O) step.functionContext().apply(context));
                context.addOutputStep(step.name() , flowOutput.getPayload());
            }else{
                throw new IllegalCallerException("xzsdf asdfasdf");
            }

            step.status(StatusStepEnum.DONE);
            flowOutput.getStepList().add(convertStepBaseToAgreement(step, null));

        }catch(Throwable throwable){
            step.status(StatusStepEnum.FAILED);
            flowOutput.getStepList().add(convertStepBaseToAgreement(step, new Error(throwable.getMessage(), LocalDateTime.now(), throwable.getClass().getName())));
        }
    
    }

    private void loadAgreementtInputIntoOutput(){

        flowOutput = new Agreement<>();

        flowOutput.setHeader(context.flowInput().getHeader());

        flowOutput.getMetadata().setKeyOrigin(context.flowInput().getMetadata().getKeyOrigin());
        flowOutput.getMetadata().setNameOrigin(context.flowInput().getMetadata().getNameOrigin());
        flowOutput.getMetadata().setOwnerOrigin(context.flowInput().getMetadata().getOwnerOrigin());
    }

    private StepAgreement convertStepBaseToAgreement(StepBase<I> step, Error error ){
        StepAgreement stepAgreement = new StepAgreement();
        stepAgreement.setKey(step.key());
        stepAgreement.setName(step.name());
        stepAgreement.setKey(step.key());
        stepAgreement.setOrder(step.order());
        stepAgreement.setStatusStepEnum(step.status());
        if( error != null) stepAgreement.getErrorList().add(error);
        return stepAgreement;
    }

    @Override
    public Agreement<O> collectOutput() {
        return flowOutput;
    }


    
}
