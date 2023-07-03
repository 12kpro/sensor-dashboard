package capstone.sensor_api.auth;

import capstone.sensor_api.utils.ClientWebSocketHandler;
import capstone.sensor_api.utils.StompSessionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
//TODO da rimuovere
@Configuration
public class ClientWebSocketConfig {
//    @Bean
//    public WebSocketConnectionManager webSocketConnectionManager() {
//        WebSocketConnectionManager manager = new WebSocketConnectionManager(
//                webSocketClient(),
//                clientWebSocketHandler(),
//                "ws://localhost:5080/sensorevent"
//        );
//        manager.setAutoStartup(true);
//        return manager;
//    }
//
//    @Bean
//    public WebSocketClient webSocketClient() {
//        return new StandardWebSocketClient();
//    }
//
//    @Bean
//    public WebSocketHandler clientWebSocketHandler() {
//        return new ClientWebSocketHandler();
//    }

}
