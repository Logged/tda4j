package com.tda4j.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tda4j.http.TDAHttpClient;
import com.tda4j.http.typeAdapters.ZdtTypeAdapter;
import com.tda4j.models.TDACredentials;
import com.tda4j.models.UserPrincipals;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;

public class TDACredentialProvider {

  public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(ZonedDateTime.class, new ZdtTypeAdapter())
      .enableComplexMapKeySerialization()
      .create();

  public static TDACredentials withToken(String bearerToken, String source) {
    HttpResponse<String> stringHttpResponse = TDAHttpClient.invokeGet(bearerToken);
    UserPrincipals userPrincipals = gson
        .fromJson(stringHttpResponse.body(), UserPrincipals.class);
    return TDACredentials.loader()
        .accesslevel(userPrincipals.getStreamerInfo().getAccessLevel())
        .acl(userPrincipals.getStreamerInfo().getAcl())
        .appid(userPrincipals.getStreamerInfo().getAppId())
        .cddomain(userPrincipals.getAccounts().get(0).getAccountCdDomainId())
        .company(userPrincipals.getAccounts().get(0).getCompany())
        .segment(userPrincipals.getAccounts().get(0).getSegment())
        .timestamp(Long.toString(
            userPrincipals.getStreamerInfo().getTokenTimestamp().toInstant().toEpochMilli()))
        .token(userPrincipals.getStreamerInfo().getToken())
        .usergroup(userPrincipals.getStreamerInfo().getUserGroup())
        .userid(userPrincipals.getPrimaryAccountId())
        .source(source)
        .load();
  }
}
