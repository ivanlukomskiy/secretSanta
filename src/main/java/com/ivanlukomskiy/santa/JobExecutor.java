package com.ivanlukomskiy.santa;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public interface JobExecutor {
    public void execute(Job job) throws Exception;
}
