package com.example.CarShopSber.Deserializers;

import com.example.CarShopSber.Entities.CarWheelDrive;
import com.example.CarShopSber.Entities.Transmission;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class CarWheelDriveDeserializer extends JsonDeserializer<CarWheelDrive> {

    @Override
    public CarWheelDrive deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode textNode = parser.getCodec().readTree(parser);
        String value = textNode.textValue();

        for (CarWheelDrive carWheelDrive : CarWheelDrive.values()) {
            if (carWheelDrive.getWheelDrive().equals(value)) {
                return carWheelDrive;
            }
        }

        throw new IllegalArgumentException("Неизвестный тип привода: " + value);
    }
}
