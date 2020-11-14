package se.fast2.assignment.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class People {
    private List<Person> people;
}
