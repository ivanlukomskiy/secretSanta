package com.ivanlukomskiy.santa.models;

import lombok.Data;

import java.util.Map;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
public class Person {
    private String name;
    private String email;
    private Gender gender;
    private Integer group;
    private Map<String,String> parameters;

    public enum Gender {MALE, FEMALE}
}
