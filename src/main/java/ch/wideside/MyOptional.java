package ch.wideside;

import ch.wideside.model.Car;
import ch.wideside.model.Person;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class MyOptional {

    public String getName(Optional<Person> person) {
        return person
            .map(Person::getName)
            .orElse("unknown");
    }

    public String getNameWithDefault(Optional<Person> person, Supplier<String> defaultNameSupplier) {
        return person
            .map(Person::getName)
            .orElseGet(defaultNameSupplier);
    }

    public String getCarModel(Optional<Person> person) {
        return person
            .flatMap(Person::getCar)
            .map(Car::getModel)
            .orElse("unknown");

//        if (person.isPresent()) {
//            Person p = person.get();
//            if (p.getCar().isPresent()) {
//                Car car = p.getCar().get();
//                return car.getModel();
//            }
//        }
//
//        return "unknown";
    }

    public String getCarModel(Optional<Person> person, Predicate<Car> colorPredicate) {
        return person
            .flatMap(Person::getCar)
            .filter(colorPredicate)
            .map(Car::getModel)
            .orElse("unknown");

//        if (person.isPresent()) {
//            Person p = person.get();
//            if (p.getCar().isPresent()) {
//                Car car = p.getCar().get();
//                if (colorPredicate.test(car)) {
//                    return car.getModel();
//                }
//            }
//        }
//
//        return "unknown";
    }
}
