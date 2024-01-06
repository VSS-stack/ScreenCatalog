package com.example.screencatalog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* Class used to convert json strings into an object */
public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper(); //object used to transform strings into objects

    /* Convert string into a given object type */
    @Override
    public <T> T getData(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType); //read the json and transform into an object
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
