package com.tda4j.models.creds;

public record TDACredentials(String userid, String token,
                             String company, String segment,
                             String cddomain, String usergroup,
                             String accesslevel, String timestamp,
                             String appid, String acl,
                             String source) {

}
