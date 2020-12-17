package moodtracker.restserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.time.LocalDate;
import moodtracker.core.Day;
import moodtracker.core.Days;
import moodtracker.json.JsonConverter;
import moodtracker.restserver.json.FileOperations;
import org.springframework.stereotype.Service;

@Service
public class MoodModelService {
  private Days days;

  String fileName =
      getClass().getClassLoader().getResource("json/registrations.json").toString().replace("file:",
          "");

  // We do not read file at construction.
  // If we used a DB, it would not make sense to cache all items in the DB.
  // Therefore we chose to emulate best practice.

  /**
   * Gets the stored Days object.
   *
   * @return a json string representing a Days-object
   */
  public String getAllDays() {
    getDaysFromFile();
    try {
      return JsonConverter.serialize(days);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Gets a Day object that matches the given date.
   *
   * @param date to find Day object from
   * @return a json string of the corresponding Day
   */
  public Day getOneDay(LocalDate date) {
    getDaysFromFile();
    return days.getDayByDate(date);
  }

  /**
   * Adds a day to the list of registered days.
   *
   * @param dayString a json representation of object to add to the list
   * @return a json string of the added object
   */
  public String addNewDay(String dayString) {
    getDaysFromFile();
    try {
      Day day = JsonConverter.deserializeDay(dayString);
      this.days.addDay(day);
      saveDaysToFile();
      if (days.getDayByDate(day.getDate()).getStats().equals(day.getStats())) {
        return dayString;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Removes a day-object from the list.
   *
   * @param date of Day object to remove from list
   * @return a json string of the removed object
   */
  public String deleteDay(LocalDate date) {
    getDaysFromFile();
    Day dayToRemove = days.getDayByDate(date);
    this.days.removeDay(dayToRemove);
    saveDaysToFile();
    try {
      return this.days.getDays().contains(dayToRemove) ? null :
          JsonConverter.serializeDay(dayToRemove);
    } catch (JsonProcessingException e) {
      return null;
    }

  }


  /**
   * Updates the 'stats' of the stored day-object.
   *
   * @param date      that matches a stored day-object
   * @param dayString json representation of the wished day-object
   * @return a json string of the resulting day-object
   */
  public String updateDay(LocalDate date, String dayString) {
    getDaysFromFile();
    Day dayToEdit = this.days.getDayByDate(date);
    try {
      dayToEdit.setStats(JsonConverter.deserializeDay(dayString).getStats());
      saveDaysToFile();
      return JsonConverter.serializeDay(this.days.getDayByDate(date));
    } catch (JsonProcessingException e) {
      return null;
    }


  }

  private void getDaysFromFile() {
    try {
      this.days = FileOperations.readFromFile(fileName);
    } catch (IOException e) {
      this.days = new Days();
    }
  }

  private void saveDaysToFile() {
    try {
      FileOperations.saveToFile(fileName, this.days);
    } catch (IOException e) {
      System.err.println("An error occurred during saving to json file");
    }
  }


}
