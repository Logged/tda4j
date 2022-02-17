package com.tda4j.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.StringJoiner;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content implements Serializable {

    private String key;
    private long timestamp;

    @JsonProperty(value = "0")
    private String symbol;

    @JsonProperty(value = "1")
    private Double bidPrice;

    @JsonProperty(value = "2")
    private Double askPrice;

    @JsonProperty(value = "3")
    private Double lastPrice;

    @JsonProperty(value = "4")
    private Double bidSize;

    @JsonProperty(value = "5")
    private Double askSize;

    @JsonProperty(value = "8")
    private Long totalVolume;

    @JsonProperty(value = "9")
    private Double lastSize;

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
}
