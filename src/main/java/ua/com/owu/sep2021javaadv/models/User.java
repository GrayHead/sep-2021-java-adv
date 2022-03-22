package ua.com.owu.sep2021javaadv.models;

import lombok.*;

import javax.persistence.*;

@Entity
//@Table(name = "usertable")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private int id;
    private String name;
    private int age;


}
