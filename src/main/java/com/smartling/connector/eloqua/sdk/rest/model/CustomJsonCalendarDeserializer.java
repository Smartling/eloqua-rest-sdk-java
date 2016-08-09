package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Calendar;

public class CustomJsonCalendarDeserializer extends JsonDeserializer<Calendar>
{

    @Override
    public Calendar deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        Long time = Long.parseLong(p.getText());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time*1000);
        return calendar;
    }
}
