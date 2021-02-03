package org.furmanski.model.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantDeserializer extends JsonDeserializer<Instant> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX");

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return Instant.from(dateTimeFormatter.parse(jsonParser.getText()));
    }
}
