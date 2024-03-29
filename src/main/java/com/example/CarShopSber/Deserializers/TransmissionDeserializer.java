package com.example.CarShopSber.Deserializers;

import com.example.CarShopSber.Entities.Transmission;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

/**
 * Десериализатор для преобразования строкового значения в enum {@link Transmission}.
 */
public class TransmissionDeserializer extends JsonDeserializer<Transmission> {

    /**
     * Десериализует строковое значение в соответствующий enum {@link Transmission}.
     *
     * @param parser  Парсер JSON.
     * @param context Контекст десериализации.
     * @return Экземпляр enum {@link Transmission}, соответствующий строковому значению.
     * @throws IOException Если возникает ошибка ввода/вывода при десериализации.
     */
    @Override
    public Transmission deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        TextNode textNode = parser.getCodec().readTree(parser);
        String value = textNode.textValue();

        for (Transmission transmission : Transmission.values()) {
            if (transmission.getTransmission().equals(value)) {
                return transmission;
            }
        }

        throw new IllegalArgumentException("Unknown transmission: " + value);
    }
}