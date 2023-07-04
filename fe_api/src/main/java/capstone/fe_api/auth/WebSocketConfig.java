package capstone.fe_api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
//TODO implementare security?
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/sensorevent");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new ServerWebSocketHandler();
    }
}


//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //@Override
//    public void configureMessageBroker(final MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/sensors");
//        config.setApplicationDestinationPrefixes("/app");
//    }

 //   @Override
 //   public void registerStompEndpoints(final StompEndpointRegistry registry) {
 //       registry.addEndpoint("/event");
 //       registry.addEndpoint("/event").withSockJS();
 //   }
//}
