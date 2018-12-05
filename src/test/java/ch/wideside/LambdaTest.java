package ch.wideside;

import ch.wideside.model.Bean1;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
@RunWith(JUnit4.class)
public class LambdaTest {

    private Lambda target;

    @Before
    public void setUp() {
        target = new Lambda();
    }

    // ############## supplier ################
    @Test
    public void shouldReturnSuppliedBean1() {
        Supplier<Bean1> supplier = () -> new Bean1("aa", 3);

        Bean1 bean1 = target.get(supplier);

        Assertions.assertThat(bean1.getX()).isEqualTo("aa");
        Assertions.assertThat(bean1.getY()).isEqualTo(3);
    }

    // ############## supplier method reference ################
    @Test
    public void shouldReturnSuppliedBean2() {
        Supplier<Bean1> supplier = this::getBean1;

        Bean1 bean1 = target.get(supplier);

        Assertions.assertThat(bean1.getX()).isEqualTo("aa");
        Assertions.assertThat(bean1.getY()).isEqualTo(3);
    }

    // ############## predicate ################
    @Test
    public void shouldReturnTrueForMatchingPredicate1() {
        Predicate<Bean1> predicate = bean1 -> bean1.getY() > 0;

        Boolean matches = target.match(predicate, this::getBean1);
        Assertions.assertThat(matches).isTrue();
    }

    // ############## predicate method reference ################
    @Test
    public void shouldReturnTrueForMatchingPredicate2() {
        Predicate<Bean1> predicate = this::test;

        Boolean matches = target.match(predicate, this::getBean1);
        Assertions.assertThat(matches).isTrue();
    }

    // ############## predicate ################
    @Test
    public void shouldReturnFalseForNonMatchingPredicate() {
        Predicate<Bean1> predicate = bean1 -> bean1.getY() < 0;

        Boolean matches = target.match(predicate, this::getBean1);
        Assertions.assertThat(matches).isFalse();
    }

    // ############## UnaryOperator ################
    @Test
    public void shouldReturnModifiedBean1() {
        UnaryOperator<Bean1> unaryOperator = bean1 -> new Bean1(bean1.getX(), bean1.getY() * 10);

        Bean1 bean1 = target.get(unaryOperator, getBean1());

        Assertions.assertThat(bean1.getX()).isEqualTo("aa");
        Assertions.assertThat(bean1.getY()).isEqualTo(30);
    }

    // ############## function ################
    @Test
    public void shouldReturnAStringFromBean1() {
        Function<Bean1, String> function = bean1 -> bean1.getX() + "-" + bean1.getY();

        String toString = target.toString(function, getBean1());

        Assertions.assertThat(toString).isEqualTo("aa-3");
    }

    // ############## function compose ################
    @Test
    public void shouldReturn8() {

        Function<Integer, Integer> times2 = e -> e * 2;

        Function<Integer, Integer> squared = e -> e * e;

        Integer result = target.compose(2, times2, squared);

        Assertions.assertThat(result).isEqualTo(8);
    }

    // ############## function compose ################
    @Test
    public void shouldReturn16() {

        Function<Integer, Integer> times2 = e -> e * 2;

        Function<Integer, Integer> squared = e -> e * e;

        Integer result = target.compose(2, squared, times2);

        Assertions.assertThat(result).isEqualTo(16);
    }

    private Bean1 getBean1() {
        return new Bean1("aa", 3);
    }

    private Boolean test(Bean1 bean1) {
        return bean1.getY() > 0;
    }
}
