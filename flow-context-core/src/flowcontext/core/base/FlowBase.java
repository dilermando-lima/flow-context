package flowcontext.core.base;

import java.time.temporal.Temporal;
import java.util.LinkedList;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.function.ConsumerContext;
import flowcontext.core.function.FunctionContext;
import flowcontext.core.function.PredictContext;

public interface FlowBase<I,O> {

        public FlowBase<I,O> input(Agreement<I> flowInput);
        public Agreement<I> input();
        public FlowBase<I,O> output(Agreement<O> flowOutput);
        public Agreement<O> output();

        public StepContextBase<I,O> start();


        public interface StepContextBase<I,O>{

            public StepContextBase<I,O> stepValidate(String name, PredictContext<I> predict);

            public StepContextBase<I,O> stepRetrive(String name, FunctionContext<Object,I> function);

            public StepContextBase<I,O> stepMap(String name, FunctionContext<Object,I> function);

            // StepContextBase stepCheckSkipNext(StepBase<?> stepBase)

            // StepContextBase stepExec(StepBase<?> stepBase)

            // StepContextBase nonStepExec(StepBase<?> stepBase

            // StepContextBase stepWhenAbortFlow(StepBase<?> stepBase)

            FlowBase<I,O> runFlow(RunFlowBase<I,O> runFlow);

            FlowBase<I,O> runFlow();

        }

        public interface ContextBase<I>{

            Agreement<I> flowInput();

            Object val(String key);

            ContextBase<I> saveVal(String key, Object value);

            boolean containsVal(String key);

            Object lastOutputStep();

            ContextBase<I> lastOutputStep(Object lastOutputStep);

        }


        public interface RunFlowBase<I,O>{
            
            RunFlowBase<I,O> addStepQueueToProcess(LinkedList<StepBase<I>> stepQueue);

            RunFlowBase<I,O> run();

            Agreement<O> collectOutput();

        }


        public interface StepBase<I>{

            TypeStepEnum type();

            int order();

            StatusStepEnum status();

            StepBase<I> status(StatusStepEnum statusStepEnum);

            String key();

            String name();

            Object input();

            Object output();

            PredictContext<I> predictContext();

            FunctionContext<Object,I> functionContext();

            ConsumerContext<I> consumerContext();

            StepBase<I> consumerContext(ConsumerContext<I> consumerContext);

            StepBase<I> functionContext(FunctionContext<Object,I> functionContext);

            StepBase<I> predictContext(PredictContext<I> predictContext);

        }

        

        public enum StatusFlowEnum{
            ABORTED, // aborted by FLOW_ABORTED from any step
            FAILED,  // ended with errors
            DONE // ended with no errors
        }

        public enum StatusStepEnum{
            STEP_ABORTED, 
            FLOW_ABORTED, 
            STEP_SKIPED, 
            FAILED, 
            DONE
        }

        public enum TypeStepEnum{
            VALIDATION, RETRIEVE, MAP, EXEC, CHECK_SKIP_NEXT , ON_ABORTED_FLOW, CUSTOM;
        }


        public interface ErrorBase{

            String message();
            Temporal datetime();
            String type();

        }
    
}
