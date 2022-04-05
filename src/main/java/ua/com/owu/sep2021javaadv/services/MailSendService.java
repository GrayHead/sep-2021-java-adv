package ua.com.owu.sep2021javaadv.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.sep2021javaadv.models.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
public class MailSendService {
    private JavaMailSender javaMailSender;

    public void send(User user, MultipartFile multipartFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessage.setFrom(new InternetAddress("zoopazoo12@gmail.com"));
            helper.setTo(user.getEmail());
            helper.setText("<h1>message</h1>", true);

            String fileFormat = multipartFile.getContentType().split("/")[1];
            helper.addAttachment("somefilename." + fileFormat, multipartFile);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }


}
