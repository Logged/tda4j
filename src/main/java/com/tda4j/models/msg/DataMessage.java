package com.tda4j.models.msg;

import com.google.gson.annotations.SerializedName;
import com.tda4j.models.gson.GsonUtils;
import java.util.List;


public class DataMessage extends Message {

  private List<Data> data;

  @Override
  public String toString() {
    return GsonUtils.toJson(this);
  }

  private static class Data {

    private String service;
    private long timestamp;
    private String command;
    private List<DataContent> content;

    @Override
    public String toString() {
      return GsonUtils.toJson(this);
    }

    public static class DataContent {

      private String key;
      private long timestamp;

      @SerializedName(value = "0")
      private String symbol;

      @SerializedName(value = "1")
      private Double bidPrice;

      @SerializedName(value = "2")
      private Double askPrice;

      @SerializedName(value = "3")
      private Double lastPrice;

      @SerializedName(value = "4")
      private Double bidSize;

      @SerializedName(value = "5")
      private Double askSize;

      @SerializedName(value = "8")
      private Long totalVolume;

      @SerializedName(value = "9")
      private Double lastSize;

      @Override
      public String toString() {
        return GsonUtils.toJson(this);
      }
    }
  }
}
