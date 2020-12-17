package moodtracker.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import moodtracker.core.Day;
import moodtracker.core.Days;
import org.junit.jupiter.api.Test;

public class JsonConverterTest {

  private LocalDate date;
  Map<String, Integer> stats;
  private Day day;

  @Test
  public void testDaysConvertion() {
    date = LocalDate.parse("2020-10-10");
    stats = new HashMap<>();
    stats.put("Mood", 3);
    day = new Day(date, stats);
    Days days = new Days();
    days.addDay(day);
    try {
      String json = JsonConverter.serialize(days);
      Day original = days.getDayByDate(date);
      Day compareTo = JsonConverter.deserialize(json).getDayByDate(date);
      assertEquals(original.getDate(), compareTo.getDate());
      assertEquals(original.getStats(), compareTo.getStats());
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testDayConvertion() {
    try {
      date = LocalDate.parse("2008-08-10");
      stats = new HashMap<>();
      stats.put("Sleep", 5);
      day = new Day(date, stats);
      String json = JsonConverter.serializeDay(day);
      Day compareTo = JsonConverter.deserializeDay(json);
      assertEquals(day.getDate(), compareTo.getDate());
      assertEquals(day.getStats(), compareTo.getStats());
    } catch (JsonProcessingException e) {
      fail();
    }
  }
}
