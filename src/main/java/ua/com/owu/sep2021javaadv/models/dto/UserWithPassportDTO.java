package ua.com.owu.sep2021javaadv.models.dto;

import lombok.Data;
import ua.com.owu.sep2021javaadv.models.entity.User;

@Data
public class UserWithPassportDTO {
    private String name;
    private int age;
    private PassportDTO passportDTO;

    public UserWithPassportDTO(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.passportDTO = new PassportDTO(user.getPassport());
    }
}
