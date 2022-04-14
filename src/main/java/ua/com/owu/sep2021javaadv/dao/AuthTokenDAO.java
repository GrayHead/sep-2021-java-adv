package ua.com.owu.sep2021javaadv.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2021javaadv.models.entity.AuthToken;

public interface AuthTokenDAO extends JpaRepository<AuthToken, Integer> {
    AuthToken findAuthTokenByToken(String token);
}
