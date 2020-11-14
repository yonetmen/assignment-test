# Instructions to use API
## This Project using OpenApi 3.0 Specifications for API documentation

## How to Run
`./mvnw spring-boot:run`

###### Or, if you downloaded the jar file
`java -jar <file_name>.jar`

## Swagger UI
`localhost:8080/api/swagger`

## API Endpoints for Browser & Api Clients like Postman

###### Skriver ut en list med statistik över topp-förekomster av förnamn, sorterade.
`localhost:8080/api/people/most-appearance-firstname`

###### Skriver ut en list med statistik över topp-förekomster av efternamn, sorterade.
`localhost:8080/api/people/most-appearance-surname`

###### Hämtar en lista med samtliga förnamn, sorterade.
`localhost:8080/api/people/firstnames`

###### Hämtar en lista med samtliga förnamn. Går att välja sorteringsordning
`localhost:8080/api/people/firstnames/desc`

`localhost:8080/api/people/firstnames/asc`

###### Hämtar en lista med samtliga efernamn, sorterade.
`localhost:8080/api/people/surnames`

###### Hämtar en lista med samtliga efernamn. Går att välja sorteringsordning
`localhost:8080/api/people/surnames/desc`

`localhost:8080/api/people/surnames/asc`

###### Hämtar en lista med filtrerade urvalet av namn
`localhost:8080/api/people/{firstname}`

###### Hämtar en lista med enbart namn med udda eller jämnt antal förekomster.
`localhost:8080/api/people/most-appearance/odd`

`localhost:8080/api/people/most-appearance/even`
