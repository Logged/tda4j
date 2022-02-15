package com.tda4j.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class ServiceMessage {

    @NonNull
    private String service;
    @NonNull
    private List<Content> content;
    private long timestamp;

}
