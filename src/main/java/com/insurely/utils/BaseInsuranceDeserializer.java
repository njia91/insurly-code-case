package com.insurely.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurely.domain.insurance.*;

import java.io.IOException;

public class BaseInsuranceDeserializer extends JsonDeserializer<BaseInsurance> {

    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public BaseInsurance deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        String insuranceType = node.get("insuranceSubType").asText();

        switch (insuranceType) {
            case "carInsurance":
                return OBJECT_MAPPER.readValue(node.toString(), CarInsurance.class);
            case "condoInsurance":
                return OBJECT_MAPPER.readValue(node.toString(), CondoInsurance.class);
            case "cottageInsurance":
                return OBJECT_MAPPER.readValue(node.toString(), CottageInsurance.class);
            case "mopedInsurance":
                return OBJECT_MAPPER.readValue(node.toString(), MopedInsurance.class);
            default:
                throw new RuntimeException("Unknown insurance type: " + insuranceType);
        }
    }
}
