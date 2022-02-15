package com.tda4j.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StreamParameters {

    private final String token;
    private final String credential;
    private final String version;
    private final String keys;
    private final String fields;
    private final String qoslevel;

}
