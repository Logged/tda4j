package com.tda4j.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TDACredentials {

  private final String userid;
  private final String token;
  private final String company;
  private final String segment;
  private final String cddomain;
  private final String usergroup;
  private final String accesslevel;
  private final String timestamp;
  private final String appid;
  private final String acl;
  private final String source;

  TDACredentials(String userid, String token, String company, String segment,
      String cddomain, String usergroup, String accesslevel, String timestamp,
      String appid, String acl, String source) {
    this.userid = userid;
    this.token = token;
    this.company = company;
    this.segment = segment;
    this.cddomain = cddomain;
    this.usergroup = usergroup;
    this.accesslevel = accesslevel;
    this.timestamp = timestamp;
    this.appid = appid;
    this.acl = acl;
    this.source = source;
  }

  public static TDACredentialsLoader loader() {
    return new TDACredentialsLoader();
  }

  public String getUserid() {
    return this.userid;
  }

  public String getToken() {
    return this.token;
  }

  public String getCompany() {
    return this.company;
  }

  public String getSegment() {
    return this.segment;
  }

  public String getCddomain() {
    return this.cddomain;
  }

  public String getUsergroup() {
    return this.usergroup;
  }

  public String getAccesslevel() {
    return this.accesslevel;
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public String getAppid() {
    return this.appid;
  }

  public String getAcl() {
    return this.acl;
  }

  public String getSource() {
    return this.source;
  }

  public static class TDACredentialsLoader {

    private static final Logger log = LoggerFactory.getLogger(TDACredentialsLoader.class);
    private String userid;
    private String token;
    private String company;
    private String segment;
    private String cddomain;
    private String usergroup;
    private String accesslevel;
    private String timestamp;
    private String appid;
    private String acl;
    private String source;

    TDACredentialsLoader() {
    }

    public TDACredentialsLoader userid(String userid) {
      this.userid = userid;
      return this;
    }

    public TDACredentialsLoader token(String token) {
      this.token = token;
      return this;
    }

    public TDACredentialsLoader company(String company) {
      this.company = company;
      return this;
    }

    public TDACredentialsLoader segment(String segment) {
      this.segment = segment;
      return this;
    }

    public TDACredentialsLoader cddomain(String cddomain) {
      this.cddomain = cddomain;
      return this;
    }

    public TDACredentialsLoader usergroup(String usergroup) {
      this.usergroup = usergroup;
      return this;
    }

    public TDACredentialsLoader accesslevel(String accesslevel) {
      this.accesslevel = accesslevel;
      return this;
    }

    public TDACredentialsLoader timestamp(String timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public TDACredentialsLoader appid(String appid) {
      this.appid = appid;
      return this;
    }

    public TDACredentialsLoader acl(String acl) {
      this.acl = acl;
      return this;
    }

    public TDACredentialsLoader source(String source) {
      this.source = source;
      return this;
    }

    public TDACredentials load() {
      if (null == userid || null == token || null == company || null == segment || null == cddomain
          || null == usergroup || null == accesslevel || null == timestamp
          || null == appid || null == acl || null == source) {
        log.warn("Attempting to build TDACredentials without every value given");
      }
      return new TDACredentials(userid, token, company, segment, cddomain, usergroup, accesslevel,
          timestamp, appid, acl, source);
    }
  }
}
