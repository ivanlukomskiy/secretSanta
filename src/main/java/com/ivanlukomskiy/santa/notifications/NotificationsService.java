package com.ivanlukomskiy.santa.notifications;

import com.ivanlukomskiy.santa.models.MessageContent;
import com.ivanlukomskiy.santa.models.Person;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public interface NotificationsService {
    public void sendNotifications(List<Person> persons, MessageContent content) throws Exception;
}
