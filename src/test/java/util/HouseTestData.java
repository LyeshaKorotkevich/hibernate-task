package util;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class HouseTestData {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private UUID uuid = UUID.fromString("2e68c1b2-3d9e-44b3-8103-3c32d9f18ec4");

    @Builder.Default
    private String area = "area";

    @Builder.Default
    private String country = "country";

    @Builder.Default
    private String city = "city";

    @Builder.Default
    private String street = "street";

    @Builder.Default
    private Integer number = 10;

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.MAX;

    @Builder.Default
    private List<Person> residents = List.of();

    @Builder.Default
    private List<Person> owners = List.of();

    public House getHouse() {
        return new House(id, uuid, area, country, city, street, number, createDate, residents, owners);
    }

    public HouseRequest getRequest() {
        return new HouseRequest(area, country, city, street, number, residents, owners);
    }

    public HouseResponse getResponse() {
        return new HouseResponse(uuid, area, country, city, street, number, createDate);
    }

    public List<House> getListOfHouses() {
        return  List.of(HouseTestData.builder().build().getHouse());
    }
}
