package com.tda4j.models.gson.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tda4j.exceptions.TDADeserializationException;
import com.tda4j.http.TDAWebSocketClient;
import com.tda4j.models.msg.DataMessage;
import com.tda4j.models.msg.Message;
import com.tda4j.models.msg.NotifyMessage;
import com.tda4j.models.msg.ResponseMessage;
import com.tda4j.models.msg.SnapshotMessage;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageTypeAdapter implements JsonDeserializer<Message>, JsonSerializer<Message> {

  private static final Logger log = LoggerFactory.getLogger(TDAWebSocketClient.class);
  private static final Map<String, Class<? extends Message>> map = Map.of(
      "response", ResponseMessage.class,
      "notify", NotifyMessage.class,
      "data", DataMessage.class,
      "snapshot", SnapshotMessage.class
  );

  @Override
  public Message deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    JsonObject asJsonObject = json.getAsJsonObject();
    try {
      Set<String> keys = asJsonObject.keySet();
      if (keys.size() != 1) {
        log.error("Unknown message format. Num keys found: {}. Json msg: {}", keys.size(),
            json.getAsString());
        throw new TDADeserializationException(
            String.format("Unknown message format. Num keys found: %s. Json msg: %s", keys.size(),
                json.getAsString()));
      }
      return context.deserialize(json, map.get(keys.iterator().next()));
    } catch (Exception e) {
      log.error("Failure deserializing msg. Json Msg: {}", json.getAsString());
      throw new TDADeserializationException(
          String.format("Failure deserializing msg. Json Msg: %s", json.getAsString()));
    }
  }

  @Override
  public JsonElement serialize(Message src, Type typeOfSrc, JsonSerializationContext context) {
    //Todo: impl serialization
    return null;
  }
}
