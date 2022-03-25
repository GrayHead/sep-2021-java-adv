package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.dto.UserWithPassportDTO;
import ua.com.owu.sep2021javaadv.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    private UserDAO userDAO;


    @GetMapping("")
    public ResponseEntity<List<UserWithPassportDTO>> findAll() {
        List<User> allUsers = userDAO.findAll();
        List<UserWithPassportDTO> userWithPassportDTOS = allUsers.stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
        ResponseEntity<List<UserWithPassportDTO>> response = new ResponseEntity<>(userWithPassportDTOS, HttpStatus.OK);
        return response;

    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        User user = userDAO.findById(id).orElse(new User());
        if (user.getId() == 0) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping("/findBy")
    public void findBy() {
        System.out.println(userDAO.findByAge(33));
        System.out.println(userDAO.findByName("lana"));
        System.out.println(userDAO.findByNameAndAge("lana", 32));

    }


    @PostMapping("")
    public List<User> saveUser(@RequestBody User user) {
        userDAO.save(user);
        return userDAO.findAll();
    }

    @PostMapping("/with-passport")
    public List<UserWithPassportDTO> saveUserWithPassport(@RequestBody User user) {
        userDAO.save(user);
        return userDAO.findAll().stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userDAO.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
        return userDAO.findAll();
    }


}
