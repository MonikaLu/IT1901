package moodtracker.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Day {
  private LocalDate date;
  private Map<String, Integer> stats;

  @JsonCreator
  public Day(
      @JsonProperty("date") final LocalDate date,
      @JsonProperty("stats") final Map<String, Integer> stats) {
    this.date = date;
    this.stats = new HashMap<>(stats);
  }

  public LocalDate getDate() {
    return date;
  }

  public Map<String, Integer> getStats() {
    return stats;
  }

  public void setStats(final Map<String, Integer> stats) {
    this.stats = stats;
  }

  @JsonIgnore
  public Integer getStat(final String stat) {
    return stats.get(stat);
  }
}
