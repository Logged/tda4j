package com.tda4j.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tda4j.models.StreamResponse;
import com.tda4j.models.TDACredentials;
import com.tda4j.models.TDASessionConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;

@Builder
public class TDAWebSocketHandler implements WebSocketHandler {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final Consumer<StreamResponse> consumer;
    private final TDACredentials credentials;
    private final TDASessionConfig sessionConfig;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        Thread newThread = new Thread(() -> {
            try {
                consumer.accept(mapper.readValue((String) message.getPayload(), StreamResponse.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        newThread.start();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void register() throws Exception {
        var webSocketClient = new StandardWebSocketClient();
        WebSocketSession webSocketSession = webSocketClient
                .doHandshake(this, new WebSocketHttpHeaders(), URI.create(sessionConfig.getUri()))
                .get();
        sendLoginRequest(webSocketSession);
        Thread.sleep(1000);
        sendQuoteRequest(webSocketSession);
    }

    private void sendQuoteRequest(WebSocketSession webSocketSession) throws IOException {
        webSocketSession.sendMessage(new TextMessage(
                mapper.writeValueAsString(getStreamRequest("QUOTE", "1", "SUBS", getStreamParameters()))));
    }

    private void sendLoginRequest(WebSocketSession webSocketSession) throws IOException {
        webSocketSession.sendMessage(new TextMessage(
                mapper.writeValueAsString(getStreamRequest("ADMIN", "0", "LOGIN", getLoginParameters()))));
    }

    private StreamRequest getStreamRequest(String service, String requestId, String command, StreamParameters params) {
        return StreamRequest.builder()
                .service(service)
                .requestid(requestId)
                .command(command)
                .account(credentials.getUserid())
                .source(sessionConfig.getSource())
                .parameters(params).build();
    }

    private StreamParameters getStreamParameters() {
        return StreamParameters.builder()
                .keys(sessionConfig.getTickers().toString())
                .fields("0,3,8,9")
                .build();
    }

    private StreamParameters getLoginParameters() throws JsonProcessingException {
        return StreamParameters.builder()
                .credential(getCredentials())
                .token(credentials.getToken())
                .version("1.0")
                .qoslevel("0")
                .build();
    }

    private String getCredentials() throws JsonProcessingException {
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
        String s = new ObjectMapper().writeValueAsString(build);
        StringBuilder stringBuilder = new StringBuilder();
        JsonNode jsonNode = new ObjectMapper().reader().readTree(s);
        jsonNode.fields().forEachRemaining(x -> {
            stringBuilder.append(x.getKey());
            stringBuilder.append("%3D");
            stringBuilder.append(x.getValue());
            stringBuilder.append("%26");
        });
        String s1 = stringBuilder.toString().replace("\"", "");
        return s1.substring(0, s1.length() - 3);
    }

    @Getter
    @Setter
    @Builder
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
    }

    @Builder
    @Getter
    @Setter
    private static class StreamParameters {
        private final String token;
        private final String credential;
        private final String version;
        private final String keys;
        private final String fields;
        private final String qoslevel;
    }

    @Builder
    @Getter
    @Setter
    private static class StreamRequest {
        private final String service;
        private final String command;
        private final String requestid;
        private final String account;
        private final String source;
        private final StreamParameters parameters;
    }
}
