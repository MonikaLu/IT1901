package moodtracker.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Days {
  private final List<Day> days = new ArrayList<>();

  public Collection<Day> getDays() {
    return days;
  }

  public void addDay(Day day) {
    //Checks if days already have a day object that matches given date
    if (!getListOfDates().contains(day.getDate())) {
      days.add(day);
      sortDays();
    }
  }

  public void removeDay(final Day day) {
    days.remove(day);
  }

  /**
   * Gets a Day object from the list of registered days.
   *
   * @param date of the Day that should be returned
   * @return Day object that matches the given date
   */
  public Day getDayByDate(LocalDate date) {
    //return the first Day object that matches with the given date
    return days.stream()
        .filter(a -> a.getDate().isEqual(date))
        .findFirst()
        .orElseGet(null);
  }

  @JsonIgnore
  public Collection<LocalDate> getListOfDates() {
    return days.stream().map(Day::getDate).collect(Collectors.toList());
  }

  public void sortDays() {
    days.sort(Comparator.comparing(Day::getDate));
    Collections.reverse(days);
  }
}