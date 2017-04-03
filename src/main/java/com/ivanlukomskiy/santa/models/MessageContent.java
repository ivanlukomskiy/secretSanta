package com.ivanlukomskiy.santa.models;

import lombok.Data;

import java.util.Map;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class MessageContent {
    private String messageText;
    private String topic;

    public String prepareFor(Person person) {
        String result = messageText;
        for(Map.Entry<String,String> row : person.getParameters().entrySet()) {
            result = result.replaceAll("%"+row.getKey(),row.getValue().replaceAll("[\n\r]","<br>"));
        }
        return result;
    }
}
