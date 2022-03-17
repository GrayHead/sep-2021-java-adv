package ua.com.owu.sep2021javaadv.controllers;

import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2021javaadv.models.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//localhost:8080/ -> 404
@RestController
public class MainController {

    //CRUD

    private List<User> users = new ArrayList<>();

    public MainController() {
        users.add(new User(2, "kokos", true));
        users.add(new User(1, "ananas", true));
        users.add(new User(3, "banan", false));
        users.add(new User(5, "tomat", false));
        users.add(new User(4, "potatos", true));
        users.add(new User(6, "mango", false));

    }


    @GetMapping("/") //localhost:8080/users
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/users") //localhost:8080/users
    public List<User> users() {
        return users;
    }

    //localhost:8080/users/100500
    @GetMapping("/users/{id}")
    public User user(@PathVariable("id") int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @GetMapping("/user")
    public User getUserByParams(@RequestParam("userId") int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }


    @PostMapping("/user")
    public List<User> saveUser(@RequestBody User user) {
        System.out.println(user);
        if (user != null) {
            users.add(user);

        } else {
            throw new RuntimeException("no user");
        }

        return users;


    }

    @PutMapping("/users/{id}")
    public List<User> putUser(@PathVariable int id, @RequestBody User user) {
        User findUser = users.stream().filter(user1 -> user1.getId() == id).findFirst().get();
        int indexOf = users.indexOf(findUser);
        users.set(indexOf, user);
        return users;
    }


    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        users.removeIf(next -> next.getId() == id);

        return users;

    }

}
