package se.fast2.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.fast2.assignment.models.Person;
import se.fast2.assignment.models.ResponseDto;
import se.fast2.assignment.services.PeopleService;
import se.fast2.assignment.services.QueryService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApplicationTests {

    private final PeopleService peopleService;
    private final QueryService queryService;

    @Autowired
    public ApplicationTests(PeopleService peopleService) {
        this.peopleService = peopleService;
        List<Person> source = getTestData();
        queryService = new QueryService(source);
    }

    @Test
    public void findAllGroupByFirstnameTest() {
        Map<String, List<Person>> resultMap = queryService.findAllGroupByFirstname();
        List<ResponseDto> dtoList = peopleService.mapToResponseList(resultMap);

        ResponseDto expected = new ResponseDto("Robert", 3);
        assertEquals(expected, dtoList.get(0));
    }

    @Test
    public void findAllGroupBySurnameTest() {
        Map<String, List<Person>> resultMap = queryService.findAllGroupBySurname();
        List<ResponseDto> dtoList = peopleService.mapToResponseList(resultMap);

        ResponseDto expected = new ResponseDto("Adams", 3);
        assertEquals(expected, dtoList.get(0));
    }

    @Test
    public void findAllFirstnamesSortedTest() {
        List<String> firstnameList = queryService.findAllFirstnamesSorted();

        int lastIdx = firstnameList.size() - 1;
        assertEquals("David", firstnameList.get(0));
        assertEquals("Scott", firstnameList.get(lastIdx));
    }

    @Test
    public void getFirstnameListSortedByDescTest() {
        String order = "DESC";
        List<String> firstnameList = queryService.findAllFirstnamesSorted();
        peopleService.orderQueryList(firstnameList, order);

        int lastIdx = firstnameList.size() - 1;
        assertEquals("Scott", firstnameList.get(0));
        assertEquals("David", firstnameList.get(lastIdx));
    }

    @Test
    public void findAllSurnamesSortedTest() {
        List<String> surnameList = queryService.findAllSurnamesSorted();

        int lastIdx = surnameList.size() - 1;
        assertEquals("Adams", surnameList.get(0));
        assertEquals("Hudson", surnameList.get(lastIdx));
    }

    @Test
    public void getSurnameListSortedByDescTest() {
        String order = "DESC";
        List<String> surnameList = queryService.findAllSurnamesSorted();
        peopleService.orderQueryList(surnameList, order);

        int lastIdx = surnameList.size() - 1;
        assertEquals("Hudson", surnameList.get(0));
        assertEquals("Adams", surnameList.get(lastIdx));
    }

    @Test
    public void findAllByFirstnameTest() {
        String firstname = "Robert";
        List<Person> personList = queryService.findAllByFirstname(firstname);

        assertEquals(3, personList.size());
        personList.forEach(p -> assertEquals(firstname, p.getFirstname()));
    }

    @Test
    public void getTopNamesFilterByParityTest() {
        Map<String, List<Person>> resultMap = queryService.findAllGroupByFirstname();
        List<ResponseDto> dtoList = peopleService.mapToResponseList(resultMap);
        List<ResponseDto> resultList = peopleService.filterByParity(dtoList, "odd");

        assertEquals(1, resultList.size());
        assertEquals("Robert", resultList.get(0).getValue());

        resultList = peopleService.filterByParity(dtoList, "even");

        assertEquals(1, resultList.size());
        assertEquals("Scott", resultList.get(0).getValue());
    }


    private List<Person> getTestData() {
        return Arrays.asList(
                new Person("Jane", "Adams", "female"),
                new Person("Scott", "Adams", "male"),
                new Person("Robert", "Adams", "male"),
                new Person("Robert", "Foster", "male"),
                new Person("Robert", "Hudson", "male"),
                new Person("Scott", "Foster", "male"),
                new Person("David", "Evans", "male"));
    }
}
