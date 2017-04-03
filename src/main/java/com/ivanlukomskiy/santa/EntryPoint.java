package com.ivanlukomskiy.santa;

import com.ivanlukomskiy.santa.notifications.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public class EntryPoint {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        
    }

    @Autowired
    private NotificationsService service;
}
