package com.tda4j.generators;

import com.google.gson.Gson;
import com.tda4j.models.TDACredentials;
import com.tda4j.models.TDASessionConfig;

public class WebSocketRequestGenerator {
    private static final Gson gson = new Gson();

    public static String generateQuoteRequest(TDACredentials credentials, TDASessionConfig sessionConfig) {
        return gson.toJson(getQuoteRequest(credentials, sessionConfig));
    }

    public static String generateLoginRequest(TDACredentials credentials) {
        return gson.toJson(getLoginRequest(credentials));
    }

    public static StreamRequest getQuoteRequest(TDACredentials credentials, TDASessionConfig sessionConfig) {
        StreamParameters quoteParameters = StreamParameters.builder()
                .keys(sessionConfig.getTickers().toString().replace("[", "").replace("]", ""))
                .fields("0,3,8,9")
                .build();
        return StreamRequest.builder()
                .service("QUOTE")
                .requestid("1")
                .command("SUBS")
                .account(credentials.getUserid())
                .source(credentials.getSource())
                .parameters(quoteParameters).build();
    }

    private static StreamRequest getLoginRequest(TDACredentials credentials) {
        StreamParameters streamParameters = StreamParameters.builder()
                .credential(getEncodedCredentials(credentials))
                .token(credentials.getToken())
                .version("1.0")
                .qoslevel("0")
                .build();
        return StreamRequest.builder()
                .service("ADMIN")
                .requestid("0")
                .command("LOGIN")
                .account(credentials.getUserid())
                .source(credentials.getSource())
                .parameters(streamParameters)
                .build();
    }

    private static String getEncodedCredentials(TDACredentials credentials) {
        Credentials build = Credentials.builder()
                .userid(credentials.getUserid())
                .token(credentials.getToken())
                .company(credentials.getCompany())
                .segment(credentials.getSegment())
                .cddomain(credentials.getCddomain())
                .usergroup(credentials.getUsergroup())
                .accesslevel(credentials.getAccesslevel())
                .authorized("Y")
                .timestamp(credentials.getTimestamp())
                .appid(credentials.getAppid())
                .acl(credentials.getAcl())
                .build();
        StringBuilder stringBuilder = new StringBuilder();
        gson.toJsonTree(build).getAsJsonObject().entrySet().forEach(x -> {
            stringBuilder.append(x.getKey());
            stringBuilder.append("%3D");
            stringBuilder.append(x.getValue());
            stringBuilder.append("%26");
        });
        String s1 = stringBuilder.toString().replace("\"", "");
        return s1.substring(0, s1.length() - 3);
    }

    private static class Credentials {
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

        Credentials(String userid, String token, String company, String segment, String cddomain,
                    String usergroup, String accesslevel, String authorized, String timestamp, String appid,
                    String acl) {
            this.userid = userid;
            this.token = token;
            this.company = company;
            this.segment = segment;
            this.cddomain = cddomain;
            this.usergroup = usergroup;
            this.accesslevel = accesslevel;
            this.authorized = authorized;
            this.timestamp = timestamp;
            this.appid = appid;
            this.acl = acl;
        }

        public static CredentialsBuilder builder() {
            return new CredentialsBuilder();
        }

        public static class CredentialsBuilder {

            private String userid;
            private String token;
            private String company;
            private String segment;
            private String cddomain;
            private String usergroup;
            private String accesslevel;
            private String authorized;
            private String timestamp;
            private String appid;
            private String acl;

            CredentialsBuilder() {
            }

            public Credentials.CredentialsBuilder userid(String userid) {
                this.userid = userid;
                return this;
            }

            public Credentials.CredentialsBuilder token(String token) {
                this.token = token;
                return this;
            }

            public Credentials.CredentialsBuilder company(String company) {
                this.company = company;
                return this;
            }

            public Credentials.CredentialsBuilder segment(String segment) {
                this.segment = segment;
                return this;
            }

