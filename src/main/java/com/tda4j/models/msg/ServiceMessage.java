package com.tda4j.models.msg;

import java.util.List;


public class ServiceMessage extends Message {

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
    return "ServiceMessage(quoteService=" + this.getService() + ", content=" + this.getContent()
        + ", timestamp=" + this.getTimestamp() + ")";
  }
}
