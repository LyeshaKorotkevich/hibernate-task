package util;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.entity.Sex;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class PersonTestData {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private UUID uuid = UUID.fromString("c3d3bcb6-cf4e-4f77-b021-2265969160f4");

    @Builder.Default
    private String name = "Lyesho";

    @Builder.Default
    private String surname = "Korotkevich";

    @Builder.Default
    private Sex sex = Sex.MALE;

    @Builder.Default
    private Passport passport = PassportTestData.builder()
            .build()
            .getPassport();

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.MIN;

    @Builder.Default
    private LocalDateTime updateDate = LocalDateTime.MIN;

    @Builder.Default
    private House livingHouse = HouseTestData.builder()
            .build()
            .getHouse();

    @Builder.Default
    private List<House> owningHouse = List.of();

    public Person getPerson() {
        return new Person(id, uuid, name, surname, sex, passport, createDate, updateDate, livingHouse, owningHouse);
    }

    public PersonRequest getRequest() {
        return new PersonRequest(name, surname, sex, passport, owningHouse);
    }

    public PersonResponse getResponse() {
        return new PersonResponse(uuid, name, surname, sex, passport, createDate, updateDate);
    }

    public List<Person> getListOfPeople() {
        return List.of(PersonTestData.builder()
                .build()
                .getPerson()
        );
    }
}
