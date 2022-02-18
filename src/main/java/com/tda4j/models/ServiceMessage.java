package com.tda4j.models;

import java.util.List;


public class ServiceMessage {

  private String service;
  private List<Content> content;
  private long timestamp;

  public String getService() {
    return this.service;
  }

  public List<Content> getContent() {
    return this.content;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public String toString() {
    return "ServiceMessage(service=" + this.getService() + ", content=" + this.getContent()
        + ", timestamp=" + this.getTimestamp() + ")";
  }
}
