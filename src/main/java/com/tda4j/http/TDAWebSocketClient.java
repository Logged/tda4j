package com.tda4j.http;

import com.tda4j.generators.WebSocketRequestGenerator;
import com.tda4j.models.TDACredentials;
import com.tda4j.models.TDASessionConfig;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TDAWebSocketClient extends AbstractTDAWebSocketClient {

  private static final Logger log = LoggerFactory.getLogger(TDAWebSocketClient.class);

  public TDAWebSocketClient(URI uri, Consumer<String> consumer, TDACredentials credentials,
      TDASessionConfig sessionConfig, boolean asyncLogin) {
    super(uri, consumer, credentials, sessionConfig, asyncLogin);
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    log.trace("Successfully Connected WebSocket to: {}. Data: {}", this.getURI(),
        handshakedata);
  }

  @Override
  public void onMessage(String message) {
    log.trace("Received message: {}", message);
    consumer.accept(message);
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {
    log.trace("Disconnected with the following code, reason: {},{}",
        code, reason);
  }

  @Override
  public void onError(Exception ex) {
    log.error("WebSocket Error occurred: ", ex);
  }

  @Override
  public void sendQuoteRequest() {
    this.send(WebSocketRequestGenerator.generateQuoteRequest(this.credentials, this.sessionConfig));
  }

  @Override
  public void login() {
    this.send(WebSocketRequestGenerator.generateLoginRequest(this.credentials));
  }

  public void register() throws InterruptedException {
    log.trace("Attempting to connect WebSocket with URI: {}", this.getURI());
    this.connectBlocking();
    this.login();
    TimeUnit.SECONDS.sleep(5L);
    this.sendQuoteRequest();
  }
}
