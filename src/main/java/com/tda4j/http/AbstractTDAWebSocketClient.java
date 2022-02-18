package com.tda4j.http;

import com.tda4j.models.TDACredentials;
import com.tda4j.models.TDASessionConfig;
import java.net.URI;
import java.util.function.Consumer;
import org.java_websocket.client.WebSocketClient;

public abstract class AbstractTDAWebSocketClient extends WebSocketClient {

  protected final Consumer<String> consumer;
  protected final TDACredentials credentials;
  protected final TDASessionConfig sessionConfig;
  protected final boolean asyncLogin;

  public AbstractTDAWebSocketClient(URI uri,
      Consumer<String> consumer, TDACredentials credentials,
      TDASessionConfig sessionConfig, boolean asyncLogin) {
    super(uri);
    this.consumer = consumer;
    this.credentials = credentials;
    this.sessionConfig = sessionConfig;
    this.asyncLogin = asyncLogin;
  }

  public abstract void sendQuoteRequest();

  public abstract void login();

}
