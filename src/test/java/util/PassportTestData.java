package util;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.house.entity.Passport;

@Data
@Builder(setterPrefix = "with")
public class PassportTestData {

    @Builder.Default
    String series = "abcsfsf";

    @Builder.Default
    String number = "12324";

    public Passport getPassport(){
        return new Passport(series, number);
    }
}
