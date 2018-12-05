package ch.wideside;

import ch.wideside.model.Bean1;
import ch.wideside.model.Car;
import ch.wideside.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class ServiceTest {

    private Service target;

    @Before
    public void setUp() {
        target = new DefaultService();
    }

    @Test
    public void shouldReturnAllModels() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Mario", new Car("ferrari", "green")),
            new Person("Pasquale", new Car("seat", "red"))
        );

        List<String> allModels = target.getAllModels(personList);

        Assertions.assertThat(allModels).containsOnly("ferrari", "seat");
    }

    @Test
    public void shouldReturnAllModelsNotGreen() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Mario", new Car("ferrari", "green")),
            new Person("Pasquale", new Car("seat", "red"))
        );

        List<String> allModels = target.getAllModelsNotGreen(personList);

        Assertions.assertThat(allModels).containsOnly("seat");
    }

    @Test
    public void shouldReturnAllModelsNotRed() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Mario", new Car("ferrari", "green")),
            new Person("Pasquale", new Car("seat", "red"))
        );

        List<String> allModels = target.getAllModelsWithFilter(personList, car -> !"red".equalsIgnoreCase(car.getColor()));

        Assertions.assertThat(allModels).containsOnly("ferrari");
    }

    @Test
    public void shouldReturnAllDistinctModels() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Mario", new Car("ferrari", "green")),
            new Person("Pasquale", new Car("seat", "red")),
            new Person("Peppino", new Car("seat", "white"))
        );

        List<String> allModels = target.getAllDistinctModels(personList);

        Assertions.assertThat(allModels).containsOnly("ferrari", "seat");
    }

    @Test
    public void shouldReturnFirstRedModel() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Pasquale", new Car("seat", "red")),
            new Person("Mario", new Car("ferrari", "red")),
            new Person("Peppino", new Car("fiat", "red"))
        );

        Optional<String> model = target.getFirstModel(personList, car -> "red".equalsIgnoreCase(car.getColor()));

        Assertions.assertThat(model.isPresent()).isTrue();
        Assertions.assertThat(model.get()).isEqualTo("ferrari");
    }


    @Test
    public void shouldReturnMario() {
        List<Bean1> beanList = Arrays.asList(
            new Bean1("Franco", 1),
            new Bean1("Pasquale", 2),
            new Bean1("Mario", 3)
        );

        Optional<Bean1> max = target.findMax(beanList);

        Assertions.assertThat(max.isPresent()).isTrue();
        Assertions.assertThat(max.get().getX()).isEqualTo("Mario");
    }
}