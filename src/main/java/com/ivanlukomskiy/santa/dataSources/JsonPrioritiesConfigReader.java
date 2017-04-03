package com.ivanlukomskiy.santa.dataSources;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ivanlukomskiy.santa.models.PrioritiesConfig;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileReader;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
@AllArgsConstructor
public class JsonPrioritiesConfigReader implements PrioritiesConfigProvider {
    private String jsonPath;
    public PrioritiesConfig getPrioritiesConfig() throws Exception {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(jsonPath));
        return gson.fromJson(reader, PrioritiesConfig.class);
    }
}
