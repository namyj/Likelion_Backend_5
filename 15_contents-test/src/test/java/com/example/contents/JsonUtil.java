package com.example.contents;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mappaer = new ObjectMapper();
        mappaer.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mappaer.writeValueAsBytes(object);
    }
}
