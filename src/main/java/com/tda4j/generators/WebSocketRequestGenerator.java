package com.tda4j.generators;

import com.google.gson.Gson;
import com.tda4j.models.config.TDASessionConfig;
import com.tda4j.models.creds.TDACredentials;

public class WebSocketRequestGenerator {

  private static final Gson gson = new Gson();

  public static String generateQuoteRequest(TDACredentials credentials,
      TDASessionConfig sessionConfig) {
    return gson.toJson(getQuoteRequest(credentials, sessionConfig));
  }

  public static String generateLoginRequest(TDACredentials credentials) {
    return gson.toJson(getLoginRequest(credentials));
  }

  public static QuoteRequest getQuoteRequest(TDACredentials credentials,
      TDASessionConfig sessionConfig) {
    QuoteParameters quoteParameters = new QuoteParameters(sessionConfig.tickers(),
        sessionConfig.fields());
    return new QuoteRequest(sessionConfig.quoteService(), "SUBS", "1",
        credentials.userid(), credentials.source(), quoteParameters);
  }

  private static LoginRequest getLoginRequest(TDACredentials credentials) {
    LoginParameters loginParameters = new LoginParameters(credentials.token(),
        getEncodedCredentials(credentials), "1.0", "0");
    return new LoginRequest("ADMIN", "LOGIN", "0", credentials.userid(),
        credentials.source(), loginParameters);
  }

  private static String getEncodedCredentials(TDACredentials tdaCredentials) {
    Credentials creds = new Credentials(tdaCredentials.userid(), tdaCredentials.token(),
        tdaCredentials.company(), tdaCredentials.segment(), tdaCredentials.cddomain(),
        tdaCredentials.usergroup(), tdaCredentials.accesslevel(), "Y",
        tdaCredentials.timestamp(), tdaCredentials.appid(), tdaCredentials.acl());
    StringBuilder stringBuilder = new StringBuilder();
    gson.toJsonTree(creds).getAsJsonObject().entrySet().forEach(x -> {
      stringBuilder.append(x.getKey());
      stringBuilder.append("%3D");
      stringBuilder.append(x.getValue());
      stringBuilder.append("%26");
    });
    String s1 = stringBuilder.toString().replace("\"", "");
    return s1.substring(0, s1.length() - 3);
  }

  private record Credentials(String userid, String token, String company, String segment,
                             String cddomain, String usergroup, String accesslevel,
                             String authorized, String timestamp, String appid, String acl) {
  }

  private record LoginParameters(String token, String credential, String version, String qoslevel) {
  }

  private record LoginRequest(String service, String command, String requestid, String account,
                              String source, LoginParameters parameters) {
  }

  private record QuoteParameters(String keys, String fields) {
  }

  private record QuoteRequest(String service, String command, String requestid, String account,
                              String source, QuoteParameters parameters) {
  }
}
