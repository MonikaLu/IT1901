package moodtracker.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Statistics {

  /**
   * Calculates the average of all stats.
   *
   * @param stats should be a map consisting of stat-names and a list of the respective values.
   * @return map of all stats and their respective average value.
   */
  public static Map<String, Double> calculateAverage(final Map<String, List<Integer>> stats) {
    Map<String, Double> averageStats = new HashMap<>();

    // calculates the average for each stat and puts the results in averageStats
    for (Entry<String, List<Integer>> stat : stats.entrySet()) {
      List<Integer> values = stat.getValue();
      double scoreAverage = sum(values) / values.size();
      averageStats.put(stat.getKey(), scoreAverage);
    }

    return averageStats;
  }

  /**
   * Calculates the average of all stats over a given period.
   *
   * @param days      is the Days object to calculate the average from.
   * @param startDate low endpoint (inclusive) of dates to include in the calculations.
   * @param endDate   high endpoint (inclusive) of dates to include in the calculations.
   * @return map of all stats and their respective average value.
   */
  public static Map<String, Double> calculateAverageOverPeriod(
      final Days days, final LocalDate startDate, final LocalDate endDate) {
    //filters out irrelevant days based on date
    Collection<Day> daysInPeriod = days.getDays().stream()
        .filter(a -> checkIfDateInPeriod(a, startDate, endDate))
        .collect(Collectors.toList());

    //collects all stats from the relevant days
    Map<String, List<Integer>> stats = collectStats(daysInPeriod);

    //calculate average for stats
    return calculateAverage(stats);
  }

  private static Map<String, List<Integer>> collectStats(final Collection<Day> days) {
    Map<String, List<Integer>> stats = new HashMap<>();

    for (Day day : days) {
      for (String stat : day.getStats().keySet()) {
        //if stat (key) is not assigned, create a arraylist to that stat
        //if stat is assigned "computeIfAbsent" will be skipped
        //then add the value to the list
        stats.computeIfAbsent(stat, a -> new ArrayList<>())
            .add(day.getStat(stat));
      }
    }

    return stats;
  }

  private static double sum(final Collection<Integer> scoreValues) {
    //returns the sum of all values in the list
    return (double) scoreValues.stream().reduce(0, Integer::sum);
  }

  private static Boolean checkIfDateInPeriod(final Day day,
                                             final LocalDate startDate,
                                             final LocalDate endDate) {
    LocalDate date = day.getDate();
    return (date.isEqual(startDate) || date.isAfter(startDate))
        && (date.isEqual(endDate) || date.isBefore(endDate));
  }
}