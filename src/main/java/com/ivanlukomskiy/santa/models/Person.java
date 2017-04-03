package com.ivanlukomskiy.santa.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class Person {
    private Map<String,String> parameters = new HashMap<>();

    public String toString() {
        if(parameters!=null&&parameters.containsKey("name")) {
            return parameters.get("name");
        }
        return super.toString();
    }
}
