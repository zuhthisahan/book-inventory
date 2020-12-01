package com.project.bookstore.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSender() {
            @Override
            public void send(SimpleMailMessage simpleMailMessage) throws MailException {

            }

            @Override
            public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

            }

            @Override
            public MimeMessage createMimeMessage() {
                return null;
            }

            @Override
            public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
                return null;
            }

            @Override
            public void send(MimeMessage mimeMessage) throws MailException {

            }

            @Override
            public void send(MimeMessage... mimeMessages) throws MailException {

            }

            @Override
            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

            }

            @Override
            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

            }
        };
    }
    public void sendMail(String toEmail,
                         String body,
                         String subject){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("zuhthisahan7@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);


        mailSender.send(message);
        System.out.println("Mail sent....");
    }
}
