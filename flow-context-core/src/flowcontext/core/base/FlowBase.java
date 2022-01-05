package flowcontext.core.base;

import java.util.List;
import java.util.Map;

import flowcontext.core.agreement.Agreement;
import flowcontext.core.config.ConfigProp;
import flowcontext.core.config.MapConfig;
import flowcontext.core.function.ConsumerContext;
import flowcontext.core.function.FunctionContext;
import flowcontext.core.function.PredictContext;

public interface FlowBase<I,O> {

        public FlowBase<I,O> input(Agreement<I> flowInput);
        public Agreement<I> input();

        public <T extends StepRegisterBase<I,O>> T register(T stepRegister);

        Agreement<O> runFlow(RunFlowBase<I,O> runFlow);

        public FlowBase<I,O> overrideConfig(Map<ConfigProp,Object> mapConfigOverride);

        public FlowBase<I,O> config(ConfigLoadBase configLoad, boolean reloadConfigInEachFlow);
        


        public interface StepRegisterBase<I,O>{

            public  <T extends FlowBase<I,O>> T collectOutput(String name, T flowContext, FunctionContext<Object,I> function);

            public List<StepBase<I>> stepQueue();

        }

        public interface ContextBase<I>{

            Agreement<I> flowInput();

            Object val(String key);

            ContextBase<I> saveVal(String key, Object value);

            boolean containsVal(String key);

            Object retriveOutputStep(String nameStep);

            Map<ConfigProp,Object> config();

        }


        public interface RunFlowBase<I,O>{
            
            RunFlowBase<I,O> addStepQueueToProcess(List<StepBase<I>> stepQueue);

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
            VALIDATION, 
            RETRIEVE, 
            MAP, 
            EXECUTE, 
            GOST,
            CHECK_SKIP_NEXT , 
            ON_ABORTED_FLOW, 
            COLLECT_OUTPUT
        }

        @FunctionalInterface
        public interface ConfigLoadBase{
            MapConfig loadConfig();
        }   
    
}
