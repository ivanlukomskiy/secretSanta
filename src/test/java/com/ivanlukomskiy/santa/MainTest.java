package com.ivanlukomskiy.santa;

import com.ivanlukomskiy.santa.dataSources.MessageContentProvider;
import com.ivanlukomskiy.santa.dataSources.PersonsProvider;
import com.ivanlukomskiy.santa.dataSources.PrioritiesConfigProvider;
import com.ivanlukomskiy.santa.notifications.NotificationsService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public class MainTest {
    private static AnnotationConfigApplicationContext ctx;

    @BeforeClass
    public static void initTesting() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        Locale.setDefault(dLocale);
    }

    @Autowired
    private ApplicationContext appContext;

    @Before
    public void before() {
        personsProvider = (PersonsProvider) ctx.getBean(PersonsProvider.class);
        notificationsService = (NotificationsService) ctx.getBean(NotificationsService.class);
        prioritiesConfigProvider = (PrioritiesConfigProvider) ctx.getBean(PrioritiesConfigProvider.class);
        messageContentProvider = (MessageContentProvider) ctx.getBean(MessageContentProvider.class);
        jobExecutor = (JobExecutor) ctx.getBean(JobExecutor.class);
    }

    private NotificationsService notificationsService;
    private PersonsProvider personsProvider;
    private PrioritiesConfigProvider prioritiesConfigProvider;
    private MessageContentProvider messageContentProvider;
    private JobExecutor jobExecutor;

    @Test
    @SneakyThrows
    public void test() {
        Job job = Job.builder()
                .messageContentProvider(messageContentProvider)
                .notificationsService(notificationsService)
                .personsProvider(personsProvider)
                .prioritiesConfigProvider(prioritiesConfigProvider)
                .build();
        jobExecutor.execute(job);
    }
}