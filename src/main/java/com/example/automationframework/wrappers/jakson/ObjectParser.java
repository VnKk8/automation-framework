package com.example.automationframework.wrappers.jakson;

import com.example.automationframework.wrappers.MapperConfigurator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ObjectParser {

    public static <T> T jsonStringToJavaObject(String jsonString, Class<T> classType) throws JsonProcessingException {
        return MapperConfigurator.getJavaObjectMapper().readValue(jsonString, classType);
    }

    public static String javaObjectToFormattedJsonString(Object object) {
        ObjectWriter objectWriter = MapperConfigurator.getJavaObjectMapper().writerWithDefaultPrettyPrinter();
        try {
            JsonNode jsonNode = jsonStringToJsonNode(object.toString());
            return objectWriter.writeValueAsString(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode jsonStringToJsonNode(String jsonString) throws JsonProcessingException {
        return MapperConfigurator.getJavaObjectMapper().readTree(jsonString);
    }

    public static String javaObjectToJsonString(Object object) throws JsonProcessingException {
        return MapperConfigurator.getJavaObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
