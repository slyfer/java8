package ch.wideside;

import ch.wideside.model.Bean1;
import ch.wideside.model.Car;
import ch.wideside.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public interface Service {

    List<String> getAllModelsWithFilter(List<Person> personList, Predicate<Car> carPredicate);

    default List<String> getAllModelsNotGreen(List<Person> personList) {
        return getAllModelsWithFilter(personList, car -> !car.getColor().equalsIgnoreCase("green"));
    }

    default List<String> getAllModels(List<Person> personList) {
        return getAllModelsWithFilter(personList, car -> true);
    }

    List<String> getAllDistinctModels(List<Person> personList);

    Optional<String> getFirstModel(List<Person> personList, Predicate<Car> filter);

    Optional<Bean1> findMax(List<Bean1> bean1List);
}
