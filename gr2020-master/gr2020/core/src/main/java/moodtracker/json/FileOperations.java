package moodtracker.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import moodtracker.core.Days;

public class FileOperations {

  /**
   * Get a Days object from a JSON file.
   *
   * @param filename for JSON file that shall be read.
   * @return a Days object parsed from the JSON file.
   * @throws IOException if file not found or jackson could not convert the object.
   */
  public static Days readFromFile(final String filename)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.readValue(new File(filename), Days.class);
  }

  /**
   * Save a Days-object as JSON to a JSON file.
   *
   * @param filename to JSON file to write the JSON string.
   * @param days     to write as JSON.
   * @throws IOException if file not found or jackson could not convert the object.
   */
  public static void saveToFile(final String filename, final Days days) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      throw new FileNotFoundException();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    //DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
    //pp.indentArraysWith( DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

    objectMapper.writeValue(file, days);
    //objectMapper.writer(pp).writeValue(file, days);
  }
}