package moodtracker.restserver;

import org.glassfish.jersey.server.ResourceConfig;

public class EndPointConfig extends ResourceConfig {

  public EndPointConfig() {
    register(RestEndpoint.class);
  }


}