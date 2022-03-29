package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2021javaadv.dao.CardDAO;
import ua.com.owu.sep2021javaadv.models.entity.Card;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {


    private CardDAO cardDAO;


    @PostMapping("")
    public void saveCardWithUser(@RequestBody Card card) {
        cardDAO.save(card);
    }

    @GetMapping("")
    public List<Card> getCards() {
        return cardDAO.findAll();
    }
}
