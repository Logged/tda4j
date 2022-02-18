package com.tda4j.models;

import java.util.List;

public class TDASessionConfig {

  private final List<String> tickers;

  TDASessionConfig(List<String> tickers) {
    this.tickers = tickers;
  }

  public static TDASessionConfigLoader loader() {
    return new TDASessionConfigLoader();
  }

  public List<String> getTickers() {
    return this.tickers;
  }

  public static class TDASessionConfigLoader {

    private List<String> tickers;

    TDASessionConfigLoader() {
    }

    public TDASessionConfigLoader tickers(List<String> tickers) {
      this.tickers = tickers;
      return this;
    }

    public TDASessionConfig loader() {
      return new TDASessionConfig(tickers);
    }
  }
}
