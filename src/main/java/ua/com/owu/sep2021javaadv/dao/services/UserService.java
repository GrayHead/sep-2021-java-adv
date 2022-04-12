package ua.com.owu.sep2021javaadv.dao.services;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.entity.User;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }


    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    public User findUserByName(String name) {
        return userDAO.findByUsername(name);
    }

}
