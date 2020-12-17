package fxui;

import java.time.LocalDate;
import moodtracker.core.Day;
import moodtracker.core.Days;

public class LocalAccess implements AccessInterface {

  private Days days;

  public LocalAccess() {
    days = new Days();
  }

  public Day updateDay(Day day) {
    days.getDayByDate(day.getDate()).setStats(day.getStats());
    return days.getDayByDate(day.getDate());
  }

  public Day deleteDay(Day day) {
    days.removeDay(day);
    return day;
  }

  public Day addDay(Day day) {
    days.addDay(day);
    return days.getDayByDate(day.getDate());
  }

  public Day getDay(LocalDate date) {
    return days.getDayByDate(date);
  }

  public Days getDays() {
    return days;
  }

}
