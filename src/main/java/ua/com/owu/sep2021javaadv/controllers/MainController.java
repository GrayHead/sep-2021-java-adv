package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2021javaadv.dao.services.UserService;
import ua.com.owu.sep2021javaadv.models.entity.User;

@RestController
@AllArgsConstructor
public class MainController {

    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/securityURL")
    public String helloSecurity() {
        return "hello security";
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }
}
