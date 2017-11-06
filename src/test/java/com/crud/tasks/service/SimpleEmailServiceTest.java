package com.crud.tasks.service;

import com.crud.tasks.config.Profiles;
import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(Profiles.LOCAL)
public class SimpleEmailServiceTest {

        @InjectMocks
        private SimpleEmailService simpleEmailService;

        @Mock
        private JavaMailSender javaMailSender;

        @Test
        public void shouldSendEmail() {
            //Given
            Mail mail = new Mail("test@test.com", null, "Test", "Test message");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
            //When
            simpleEmailService.send(mail);
            //Then
            Mockito.verify(javaMailSender,times(1)).send(mailMessage);
        }
}