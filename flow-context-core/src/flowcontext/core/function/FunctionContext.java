package flowcontext.core.function;

import flowcontext.core.context.Context;

@FunctionalInterface
public interface FunctionContext<R,I> {

    R apply(Context<I> context);

}
