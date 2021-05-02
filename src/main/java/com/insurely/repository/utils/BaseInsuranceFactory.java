package com.insurely.repository.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurely.domain.insurance.BaseInsurance;

import java.util.List;
import java.util.stream.Collectors;

public class BaseInsuranceFactory {

    private final ObjectMapper objectMapper;

    public BaseInsuranceFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<BaseInsurance> createPOJOsFromJson(List<String> jsonStrings) {
        return jsonStrings.stream()
                .map(this::createPOJO)
                .collect(Collectors.toList());
    }

    private BaseInsurance createPOJO(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, BaseInsurance.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("unable to create POJO from json string: " +  jsonString);
        }
    }

}
