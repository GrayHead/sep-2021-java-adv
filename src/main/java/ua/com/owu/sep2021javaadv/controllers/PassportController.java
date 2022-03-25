package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2021javaadv.dao.PassportDAO;
import ua.com.owu.sep2021javaadv.models.dto.PassportDTO;
import ua.com.owu.sep2021javaadv.models.entity.Passport;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passports")
@AllArgsConstructor
public class PassportController {
    private PassportDAO passportDAO;

    @GetMapping("")
    public List<PassportDTO> getPassports() {
        return passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());

    }
//    todo CRUD + DTO

}
