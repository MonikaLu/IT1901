package moodtracker.json;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import moodtracker.core.Day;
import moodtracker.core.Days;
import org.junit.jupiter.api.Test;

public class FileOperationsTest {
  private final String filepath = "src/test/resources/testData.json";
  private final String not_a_file = "Not a file";

  @Test
  public void testSaveToFile() {
    Days days = new Days();
    Map<String, Integer> stats = new HashMap<String, Integer>();

    stats.put("mood", 5);
    days.addDay(new Day(LocalDate.parse("2019-09-10"), stats));

    assertDoesNotThrow(() -> FileOperations.saveToFile(filepath, days));
  }

  @Test
  public void testSaveToFile_IOException() {
    assertThrows(IOException.class,
        () -> FileOperations.saveToFile(not_a_file, new Days()));
  }

  @Test
  public void testReadFromFile() {
    assertDoesNotThrow(() -> FileOperations.readFromFile(filepath));
  }

  @Test
  public void testReadFromFile_IOException() {
    assertThrows(IOException.class,
        () -> FileOperations.readFromFile(not_a_file));
  }
}