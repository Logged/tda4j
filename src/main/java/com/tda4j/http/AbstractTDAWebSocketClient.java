package com.tda4j.http;

import com.tda4j.models.creds.TDACredentials;
import com.tda4j.models.config.TDASessionConfig;
import java.net.URI;
import java.util.function.Consumer;
import org.java_websocket.client.WebSocketClient;

public abstract class AbstractTDAWebSocketClient extends WebSocketClient {

  protected final Consumer<String> consumer;
  protected final TDACredentials credentials;

  public AbstractTDAWebSocketClient(URI uri,
      Consumer<String> consumer, TDACredentials credentials) {
    super(uri);
    this.consumer = consumer;
    this.credentials = credentials;
  }

  public abstract void sendQuoteRequest(TDASessionConfig sessionConfig);

  public abstract void login();

  public abstract void loginBlocking() throws InterruptedException;

}
