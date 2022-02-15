package com.tda4j.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TDASessionConfig {
    private final String uri;
    private final List<String> tickers;
    private final String source;
}
