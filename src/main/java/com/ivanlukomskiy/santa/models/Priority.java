package com.ivanlukomskiy.santa.models;

import lombok.Data;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class Priority {
    private String fieldName;
    private PriorityType priorityType;

    public enum PriorityType {
        PREFER_IF_DIFFERENT
    }
}
