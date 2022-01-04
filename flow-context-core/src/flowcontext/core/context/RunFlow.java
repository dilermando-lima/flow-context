package flowcontext.core.context;

import java.util.LinkedList;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.agreement.StepAgreement;
import flowcontext.core.base.FlowBase.RunFlowBase;
import flowcontext.core.base.FlowBase.StatusStepEnum;
import flowcontext.core.base.FlowBase.StepBase;
import flowcontext.core.base.FlowBase.TypeStepEnum;

public class RunFlow<I,O> implements RunFlowBase<I,O> {


    private LinkedList<StepBase<I>> stepQueue;
    private Agreement<O> flowOutput;
    private final Context<I> context;

    public RunFlow(Context<I> context) {
        this.context = context;
        this.flowOutput = new Agreement<>();
    }

    @Override
    public RunFlowBase<I,O> addStepQueueToProcess(LinkedList<StepBase<I>> stepQueue) {
        this.stepQueue = stepQueue;
        return this;
    }


    @Override
    public RunFlowBase<I,O> run() {
        stepQueue.forEach(this::processStep);
        return this;
    }

    private void processStep(StepBase<I> step){

        if( step.type() == TypeStepEnum.VALIDATION ){

            context.lastOutputStep(step.predictContext().test(context));
            step.status(StatusStepEnum.DONE);
            flowOutput.getStepList().add(convertStepBaseToAgreement(step));

        }else if( step.type() == TypeStepEnum.RETRIEVE || step.type() == TypeStepEnum.MAP ){


            context.lastOutputStep(step.functionContext().apply(context));
            step.status(StatusStepEnum.DONE);
            flowOutput.getStepList().add(convertStepBaseToAgreement(step));

        }else{
            throw new IllegalCallerException("xzsdf asdfasdf");
        }
    }

    private StepAgreement convertStepBaseToAgreement(StepBase<I> step){
        StepAgreement stepAgreement = new StepAgreement();
        stepAgreement.setKey(step.key());
        stepAgreement.setName(step.name());
        stepAgreement.setKey(step.key());
        stepAgreement.setOrder(step.order());
        stepAgreement.setStatusStepEnum(step.status());
        return stepAgreement;
    }


    @Override
    public Agreement<O> collectOutput() {
        return flowOutput;
    }


    
}
