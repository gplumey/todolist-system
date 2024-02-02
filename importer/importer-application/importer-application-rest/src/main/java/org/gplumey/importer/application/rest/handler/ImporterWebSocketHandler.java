package org.gplumey.importer.application.rest.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ImporterWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ImporterWebSocketHandler.class);

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var f = Flux.just("A", "B", "C", "D", "E").map(session::textMessage);
        return session.send(f);
    }
}
