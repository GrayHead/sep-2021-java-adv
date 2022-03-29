package ua.com.owu.sep2021javaadv.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String series;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;


}
