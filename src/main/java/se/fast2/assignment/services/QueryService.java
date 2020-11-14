package se.fast2.assignment.services;

import se.fast2.assignment.models.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryService {

    private final List<Person> source;

    public QueryService(List<Person> source) {
        this.source = source;
    }

    public Map<String, List<Person>> findAllGroupByFirstname() {
        return source.stream()
                .sorted(Comparator.comparing(Person::getFirstname))
                .collect(Collectors.groupingBy(Person::getFirstname));
    }

    public Map<String, List<Person>> findAllGroupBySurname() {
        return source.stream()
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.groupingBy(Person::getSurname));
    }

    public List<String> findAllFirstnamesSorted() {
        return source.stream()
                .sorted(Comparator.comparing(Person::getFirstname))
                .map(Person::getFirstname)
                .collect(Collectors.toList());
    }

    public List<String> findAllSurnamesSorted() {
        return source.stream()
                .sorted(Comparator.comparing(Person::getSurname))
                .map(Person::getSurname)
                .collect(Collectors.toList());
    }

    public List<Person> findAllByFirstname(String firstname) {
        return source.stream()
                .filter(person -> firstname.equalsIgnoreCase(person.getFirstname()))
                .collect(Collectors.toList());
    }
}
