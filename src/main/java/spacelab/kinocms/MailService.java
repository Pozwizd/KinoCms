package spacelab.kinocms;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlEmail(String recipient, String templatePath) throws MessagingException, IOException {
        File template = new File(templatePath);

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("sender@example.com");
        helper.setTo(recipient);

        helper.setSubject("HTML Email");

        byte[] templateBytes;
        try (FileInputStream fis = new FileInputStream(template)) {
            templateBytes = fis.readAllBytes();
        }
        String htmlContent = new String(templateBytes, StandardCharsets.UTF_8);
        helper.setText(htmlContent, true);

        mailSender.send(message);

    }
}