            public Credentials.CredentialsBuilder cddomain(String cddomain) {
                this.cddomain = cddomain;
                return this;
            }

            public Credentials.CredentialsBuilder usergroup(String usergroup) {
                this.usergroup = usergroup;
                return this;
            }

            public Credentials.CredentialsBuilder accesslevel(String accesslevel) {
                this.accesslevel = accesslevel;
                return this;
            }

            public Credentials.CredentialsBuilder authorized(String authorized) {
                this.authorized = authorized;
                return this;
            }

            public Credentials.CredentialsBuilder timestamp(String timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Credentials.CredentialsBuilder appid(String appid) {
                this.appid = appid;
                return this;
            }

            public Credentials.CredentialsBuilder acl(String acl) {
                this.acl = acl;
                return this;
            }

            public Credentials build() {
                return new Credentials(userid, token, company, segment, cddomain, usergroup, accesslevel,
                        authorized, timestamp, appid, acl);
            }
        }
    }

    private static class StreamParameters {
        private final String token;
        private final String credential;
        private final String version;
        private final String keys;
        private final String fields;
        private final String qoslevel;

        StreamParameters(String token, String credential, String version, String keys,
                         String fields, String qoslevel) {
            this.token = token;
            this.credential = credential;
            this.version = version;
            this.keys = keys;
            this.fields = fields;
            this.qoslevel = qoslevel;
        }

        public static StreamParametersBuilder builder() {
            return new StreamParametersBuilder();
        }

        public static class StreamParametersBuilder {

            private String token;
            private String credential;
            private String version;
            private String keys;
            private String fields;
            private String qoslevel;

            StreamParametersBuilder() {
            }

            public StreamParameters.StreamParametersBuilder token(String token) {
                this.token = token;
                return this;
            }

            public StreamParameters.StreamParametersBuilder credential(String credential) {
                this.credential = credential;
                return this;
            }

            public StreamParameters.StreamParametersBuilder version(String version) {
                this.version = version;
                return this;
            }

            public StreamParameters.StreamParametersBuilder keys(String keys) {
                this.keys = keys;
                return this;
            }

            public StreamParameters.StreamParametersBuilder fields(String fields) {
                this.fields = fields;
                return this;
            }

            public StreamParameters.StreamParametersBuilder qoslevel(String qoslevel) {
                this.qoslevel = qoslevel;
                return this;
            }

            public StreamParameters build() {
                return new StreamParameters(token, credential, version, keys, fields, qoslevel);
            }
        }
    }

    private static class StreamRequest {
        private final String service;
        private final String command;
        private final String requestid;
        private final String account;
        private final String source;
        private final StreamParameters parameters;

        StreamRequest(String service, String command, String requestid, String account,
                      String source, StreamParameters parameters) {
            this.service = service;
            this.command = command;
            this.requestid = requestid;
            this.account = account;
            this.source = source;
            this.parameters = parameters;
        }

        public static StreamRequestBuilder builder() {
            return new StreamRequestBuilder();
        }

        public static class StreamRequestBuilder {

            private String service;
            private String command;
            private String requestid;
            private String account;
            private String source;
            private StreamParameters parameters;

            StreamRequestBuilder() {
            }

            public StreamRequest.StreamRequestBuilder service(String service) {
                this.service = service;
                return this;
            }

            public StreamRequest.StreamRequestBuilder command(String command) {
                this.command = command;
                return this;
            }

            public StreamRequest.StreamRequestBuilder requestid(String requestid) {
                this.requestid = requestid;
                return this;
            }

            public StreamRequest.StreamRequestBuilder account(String account) {
                this.account = account;
                return this;
            }

            public StreamRequest.StreamRequestBuilder source(String source) {
                this.source = source;
                return this;
            }

            public StreamRequest.StreamRequestBuilder parameters(StreamParameters parameters) {
                this.parameters = parameters;
                return this;
            }

            public StreamRequest build() {
                return new StreamRequest(service, command, requestid, account, source, parameters);
            }
        }
    }
}
