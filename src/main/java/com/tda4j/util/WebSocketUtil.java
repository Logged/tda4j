package com.tda4j.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketUtil {

  private static final Logger log = LoggerFactory.getLogger(WebSocketUtil.class);

  public static boolean isLoggedIn(String data) {
    try {
      JsonObject root = JsonParser.parseString(data).getAsJsonObject();
      JsonArray responseArray = root.get("response").getAsJsonArray();
      for (JsonElement response : responseArray) {
        if ("LOGIN".equals(response.getAsJsonObject().get("command").getAsString())) {
          JsonObject content = response.getAsJsonObject().get("content").getAsJsonObject();
          return "0".equals(content.get("code").getAsString());
        }
      }
    } catch (Exception e) {
      log.trace("Exception was thrown when parsing login message");
      return false;
    }
    return false;
  }
}
