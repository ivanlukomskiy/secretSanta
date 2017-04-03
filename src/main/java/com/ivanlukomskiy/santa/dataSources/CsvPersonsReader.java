package com.ivanlukomskiy.santa.dataSources;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import com.ivanlukomskiy.santa.models.Person;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.*;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 03.04.2017.
 */
@AllArgsConstructor
public class CsvPersonsReader implements PersonsProvider {
    public static final String SEPARATOR = "~";
    public static final String[] COL_NAMES = {"name", "surname", "sex", "local", "address", "postal", "email", "about"};
    private String scvFilePath;

    @Override
    public List<Person> loadPersons() throws Exception {
        String text = Files.toString(new File(scvFilePath), Charsets.UTF_8);
        List<String> parsed = Arrays.asList(text.split(SEPARATOR));
        List<Person> person = new LinkedList<>();

        Iterator<String> i = parsed.iterator();
        Person currentPerson = new Person();
        int colIndex = 0;
        while (i.hasNext()) {
            String value = i.next().trim();
            if(value.isEmpty()) {
                continue;
            }
            currentPerson.getParameters().put(COL_NAMES[colIndex], value);
            colIndex++;
            if(colIndex == COL_NAMES.length) {
                colIndex = 0;
                person.add(currentPerson);
                //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(currentPerson));
                currentPerson = new Person();
            }
        }

        return person;
    }
}
