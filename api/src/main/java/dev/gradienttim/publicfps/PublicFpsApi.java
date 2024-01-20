package dev.gradienttim.publicfps;

import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class PublicFpsApi {

  public void sendFPS(int fps, String url, String token) {
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("fps", fps);

      HttpClient client = HttpClient.newHttpClient();
      BodyPublisher body = BodyPublishers.ofString(jsonObject.toString());

      HttpRequest request = HttpRequest.newBuilder(URI.create(url))
          .header("Authorization", token)
          .POST(body)
          .build();
      client.send(request, BodyHandlers.ofString());
    } catch (Exception ignored) {}
  }

}
