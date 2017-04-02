package com.ivanlukomskiy.santa.dataSources;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ivanlukomskiy.santa.models.MessageContent;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@AllArgsConstructor
public class PlainTextFileMessageReader implements MessageContentProvider {
    private String messageFilePath;
    public static final String TOPIC_PATTERN = "(?i)^topic:\\s*\"(.*)\"\\s*$";

    public MessageContent getMessageContent() throws Exception {
        MessageContent content = new MessageContent();
        String text = Files.toString(new File(messageFilePath), Charsets.UTF_8);
        Matcher m = Pattern.compile(TOPIC_PATTERN).matcher(text);
        if(m.find()) {
            content.setTopic(m.group(1));
            content.setMessageText(text.replace(TOPIC_PATTERN,""));
        } else {
            content.setTopic("Secret Santa");
            content.setMessageText(text);
        }
        return content;
    }
}
