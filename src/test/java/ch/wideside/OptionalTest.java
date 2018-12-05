package ch.wideside;

import ch.wideside.model.Car;
import ch.wideside.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class OptionalTest {

    private MyOptional target;

    @Before
    public void setUp() {
        target = new MyOptional();
    }

    @Test
    public void shouldReturnPersonName() {
        String name = target.getName(Optional.of(new Person("Franco", null)));

        Assertions.assertThat(name).isEqualTo("Franco");
    }

    @Test
    public void shouldReturnUnknown() {
        String name = target.getName(Optional.ofNullable(null));

        Assertions.assertThat(name).isEqualTo("unknown");
    }

    @Test
    public void shouldReturnUnknown1() {
        String name = target.getNameWithDefault(Optional.empty(), () -> "unknown1");

        Assertions.assertThat(name).isEqualTo("unknown1");
    }

    @Test
    public void shouldReturnCarModel() {
        String model = target.getCarModel(Optional.of(new Person("Franco", new Car("ibiza"))));

        Assertions.assertThat(model).isEqualTo("ibiza");
    }

    @Test
    public void shouldReturnUnknownForCar1() {
        String model = target.getCarModel(Optional.empty());

        Assertions.assertThat(model).isEqualTo("unknown");
    }

    @Test
    public void shouldReturnUnknownForCar2() {
        String model = target.getCarModel(Optional.of(new Person("Franco", null)));

        Assertions.assertThat(model).isEqualTo("unknown");
    }

    @Test
    public void shouldReturnUnknownForFiltering() {
        Optional<Person> person = Optional.of(new Person("Franco", new Car("ibiza", "red")));
        String model = target.getCarModel(person, car -> "green".equalsIgnoreCase(car.getColor()));

        Assertions.assertThat(model).isEqualTo("unknown");
    }

    @Test
    public void shouldReturnCorrectModelForFiltering() {
        Optional<Person> person = Optional.of(new Person("Franco", new Car("ibiza", "green")));
        String model = target.getCarModel(person, car -> "green".equalsIgnoreCase(car.getColor()));

        Assertions.assertThat(model).isEqualTo("ibiza");
    }

}
