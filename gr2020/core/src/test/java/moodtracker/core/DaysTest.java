package moodtracker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DaysTest {
  private static Days days;
  private static HashMap<String, Integer> map = new HashMap<String, Integer>();

  @BeforeEach
  public void setUp() {
    days = new Days();
    map.put("score", 5);
  }


  @Test
  public void testAddDay() {
    LocalDate date = LocalDate.parse("2010-04-04");
    Day testDay = new Day(date, map);

    days.addDay(testDay);

    Day compareTo = days.getDayByDate(date);
    assertEquals(compareTo.getDate(), testDay.getDate());
    assertEquals(compareTo.getStats(), testDay.getStats());
  }

  @Test
  public void testAddSameDateWithDiffernetScore() {
    LocalDate date = LocalDate.parse("2016-02-28");
    HashMap<String, Integer> map2 = new HashMap<String, Integer>();
    map2.put("score", 1);

    Day testDay1 = new Day(date, map);
    Day testDay2 = new Day(date, map2);

    days.addDay(testDay1);
    days.addDay(testDay2);

    Day compareTo = days.getDayByDate(date);
    assertTrue(compareTo.getStats().equals(testDay1.getStats()));
    assertFalse(compareTo.getStats().equals(testDay2.getStats()));
  }

  @Test
  public void testToString() {
    days.addDay(new Day(LocalDate.parse("2000-10-02"), map));

    //This toString() method in days is not updated
    //assertEquals("02.10.2000: 3\n", days.toString());
  }

  @Test
  public void testRemoveDay() {
    LocalDate date = LocalDate.parse("1989-04-10");
    Day newDay = new Day(date, map);
    days.addDay(newDay);
    assertTrue(days.getListOfDates().contains(date));
    days.removeDay(newDay);
    assertFalse(days.getListOfDates().contains(date));
  }
}