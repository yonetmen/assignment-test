package se.fast2.assignment.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import se.fast2.assignment.models.People;
import se.fast2.assignment.models.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class JsonParser {

    public List<Person> parse(String filename) {
        try {
            Resource resource = new ClassPathResource(filename);
            InputStream input = resource.getInputStream();
            People people = new ObjectMapper().readValue(input, People.class);
            return people.getPeople();
        } catch (IOException e) {
            log.error("Error while parsing json file. File name: " + filename);
            throw new RuntimeException(e);
        }
    }
}
