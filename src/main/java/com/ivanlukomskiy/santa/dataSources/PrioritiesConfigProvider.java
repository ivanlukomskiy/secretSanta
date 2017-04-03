package com.ivanlukomskiy.santa.dataSources;

import com.ivanlukomskiy.santa.models.PrioritiesConfig;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public interface PrioritiesConfigProvider {
    public PrioritiesConfig getPrioritiesConfig() throws Exception;
}
