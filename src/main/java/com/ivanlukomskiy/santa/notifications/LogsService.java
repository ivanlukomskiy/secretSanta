package com.ivanlukomskiy.santa.notifications;

import com.ivanlukomskiy.santa.models.MessageContent;
import com.ivanlukomskiy.santa.models.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Slf4j
public class LogsService implements NotificationsService {
    @Override
    public void sendNotifications(List<Person> persons, MessageContent content) throws Exception {
        persons.forEach(person-> System.out.println(person));
    }
}