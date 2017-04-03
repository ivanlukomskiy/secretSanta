package com.ivanlukomskiy.santa;

import com.ivanlukomskiy.santa.dataSources.MessageContentProvider;
import com.ivanlukomskiy.santa.dataSources.PersonsProvider;
import com.ivanlukomskiy.santa.dataSources.PrioritiesConfigProvider;
import com.ivanlukomskiy.santa.notifications.NotificationsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@AllArgsConstructor
@Builder
@Data
public class Job {
    private NotificationsService notificationsService;
    private PersonsProvider personsProvider;
    private PrioritiesConfigProvider prioritiesConfigProvider;
    private MessageContentProvider messageContentProvider;
}
