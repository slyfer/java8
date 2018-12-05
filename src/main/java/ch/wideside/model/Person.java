package ch.wideside.model;

import java.util.Optional;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class Person {

    private String name;

    private Optional<Car> car;

    public Person(String name, Car car) {
        this.name = name;
        this.car = Optional.ofNullable(car);
    }

    public String getName() {
        return name;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
