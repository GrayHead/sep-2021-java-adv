package ua.com.owu.sep2021javaadv.models.dto;

import lombok.Data;
import ua.com.owu.sep2021javaadv.models.entity.Passport;

@Data
public class PassportDTO {
    private String series;

    public PassportDTO(Passport passport) {
        this.series = passport.getSeries();
    }
}
