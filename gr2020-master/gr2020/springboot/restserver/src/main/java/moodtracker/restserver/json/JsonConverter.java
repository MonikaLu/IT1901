package moodtracker.restserver.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import moodtracker.core.Days;

public class JsonConverter {
  public static Days deserialize(final String json)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.readValue(json, Days.class);
  }

  public static String serialize(final Days days) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return mapper.writeValueAsString(days);
  }
}