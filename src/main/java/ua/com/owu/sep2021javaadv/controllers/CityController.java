package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2021javaadv.dao.CityDAO;
import ua.com.owu.sep2021javaadv.models.entity.City;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    private CityDAO cityDAO;

    @PostMapping("")
    public void saveCityWithUser(@RequestBody City city) {
        cityDAO.save(city);
    }

}
