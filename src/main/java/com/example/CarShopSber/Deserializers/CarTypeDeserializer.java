package com.example.CarShopSber.Deserializers;

import com.example.CarShopSber.Entities.CarType;
import com.example.CarShopSber.Entities.CarWheelDrive;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

/**
 * Десериализатор для преобразования строкового значения в enum {@link CarType}.
 */
public class CarTypeDeserializer extends JsonDeserializer<CarType> {
    /**
     * Десериализует строковое значение в соответствующий enum {@link CarType}.
     *
     * @param parser                Парсер JSON.
     * @param deserializationContext Контекст десериализации.
     * @return Экземпляр enum {@link CarType}, соответствующий строковому значению.
     * @throws IOException Если возникает ошибка ввода/вывода при десериализации.
     */
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
