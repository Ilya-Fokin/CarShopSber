package com.example.CarShopSber.Deserializers;

import com.example.CarShopSber.Entities.CarType;
import com.example.CarShopSber.Entities.CarWheelDrive;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class CarTypeDeserializer extends JsonDeserializer<CarType> {
    @Override
    public CarType deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode textNode = parser.getCodec().readTree(parser);
        String value = textNode.textValue();

        for (CarType carType : CarType.values()) {
            if (carType.getType().equals(value)) {
                return carType;
            }
        }

        throw new IllegalArgumentException("Неизвестный тип привода: " + value);
    }
}
