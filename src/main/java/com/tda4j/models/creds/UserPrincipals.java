package com.tda4j.models.creds;

import java.time.ZonedDateTime;
import java.util.List;

public class UserPrincipals {

  private String primaryAccountId;
  private StreamerInfo streamerInfo;
  private List<Account> accounts;

  public List<Account> getAccounts() {
    return accounts;
  }

  public String getPrimaryAccountId() {
    return primaryAccountId;
  }

  public StreamerInfo getStreamerInfo() {
    return streamerInfo;
  }

  public static class StreamerInfo {

    private String token;
    private ZonedDateTime tokenTimestamp;
    private String userGroup;
    private String accessLevel;
    private String acl;
    private String appId;

    public String getToken() {
      return token;
    }

    public ZonedDateTime getTokenTimestamp() {
      return tokenTimestamp;
    }

    public String getUserGroup() {
      return userGroup;
    }

    public String getAccessLevel() {
      return accessLevel;
    }

    public String getAcl() {
      return acl;
    }

    public String getAppId() {
      return appId;
    }
  }

  public static class Account {

    private String company;
    private String segment;
    private String accountCdDomainId;

    public String getCompany() {
      return company;
    }

    public String getSegment() {
      return segment;
    }

    public String getAccountCdDomainId() {
      return accountCdDomainId;
    }
  }
}
