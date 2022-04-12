package ua.com.owu.sep2021javaadv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2021javaadv.models.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
