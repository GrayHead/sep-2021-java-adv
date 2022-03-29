package ua.com.owu.sep2021javaadv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2021javaadv.models.entity.Card;

public interface CardDAO extends JpaRepository<Card, Integer> {
}
