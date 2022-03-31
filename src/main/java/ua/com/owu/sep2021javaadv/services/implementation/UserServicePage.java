package ua.com.owu.sep2021javaadv.services.implementation;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2021javaadv.controllers.MainController;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.services.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicePage implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServicePage.class);

    private UserDAO userDAO;

    @Override
    public List<UserDTO> findAllUserDTO() {
        logger.error("asigqwuytquyg!!!!!!!!!!!!!!");
        PageRequest request = PageRequest.of(1, 2);
        return userDAO.findAll(request).getContent().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
