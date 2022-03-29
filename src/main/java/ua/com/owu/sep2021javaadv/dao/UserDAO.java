package ua.com.owu.sep2021javaadv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.owu.sep2021javaadv.models.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findByAge(int age);

    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, int age);

    @Query("select u from User u join fetch u.cities")
        // join
    List<User> customQueryUsersWithFetchCities();

    @Query("select u from User u join fetch u.cities  where u.name=:name")
    List<User> customQueryUsersByNameWithFetchCities(/*@Param("name") */String name);

    @Query(nativeQuery = true, value = "select * from user where user.name =?1 and user.age=?2")
    List<User> nativeUsersQuery(String name,int age);

}

