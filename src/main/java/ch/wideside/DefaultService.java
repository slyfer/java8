package ch.wideside;

import ch.wideside.model.Bean1;
import ch.wideside.model.Car;
import ch.wideside.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class DefaultService implements Service {

    @Override
    public List<String> getAllModelsWithFilter(List<Person> personList, Predicate<Car> carPredicate) {

        return personList
            .stream()
            .map(Person::getCar)
            .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
//            .filter(Optional::isPresent)
//            .map(Optional::get)
            .filter(carPredicate)
            .map(Car::getModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllDistinctModels(List<Person> personList) {
        return personList
            .stream()
            .map(Person::getCar)
            .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
            .map(Car::getModel)
            .distinct()
            .collect(Collectors.toList());
    }

    @Override
    public Optional<String> getFirstModel(List<Person> personList, Predicate<Car> filter) {
        return personList
            .stream()
            .map(Person::getCar)
            .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
            .filter(filter)
            .map(Car::getModel)
            .distinct()
            .sorted()
            .findFirst();
    }

    @Override
    public Optional<Bean1> findMax(List<Bean1> bean1List) {
        return bean1List
            .stream()
            .max(Comparator.comparing(Bean1::getY));
    }
}
