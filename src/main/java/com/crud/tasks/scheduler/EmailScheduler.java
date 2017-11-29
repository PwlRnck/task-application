package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(fixedDelay = 3600000)//(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String tasksNumber;
        switch ((int)size) {
            case 0: tasksNumber = "no tasks";
            break;
            case 1: tasksNumber = "1 task";
            break;
            default: tasksNumber = size + " tasks";
        }
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "Currently you have got: " + tasksNumber + " in your database."
        ), SimpleEmailService.REPORT);
    }
}
