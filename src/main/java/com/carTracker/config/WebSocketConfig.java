package com.carTracker.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        SimpMessagingTemplate messagingTemplate = applicationContext.getBean(SimpMessagingTemplate.class);
        registry.addHandler(new CarPositionWebSocketHandler(messagingTemplate), "/car-position")
                .setAllowedOrigins("*");
    }

    @Bean
    public SimpMessagingTemplate messagingTemplate(MessageChannel clientInboundChannel) {
        return new SimpMessagingTemplate(clientInboundChannel);
    }

    @Bean
    public MessageChannel clientInboundChannel() {
        return new ExecutorSubscribableChannel();
    }

}