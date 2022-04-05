package ua.com.owu.sep2021javaadv.models.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String avatar;
    private String email;

    public User(UserDTO dto) {
        this.name = dto.getName();
        this.avatar = dto.getAvatar();
        this.email = dto.getEmail();
    }

    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public User(String name, String email, String avatar) {
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }
}
