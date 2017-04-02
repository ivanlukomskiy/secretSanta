package com.ivanlukomskiy.santa.dataSources;

import com.ivanlukomskiy.santa.models.MessageContent;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public interface MessageContentProvider {
    public MessageContent getMessageContent() throws Exception;
}
