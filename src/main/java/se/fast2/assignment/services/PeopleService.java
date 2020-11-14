package se.fast2.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.fast2.assignment.models.Person;
import se.fast2.assignment.models.ResponseDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    private final QueryService queryService;

    @Autowired
    public PeopleService(JsonParser jsonParser) {
        List<Person> people = jsonParser.parse("names.json");
        queryService = new QueryService(people);
    }

    public List<ResponseDto> getAllGroupByFirstname() {
        Map<String, List<Person>> result = queryService.findAllGroupByFirstname();
        return mapToResponseList(result);
    }

    public List<ResponseDto> getAllGroupBySurname() {
        Map<String, List<Person>> result = queryService.findAllGroupBySurname();
        return mapToResponseList(result);
    }

    // Filtering out people with unique names to produce minimal response
    public List<ResponseDto> mapToResponseList(Map<String, List<Person>> map) {
        List<ResponseDto> list = new ArrayList<>();
        for (Map.Entry<String, List<Person>> entry : map.entrySet())
            if (entry.getValue().size() > 1)
                list.add(new ResponseDto(entry.getKey(), entry.getValue().size()));
        list.sort(Comparator.comparing(ResponseDto::getCount).reversed());
        return list;
    }

    public List<String> getFirstnameListSorted() {
        return queryService.findAllFirstnamesSorted();
    }

    public List<String> getSurnameListSorted() {
        return queryService.findAllSurnamesSorted();
    }

    public List<String> getFirstnameListSortedBy(String order) {
        List<String> result = queryService.findAllFirstnamesSorted();
        orderQueryList(result, order);
        return result;
    }

    public List<String> getSurnameListSortedBy(String order) {
        List<String> result = queryService.findAllSurnamesSorted();
        orderQueryList(result, order);
        return result;
    }

    public void orderQueryList(List<String> sortedList, String sortOrder) {
        if ("desc".equalsIgnoreCase(sortOrder)) {
            Collections.reverse(sortedList);
        }
    }

    public List<Person> getAllByFirstname(String firstname) {
        return queryService.findAllByFirstname(firstname);
    }

    public List<ResponseDto> getTopNamesFilterByParity(String parity) {
        Map<String, List<Person>> result = queryService.findAllGroupByFirstname();
        List<ResponseDto> mappedList = mapToResponseList(result);
        return filterByParity(mappedList, parity);
    }

    public List<ResponseDto> filterByParity(List<ResponseDto> dtoList, String parity) {
        List<ResponseDto> filtered = new ArrayList<>();
        if ("odd".equalsIgnoreCase(parity)) {
            filtered = dtoList.stream()
                    .filter(p -> p.getCount() % 2 != 0)
                    .collect(Collectors.toList());
        } else if ("even".equalsIgnoreCase(parity)) {
            filtered = dtoList.stream()
                    .filter(p -> p.getCount() % 2 == 0)
                    .collect(Collectors.toList());
        }
        return filtered;
    }

}
