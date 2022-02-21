package com.tda4j.http;

import com.tda4j.exceptions.TDAWebSocketClientException;
import com.tda4j.generators.WebSocketRequestGenerator;
import com.tda4j.models.TDACredentials;
import com.tda4j.models.TDASessionConfig;
import com.tda4j.util.WebSocketUtil;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TDAWebSocketClient extends AbstractTDAWebSocketClient {

  private static final Logger log = LoggerFactory.getLogger(TDAWebSocketClient.class);
  private final AtomicBoolean isLoggedIn = new AtomicBoolean(false);
  private CountDownLatch loginLatch = new CountDownLatch(1);

  public TDAWebSocketClient(URI uri, Consumer<String> consumer, TDACredentials credentials,
      TDASessionConfig sessionConfig) {
    super(uri, consumer, credentials, sessionConfig);
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    log.trace("Successfully Connected WebSocket to: {}. Data: {}", this.getURI(),
        handshakedata);
  }

  @Override
  public void onMessage(String message) {
    log.trace("Received message: {}", message);
    if (!isLoggedIn.getAcquire()) {
      if (WebSocketUtil.isLoggedIn(message)) {
        isLoggedIn.set(true);
        loginLatch.countDown();
      }
    }
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
    if (!isLoggedIn.getAcquire()) {
      log.error("Unable to send Quote Request. Not Logged In");
      throw new TDAWebSocketClientException("Unable to send Quote Request. Not Logged In");
    }
    this.send(WebSocketRequestGenerator.generateQuoteRequest(this.credentials, this.sessionConfig));
  }

  @Override
  public void login() {
    this.send(WebSocketRequestGenerator.generateLoginRequest(this.credentials));
  }

  @Override
  synchronized public void loginBlocking() throws InterruptedException {
    loginLatch = new CountDownLatch(1);
    login();
    boolean gotLock = loginLatch.await(5L, TimeUnit.SECONDS);
    if (!gotLock) {
      log.error("WebSocket Login timed out. 5 seconds has elapsed since request");
      throw new TDAWebSocketClientException(
          "WebSocket Login timed out. 5 seconds has elapsed since request");
    }
    log.trace("Successfully logged in");
  }

  public void register() throws InterruptedException {
    log.trace("Attempting to connect WebSocket with URI: {}", this.getURI());
    this.connectBlocking();
    this.loginBlocking();
    this.sendQuoteRequest();
  }
}
