package ua.com.owu.sep2021javaadv.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.services.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements IUserService {
    private UserDAO userDAO;

    @Override
    public List<UserDTO> findAllUserDTO() {

        log.error("My Custom log about error");
        return userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
