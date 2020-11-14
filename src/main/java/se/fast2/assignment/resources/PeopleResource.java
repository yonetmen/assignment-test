package se.fast2.assignment.resources;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.fast2.assignment.models.Person;
import se.fast2.assignment.models.ResponseDto;
import se.fast2.assignment.services.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleResource {

    private final PeopleService service;

    public PeopleResource(PeopleService service) {
        this.service = service;
    }

    @Operation(description = "Hämtar en list med statistik över topp-förekomster av förnamn, sorterade.")
    @GetMapping("/most-appearance-firstname")
    public ResponseEntity<?> getTopPeopleGroupByFirstname() {
        List<ResponseDto> result = service.getAllGroupByFirstname();
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en list med statistik över topp-förekomster av efternamn, sorterade.")
    @GetMapping("/most-appearance-surname")
    public ResponseEntity<?> getTopPeopleGroupBySurname() {
        List<ResponseDto> result = service.getAllGroupBySurname();
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med samtliga förnamn, sorterade.")
    @GetMapping("/firstnames")
    public ResponseEntity<?> getAllOrderByFirstname() {
        List<String> result = service.getFirstnameListSorted();
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med samtliga förnamn. Går att välja sorteringsordning. Use 'asc' or 'desc'")
    @GetMapping("/firstnames/{sortOrder}")
    public ResponseEntity<?> getAllOrderByFirstname(@PathVariable("sortOrder") String sortOrder) {
        List<String> result = service.getFirstnameListSortedBy(sortOrder);
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med samtliga efternamn, sorterade.")
    @GetMapping("/surnames")
    public ResponseEntity<?> getAllOrderBySurname() {
        List<String> result = service.getSurnameListSorted();
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med samtliga efternamn. Går att välja sorteringsordning. Use 'asc' or 'desc'")
    @GetMapping("/surnames/{sortOrder}")
    public ResponseEntity<?> getAllOrderBySurname(@PathVariable("sortOrder") String sortOrder) {
        List<String> result = service.getSurnameListSortedBy(sortOrder);
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med filtrerade urvalet av namn.")
    @GetMapping("/{firstname}")
    public ResponseEntity<?> getPeopleByFirstname(@PathVariable("firstname") String firstname) {
        List<Person> result = service.getAllByFirstname(firstname);
        return buildResponse(result);
    }

    @Operation(description = "Hämtar en lista med enbart namn med udda eller jämnt antal förekomster. Use 'odd' or 'even'")
    @GetMapping("/most-appearance/{parity}")
    public ResponseEntity<?> getTopPeopleFilterByCount(@PathVariable("parity") String oddEven) {
        List<ResponseDto> result = service.getTopNamesFilterByParity(oddEven);
        return buildResponse(result);
    }

    private ResponseEntity<?> buildResponse(List<?> result) {
        if (result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Oops! You are either using invalid parameter or search criteria returned with no result.");
        return ResponseEntity.ok(result);
    }
}
