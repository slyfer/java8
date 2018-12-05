package ch.wideside;

import ch.wideside.model.Car;
import ch.wideside.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class StreamConsumeTest {

    @Test
    public void forEachExample() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Pasquale", new Car("seat", "red")),
            new Person("Mario", new Car("ferrari", "red")),
            new Person("Peppino", new Car("fiat", "red"))
        );

        personList.forEach(person -> System.out.println(person.getName() + "-" + person.getCar().map(Car::getModel).orElse("")));
    }

    @Test
    public void forEachExample2() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Pasquale", new Car("seat", "red")),
            new Person("Mario", new Car("ferrari", "red")),
            new Person("Peppino", new Car("fiat", "red"))
        );

        personList
            .stream()
            .filter(person -> person.getCar().isPresent())
            .forEach(person -> System.out.println(person.getName() + "-" + person.getCar().map(Car::getModel).orElse("")));

    }

    @Test
    public void peekExample() {
        List<Person> personList = Arrays.asList(
            new Person("Franco", null),
            new Person("Pasquale", new Car("seat", "red")),
            new Person("Mario", new Car("ferrari", "red")),
            new Person("Peppino", new Car("fiat", "red"))
        );

        personList
            .stream()
            .peek(person -> System.out.println(person.getName() + "-" + person.getCar().map(Car::getModel).orElse("")))
            .filter(person -> person.getCar().isPresent())
            .forEach(person -> System.out.println("Person that have a car : " + person.getName()));
    }
}
