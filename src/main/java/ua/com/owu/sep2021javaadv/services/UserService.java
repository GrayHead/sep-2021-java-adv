package ua.com.owu.sep2021javaadv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.sep2021javaadv.dao.UserDAO;
import ua.com.owu.sep2021javaadv.models.dto.UserDTO;
import ua.com.owu.sep2021javaadv.models.entity.User;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private MailSendService mailSendService;

    public void saveUser(UserDTO dto) {
        userDAO.save(new User(dto));

    }

    public void saveUser(String name, MultipartFile avatar) throws IOException {
        userDAO.save(new User(name, avatar.getOriginalFilename()));
        String path = System.getProperty("user.home") + File.separator + "avatars" + File.separator;
        avatar.transferTo(new File(path + avatar.getOriginalFilename()));


    }

    public void saveUser(String name, String email, MultipartFile avatar) throws IOException, MessagingException {
        User user = new User(name, email, avatar.getOriginalFilename());
        String path = System.getProperty("user.home") + File.separator + "avatars" + File.separator;
        mailSendService.send(user, avatar);
        userDAO.save(user);
        avatar.transferTo(new File(path + avatar.getOriginalFilename()));
    }

    public String getUserAvatar(int id) {
        return userDAO.findById(id).get().getAvatar();

    }


}
