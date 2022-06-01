package com.tda4j.models.msg;

import com.tda4j.models.gson.GsonUtils;
import java.util.List;

public class ResponseMessage extends Message {

  private List<Response> response;

  @Override
  public String toString() {
    return GsonUtils.toJson(this);
  }

  private static class Response {

    private String service;
    private String requestId;
    private String command;
    private Long timestamp;
    private ResponseContent content;

    @Override
    public String toString() {
      return GsonUtils.toJson(this);
    }

    public static class ResponseContent {

      private Integer code;
      private String msg;

      @Override
      public String toString() {
        return GsonUtils.toJson(this);
      }
    }
  }
}
