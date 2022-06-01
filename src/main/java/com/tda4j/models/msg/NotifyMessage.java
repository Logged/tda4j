package com.tda4j.models.msg;

import com.tda4j.models.gson.GsonUtils;
import java.util.List;

public class NotifyMessage extends Message {

  private List<Notification> notify;

  @Override
  public String toString() {
    return GsonUtils.toJson(this);
  }

  private static class Notification {

    private String heartbeat;

    @Override
    public String toString() {
      return GsonUtils.toJson(this);
    }
  }
}
