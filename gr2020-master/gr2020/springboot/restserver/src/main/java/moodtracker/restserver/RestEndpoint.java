package moodtracker.restserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class RestEndpoint {
  @GET
  public String index() {
    return "Welcome to MoodTracker!";
  }

}