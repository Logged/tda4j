package com.tda4j.models;

import java.util.List;

public class StreamResponse {

  private List<ServiceMessage> data;

  public List<ServiceMessage> getData() {
    return this.data;
  }

  public String toString() {
    return "StreamResponse(data=" + this.getData() + ")";
  }
}
