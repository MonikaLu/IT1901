package fxui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import moodtracker.core.Day;
import moodtracker.core.Days;
import moodtracker.json.JsonConverter;

public class RemoteAccess implements AccessInterface {

  private URI endpointBaseUri;
  private URI daysUri;
  private URI newDayUri;

  public RemoteAccess(URI endpointBaseUri) {
    try {
      this.endpointBaseUri = endpointBaseUri;
      this.daysUri = new URI(endpointBaseUri.toString() + "/days");
      this.newDayUri = new URI(endpointBaseUri.toString() + "/newDay");
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Enter a valid URI");
    }
  }

  @Override
  public Days getDays() {
    HttpRequest request = HttpRequest.newBuilder(daysUri)
        .header("Accept", "application/json")
        .GET()
        .build();
    try {
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      final String responseString = response.body();
      return JsonConverter.deserialize(responseString);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public Day getDay(LocalDate date) {
    try {
      HttpRequest request = HttpRequest.newBuilder(getDayUri(date))
          .header("Accept", "application/json")
          .GET()
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      final String responseString = response.body();
      return JsonConverter.deserializeDay(responseString);
    } catch (IOException | InterruptedException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private URI getDayUri(LocalDate date) throws URISyntaxException {
    return new URI(endpointBaseUri + "/day/" + date.toString());
  }

  public Day addDay(Day day) {
    try {
      String jsonString = JsonConverter.serializeDay(day);
      System.out.println(jsonString);
      HttpRequest request = HttpRequest.newBuilder(newDayUri)
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(jsonString))
          .build();

      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      return JsonConverter.deserializeDay(response.body());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  //En metode for DELETE
  public Day deleteDay(Day day) {
    try {
      HttpRequest request = HttpRequest.newBuilder(getDeleteUri(day))
          .header("Accept", "application/json")
          .DELETE()
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      return JsonConverter.deserializeDay(response.body());
    } catch (IOException | InterruptedException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private URI getDeleteUri(Day day) throws URISyntaxException {
    return new URI(endpointBaseUri + "/deleteDay/" + day.getDate().toString());
  }

  public Day updateDay(Day day) {
    try {
      String jsonString = JsonConverter.serializeDay(day);
      System.out.println(jsonString);
      HttpRequest request = HttpRequest.newBuilder(getEditUri(day))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(jsonString))
          .build();

      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      return JsonConverter.deserializeDay(response.body());
    } catch (IOException | InterruptedException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private URI getEditUri(Day day) throws URISyntaxException {
    return new URI(endpointBaseUri + "/updateDay/" + day.getDate().toString());
  }

}
