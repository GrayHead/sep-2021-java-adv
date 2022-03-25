package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2021javaadv.dao.PassportDAO;
import ua.com.owu.sep2021javaadv.models.entity.Passport;

import java.util.List;

@RestController
@RequestMapping("/passports")
@AllArgsConstructor
public class PassportController {
    private PassportDAO passportDAO;

    @GetMapping("")
    public List<Passport> getPassports() {
        return passportDAO.findAll();

    }
}
