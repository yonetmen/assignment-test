package se.fast2.assignment.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
    private String value;
    private Integer count;
}
