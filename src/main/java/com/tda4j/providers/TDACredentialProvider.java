package com.tda4j.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tda4j.http.TDAHttpClient;
import com.tda4j.http.typeAdapters.ZdtTypeAdapter;
import com.tda4j.models.creds.TDACredentials;
import com.tda4j.models.creds.UserPrincipals;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;

public class TDACredentialProvider {

  public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(ZonedDateTime.class, new ZdtTypeAdapter())
      .enableComplexMapKeySerialization()
      .create();

  public static TDACredentials withToken(String bearerToken) {
    HttpResponse<String> stringHttpResponse = TDAHttpClient.invokeGet(bearerToken);
    UserPrincipals userPrincipals = gson
        .fromJson(stringHttpResponse.body(), UserPrincipals.class);
    return new TDACredentials(userPrincipals.getPrimaryAccountId(),
        userPrincipals.getStreamerInfo().getToken(),
        userPrincipals.getAccounts().get(0).getCompany(),
        userPrincipals.getAccounts().get(0).getSegment(),
        userPrincipals.getAccounts().get(0).getAccountCdDomainId(),
        userPrincipals.getStreamerInfo().getUserGroup(),
        userPrincipals.getStreamerInfo().getAccessLevel(), Long.toString(
        userPrincipals.getStreamerInfo().getTokenTimestamp().toInstant().toEpochMilli()),
        userPrincipals.getStreamerInfo().getAppId(),
        userPrincipals.getStreamerInfo().getAcl(), userPrincipals.getStreamerInfo().getAppId());
  }
}
