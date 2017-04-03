package com.ivanlukomskiy.santa;

import com.ivanlukomskiy.santa.dataSources.*;
import com.ivanlukomskiy.santa.notifications.EmailNotificationsService;
import com.ivanlukomskiy.santa.notifications.LogsService;
import com.ivanlukomskiy.santa.notifications.NotificationsService;
import lombok.Builder;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public PersonsProvider getTestPersonsProvider() {
        return new CsvPersonsReader("testResources/me.txt");
    }

    @Bean
    public MessageContentProvider getTestMessageProvider() {
        return new PlainTextFileMessageReader("testResources/messageIra.html");
    }

    @Bean
    public PrioritiesConfigProvider getPrioritiesConfigProvider() {
        return new JsonPrioritiesConfigReader("testResources/prioritiesIra.json");
    }

    @Bean
    @SneakyThrows
    public NotificationsService getTestNotificationsService() {
        EmailNotificationsService service = new EmailNotificationsService("testResources"+ File.separator+"email.properties");
        service.init();
        return service;
    }

    @Bean
    public JobExecutor getJobExecutor() {return new JobExecutorPrimitive();}
}
