package com.ivanlukomskiy.santa.models;

import com.ivanlukomskiy.santa.dataSources.MessageContentProvider;
import com.ivanlukomskiy.santa.dataSources.PersonsProvider;
import lombok.Data;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class Job {
    private boolean genderMatching;
    private boolean groupMatching;
    private PersonsProvider personsProvider;
    private MessageContentProvider messageContentProvider;
}
