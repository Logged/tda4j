package com.tda4j.http;

import com.tda4j.constants.HTTPConstants;
import com.tda4j.exceptions.TDAHttpClientException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class TDAHttpClient {

  public static HttpResponse<String> invokeGet(String bearerToken) {
    HttpClient client = HttpClient.newBuilder()
        .version(Version.HTTP_2)
        .followRedirects(Redirect.NORMAL)
        .build();

    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(HTTPConstants.USER_PRINCIPALS_URL))
          .GET()
          .header(HTTPConstants.USER_PRINCIPALS_AUTH_HEADER_KEY,
              HTTPConstants.USER_PRINCIPALS_BEARER_APPENDER + bearerToken)
          .timeout(Duration.ofSeconds(10))
          .build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new TDAHttpClientException(
            String.format(
                "Failed to get UserPrincipals. HTTP Status code: %s. Response: %s. Request: %s",
                response.statusCode(), response, request));
      }
      return response;
    } catch (URISyntaxException | IOException | InterruptedException e) {
      throw new TDAHttpClientException("Failed to get UserPrincipals with following exception {}",
          e);
    }
  }
}
