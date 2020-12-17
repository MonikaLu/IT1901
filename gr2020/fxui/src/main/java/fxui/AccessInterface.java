package fxui;

import java.time.LocalDate;
import moodtracker.core.Day;
import moodtracker.core.Days;

public interface AccessInterface {

  /**
   * Updates the 'stats' of the stored day-object
   *
   * @param day with new 'stats' and same 'date' compared to a stored object
   * @return the resulting day-object
   */
  public Day updateDay(Day day);


  /**
   * Removes a day-object from the list.
   *
   * @param day -object to remove from list
   * @return the removed object
   */
  public Day deleteDay(Day day);

  /**
   * Adds a day to the list of registered days.
   *
   * @param day to add to the list
   * @return the added object
   */
  public Day addDay(Day day);

  /**
   * Gets a Day object that matches the given date.
   *
   * @param date to find Day object from
   * @return a json string of the corresponding Day
   */
  public Day getDay(LocalDate date);

  /**
   * Gets the stored Days-object
   *
   * @return the stored Days-object
   */
  public Days getDays();
}