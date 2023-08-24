package com.carTracker.config;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
@Component
public class CarPositionWebSocketHandler implements WebSocketHandler {
	  private final SimpMessagingTemplate messagingTemplate;
	  public CarPositionWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
	        this.messagingTemplate = messagingTemplate;
	    }

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// Subscribe the session to a specific topic
        // You can use a unique identifier for each car or any other identifier scheme you prefer
        String carTopic = "car1";
        messagingTemplate.convertAndSend("/topic/car-position/" + carTopic, "Car connected");

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
