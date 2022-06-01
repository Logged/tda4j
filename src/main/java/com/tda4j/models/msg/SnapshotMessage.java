package com.tda4j.models.msg;

import com.tda4j.models.gson.GsonUtils;
import java.util.List;

//TODO: Implement Snapshot modeling
public class SnapshotMessage extends Message {

  private List<String> snapshot;

  @Override
  public String toString() {
    return GsonUtils.toJson(this);
  }
}
