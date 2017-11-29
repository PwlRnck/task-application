package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;



@Service
public class SimpleEmailService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    public static final String TRELLO = "Trello card email";
    public static final String REPORT = "Scheduled report email";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, String type) {
        LOGGER.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMailMessage(mail);
            MimeMessagePreparator mailMessage = createMimeMessage(mail,type);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending:", e.getMessage(),e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, String type) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            switch (type) {
                case TRELLO:
                    messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
                break;

                case REPORT:
                    messageHelper.setText(mailCreatorService.buildScheduledReportEmail(mail.getMessage()), true);
                break;
            }
        };
    }


    private SimpleMailMessage createMailMessage(final Mail mail) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
            if(mail.getToCc() != null) {
                mailMessage.setCc(mail.getToCc());
            }
            return mailMessage;
    }
}
