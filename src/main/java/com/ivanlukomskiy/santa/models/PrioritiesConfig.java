package com.ivanlukomskiy.santa.models;

import com.ivanlukomskiy.santa.dataSources.MessageContentProvider;
import com.ivanlukomskiy.santa.dataSources.PersonsProvider;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class PrioritiesConfig {
    private List<Priority> priorities;
}
