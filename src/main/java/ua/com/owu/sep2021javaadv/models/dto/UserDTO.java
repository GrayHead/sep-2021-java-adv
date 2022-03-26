package ua.com.owu.sep2021javaadv.models.dto;

import lombok.Data;
import ua.com.owu.sep2021javaadv.models.entity.User;

@Data
public class UserDTO {
    private String name;
    private int age;

    public UserDTO(User user) {
        this.name = user.getName();
        this.age = user.getAge();
    }
}
