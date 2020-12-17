package moodtracker.restserver;

import java.time.LocalDate;
import moodtracker.core.Day;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoodModelController {

  private final MoodModelService moodModelService;

  public MoodModelController() {
    this.moodModelService = new MoodModelService();
  }

  @RequestMapping("/")
  public String index() {
    return "Welcome to MoodTracker!";
  }

  @GetMapping("/days")
  public String getDays() {
    return this.moodModelService.getAllDays();
  }

  @GetMapping("/day/{date}")
  public Day getDay(@PathVariable("date") String date) {
    return this.moodModelService.getOneDay(LocalDate.parse(date));
  }

  @PostMapping("/newDay")
  public String addNewDay(@RequestBody String day) {
    return this.moodModelService.addNewDay(day);
  }

  @PutMapping("/updateDay/{date}")
  public String updateDay(@PathVariable("date") String date,
                          @RequestBody String day) {
    return this.moodModelService.updateDay(LocalDate.parse(date), day);
  }

  @DeleteMapping("/deleteDay/{date}")
  public String deleteDay(@PathVariable("date") String date) {
    return this.moodModelService.deleteDay(LocalDate.parse(date));
  }
}