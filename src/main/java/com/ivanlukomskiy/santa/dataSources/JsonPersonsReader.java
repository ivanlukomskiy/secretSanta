package com.ivanlukomskiy.santa.dataSources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ivanlukomskiy.santa.models.Person;
import lombok.AllArgsConstructor;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@AllArgsConstructor
public class JsonPersonsReader implements PersonsProvider {
    private String jsonPath;
    private static final Type PERSONS_LIST_TYPE = new TypeToken<List<Person>>() {
    }.getType();

    public List<Person> loadPersons() throws Exception {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(jsonPath));
        return gson.fromJson(reader, PERSONS_LIST_TYPE);
    }
}
