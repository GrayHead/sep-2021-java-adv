package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.models.entity.User;
import ua.com.owu.sep2021javaadv.services.UserService;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("")
    public void saveUser(@RequestParam String name, @RequestParam String email, @RequestParam MultipartFile avatar) throws IOException, MessagingException {
        System.out.println(name + " " + email);
        System.out.println(avatar);
        userService.saveUser(name, email, avatar);
    }


}
