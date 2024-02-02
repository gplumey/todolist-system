package org.gplumey.importer.application.rest.config;

import org.gplumey.importer.application.rest.domain.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Map;

public class UserHandshakeHandler extends DefaultHandshakeHandler {

    Logger log = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var sessionId = SessionId.create();
        log.info("User " + sessionId.value() + " logged in.");
        return (UserPrincipal) () -> String.valueOf(sessionId.value());
    }
}
