package com.tda4j.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.StringJoiner;

public class Content implements Serializable {

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

  public Content() {
  }

  @Override
  public String toString() {
    final var joiner = new StringJoiner(", ", Content.class.getSimpleName() + "[", "]");
    if (key != null) {
      joiner.add("key=" + key);
    }
    if (symbol != null) {
      joiner.add("symbol=" + symbol);
    }
    if (bidPrice != null) {
      joiner.add("bidPrice=" + bidPrice);
    }
    if (lastPrice != null) {
      joiner.add("lastPrice=" + lastPrice);
    }
    if (askPrice != null) {
      joiner.add("askPrice=" + askPrice);
    }
    if (bidSize != null) {
      joiner.add("bidSize=" + bidSize);
    }
    if (askSize != null) {
      joiner.add("askSize=" + askSize);
    }
    if (totalVolume != null) {
      joiner.add("totalVolume=" + totalVolume);
    }
    if (lastSize != null) {
      joiner.add("lastSize=" + lastSize);
    }
    return joiner.toString();
  }

  public String getKey() {
    return this.key;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public String getSymbol() {
    return this.symbol;
  }

  public Double getBidPrice() {
    return this.bidPrice;
  }

  public Double getAskPrice() {
    return this.askPrice;
  }

  public Double getLastPrice() {
    return this.lastPrice;
  }

  public Double getBidSize() {
    return this.bidSize;
  }

  public Double getAskSize() {
    return this.askSize;
  }

  public Long getTotalVolume() {
    return this.totalVolume;
  }

  public Double getLastSize() {
    return this.lastSize;
  }
}
