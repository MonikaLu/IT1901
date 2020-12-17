package moodtracker.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import moodtracker.core.Day;
import moodtracker.core.Days;

@SpringBootTest(classes = MoodModelApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class MoodModelControllerIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  //Test HttpRequest
  @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class)).contains("Welcome to MoodTracker!");
  }

  @Test
  public void testDays() {
    assertNotNull(
        this.restTemplate
            .getForObject("http://localhost:" + port + "/days", Days.class));
  }

  ;
 
    /*@Test
    public void testAddDay() {

        int randomDay = ThreadLocalRandom.current().nextInt(1, 30);
    int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
    int randomYear = ThreadLocalRandom.current().nextInt(1500, 2020);

    String randomDayString =
        randomDay < 10 ? "0" + randomDay : String.valueOf(randomDay);
    String randomMonthString =
        randomMonth < 10 ? "0" + randomMonth : String.valueOf(randomMonth);

    String randomDate =
        (randomYear + "-" + randomMonthString + "-" + randomDayString);

        LocalDate date = LocalDate.parse(randomDate);

        Map<String, Integer> stats = new HashMap<String, Integer>();

        stats.put("Mood", 1);
        stats.put("Sleep", 5);
        stats.put("Motivation", 2);

        Day day = new Day(date,stats);

        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/newDay/"+randomDate, day, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }*/

}