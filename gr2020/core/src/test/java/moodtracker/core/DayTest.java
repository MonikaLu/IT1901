package moodtracker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class DayTest {
  private Map<String, Integer> map;

  @BeforeAll
  public void setUp() {
    map = new HashMap<>();
    map.put("score", 5);
  }

  public void testGetters() {
    LocalDate date = LocalDate.parse("2000-10-02");
    Day newDay = new Day(date, map);
    assertEquals(5, newDay.getStat("score"));
    assertEquals(date, newDay.getDate());
  }

  @Test
  public void testDateValidity() {
    ArrayList<String> validDates =
        new ArrayList<String>(Arrays.asList("2020-02-29"));
    ArrayList<String> inValidDates = new ArrayList<String>(Arrays
        .asList("2019-02-29", "02.2020-02-30", "1000-01-00", "1000-00-01",
            "01.01.2019"));

    validDates.forEach(a
        -> Assertions.assertDoesNotThrow(() -> LocalDate.parse(a)));
    inValidDates.forEach(a -> Assertions
        .assertThrows(DateTimeParseException.class, () -> LocalDate.parse(a)));
  }

  @Test
  public void testSetStats() {
    Day day = new Day(LocalDate.parse("2020-03-11"), map);

    Map<String, Integer> newMap = new HashMap<>();
    newMap.put("Mood", 2);

    day.setStats(newMap);

    assertNotEquals(map, day.getStats());
    assertEquals(newMap, day.getStats());
  }
}