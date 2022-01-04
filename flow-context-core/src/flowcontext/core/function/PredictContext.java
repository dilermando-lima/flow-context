package flowcontext.core.function;

import flowcontext.core.context.Context;

@FunctionalInterface
public interface PredictContext<I> {

    boolean test(Context<I> context);

}
