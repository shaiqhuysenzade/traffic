package com.traffic.dna.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String beautify(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            return null;
        }
    }
}
