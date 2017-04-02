package com.ivanlukomskiy.santa.dataSources;

import com.ivanlukomskiy.santa.models.Person;

import java.util.List;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public interface PersonsProvider {
    public List<Person> loadPersons() throws Exception;
}
