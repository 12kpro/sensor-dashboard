package capstone.fe_api.config;

import capstone.fe_api.auth.ServerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Arrays;

@Configuration
@EnableWebSocket
//TODO implementare security?
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();
        registry.addHandler(webSocketHandler(), "/sensorevent").addInterceptors(interceptor).setAllowedOrigins("http://localhost:3000");
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
