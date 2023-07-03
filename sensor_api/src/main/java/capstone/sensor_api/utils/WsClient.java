package capstone.sensor_api.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
@Slf4j
@Component
@Getter
public class WsClient extends TextWebSocketHandler {

    private WebSocketSession clientSession;

    public WsClient () throws ExecutionException, InterruptedException {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        this.clientSession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(), URI.create("ws://localhost:5080/sensorevent")).get();
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Client connection opened");
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("Server received and send message" + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Client connection closed: {}", status);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            try {
                this.clientSession = new StandardWebSocketClient().doHandshake(this, new WebSocketHttpHeaders(), URI.create("ws://localhost:5080/sensorevent")).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.info("Client transport error: {}", exception.getMessage());
    }
}
