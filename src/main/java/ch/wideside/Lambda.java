package ch.wideside;

import ch.wideside.model.Bean1;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class Lambda {

    public Bean1 get(Supplier<Bean1> supplier) {
        return supplier.get();
    }

    public Boolean match(Predicate<Bean1> predicate, Supplier<Bean1> supplier) {
        return predicate.test(supplier.get());
    }

    public Bean1 get(UnaryOperator<Bean1> unaryOperator, Bean1 bean1) {
        return unaryOperator.apply(bean1);
    }

    public String toString(Function<Bean1, String> function, Bean1 bean1) {
        return function.apply(bean1);
    }

    public Integer compose(int i, Function<Integer, Integer> function, Function<Integer, Integer> before) {
        return function.compose(before).apply(i);
    }
}
