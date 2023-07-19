package com.example.chat;

import com.example.chat.config.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SimpleChatHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new ArrayList<>();

    // 연결 초기화
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("connected with session id: {}, total sessions: {}", session.getId(), sessions.size());
    }
    
    // 메시지 수신
    @Override
    public void handleTextMessage(
            WebSocketSession session, TextMessage message
    ) throws Exception {
        String payload = message.getPayload();
        log.info("received: {}", payload);
        
        // 연결된 세션에 메시지 전송
        for (WebSocketSession connected: sessions) {
            connected.sendMessage(message);
        }
    }
    
    // 연결 종료
    @Override
    public void afterConnectionClosed(
            WebSocketSession session, CloseStatus status
    ) throws Exception {
        log.info("connection with {} closed", session.getId());
        sessions.remove(session);
    }

    // 브로드캐스트
    public void broadcast(String message) throws Exception {
        for (WebSocketSession connected : sessions) {
            connected.sendMessage(new TextMessage(message));

        }
    }
}
