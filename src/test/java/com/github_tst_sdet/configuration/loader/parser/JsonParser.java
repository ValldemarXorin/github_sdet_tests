package com.github_tst_sdet.configuration.loader.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonParser implements Parser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object parse(Object data) {
        try {
            return objectMapper.readValue((String) data, Object.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
