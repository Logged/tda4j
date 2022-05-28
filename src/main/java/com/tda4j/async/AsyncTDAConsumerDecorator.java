package com.tda4j.async;

import java.util.function.Consumer;

public class AsyncTDAConsumerDecorator implements Consumer<String> {

  private final Consumer<String> consumer;

  public AsyncTDAConsumerDecorator(Consumer<String> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void accept(String s) {
    Thread.startVirtualThread(()-> consumer.accept(s));
  }
}