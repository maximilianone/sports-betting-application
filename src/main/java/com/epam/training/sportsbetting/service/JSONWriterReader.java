package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class JSONWriterReader {
    public <T> List<T> read(String filepath, Class<T[]> clazz) throws FileNotFoundException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
                    return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), formatter);
                }).create();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        T[] arr = gson.fromJson(jsonReader, clazz);
        return Arrays.asList(arr);
    }
}
