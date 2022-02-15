package com.tda4j.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TDACredentials {

    private final String userid;
    private final String token;
    private final String company;
    private final String segment;
    private final String cddomain;
    private final String usergroup;
    private final String accesslevel;
    private final String authorized;
    private final String timestamp;
    private final String appid;
    private final String acl;

}
