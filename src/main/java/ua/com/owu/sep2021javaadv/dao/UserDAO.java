package ua.com.owu.sep2021javaadv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.sep2021javaadv.models.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findByAge(int age);

    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, int age);


}

