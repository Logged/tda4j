package com.tda4j.models.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tda4j.models.gson.adapters.MessageTypeAdapter;
import com.tda4j.models.msg.Message;

public abstract class GsonUtils {

  private static final Gson gson = new GsonBuilder().registerTypeAdapter(Message.class,
      new MessageTypeAdapter()).enableComplexMapKeySerialization().create();

  public static <T> T fromJson(String json, Class<T> classOfT) {
    T t = gson.fromJson(json, classOfT);
    return t;
  }

  public static String toJson(Object object) {
    if (object == null) {
      return "";
    }
    return gson.toJson(object);
  }
}
