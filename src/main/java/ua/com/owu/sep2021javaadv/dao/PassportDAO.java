package ua.com.owu.sep2021javaadv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2021javaadv.models.entity.Passport;

public interface PassportDAO extends JpaRepository<Passport, Integer> {
}
