package moodtracker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class StatisticsTest {
  private static Map<String, List<Integer>> stats =
      new HashMap<String, List<Integer>>();
  private List<Integer> statslist = new ArrayList<Integer>();

  @Test
  public void testCalculateAverage() {
    statslist.add(5);
    statslist.add(3);
    stats.put("happy", statslist);

    Map<String, Double> results = Statistics.calculateAverage(stats);

    assertTrue(results.containsKey("happy"));
    assertEquals(4, results.get("happy").intValue(),
        "Average of 5 and 3 should be 4");
  }

  @Test
  public void testCalculateAverageOverPeriod() {
    Map<String, Integer> stat1 = new HashMap<String, Integer>();
    Map<String, Integer> stat2 = new HashMap<String, Integer>();
    Map<String, Integer> stat3 = new HashMap<String, Integer>();
    stat1.put("happy", 8);
    stat2.put("happy", 1);
    stat3.put("happy", 3);
    Day day1 = new Day(LocalDate.parse("2020-10-19"), stat1);
    Day day2 = new Day(LocalDate.parse("2020-10-20"), stat2);
    Day day3 = new Day(LocalDate.parse("2020-10-23"), stat3);
    Days days = new Days();
    days.addDay(day1);
    days.addDay(day2);
    days.addDay(day3);

    LocalDate startDate = LocalDate.parse("2020-10-20");
    LocalDate endDate = LocalDate.parse("2020-10-24");

    Map<String, Double> result =
        Statistics.calculateAverageOverPeriod(days, startDate, endDate);

    assertEquals(2.0, result.get("happy").intValue(),
        "Average of 1 and 3 should be 2");
    assertNotEquals(4.0, result.get("happy").intValue(),
        "Day 1 should be excluded");
  }
}
