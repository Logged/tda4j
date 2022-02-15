package com.tda4j.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

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
}
