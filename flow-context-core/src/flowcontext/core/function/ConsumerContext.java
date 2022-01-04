package flowcontext.core.function;

import flowcontext.core.context.Context;

@FunctionalInterface
public interface ConsumerContext<I> {

    void accept(Context<I> context);

}
