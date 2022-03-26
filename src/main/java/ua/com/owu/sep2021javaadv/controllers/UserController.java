package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
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
    public ResponseEntity<UserWithPassportDTO> findById(@PathVariable int id) {
        User user = userDAO.findById(id).orElse(new User());
        if (user.getId() == 0) {
            return new ResponseEntity<UserWithPassportDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserWithPassportDTO>(new UserWithPassportDTO(user), HttpStatus.OK);
    }


    @GetMapping("/findBy")
    public void findBy() {
        System.out.println(userDAO.findByAge(33));
        System.out.println(userDAO.findByName("lana"));
        System.out.println(userDAO.findByNameAndAge("lana", 32));
    }


    @PostMapping("")
    public ResponseEntity<List<UserDTO>> saveUser(@RequestBody User user) {
        userDAO.save(user);
        List<UserDTO> collect = userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<List<UserDTO>>(collect, HttpStatus.OK);
    }

    @PostMapping("/with-passport")
    public ResponseEntity<List<UserWithPassportDTO>> saveUserWithPassport(@RequestBody User user) {
        userDAO.save(user);
        List<UserWithPassportDTO> collect = userDAO.findAll().stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userDAO.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserWithPassportDTO>> deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
        List<UserWithPassportDTO> collect = userDAO.findAll().stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }


}
