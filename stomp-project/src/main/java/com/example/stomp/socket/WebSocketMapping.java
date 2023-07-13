package com.example.stomp.socket;

import com.example.stomp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {

    // STOMP over WebSocket
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendChat(
            ChatMessage chatMessage,
            @Headers Map<String, Object> headers,
            @Header("nativeHeaders") Map<String, String> nativeHeaders
    ){
        log.info(chatMessage.toString());
        log.info(headers.toString());
        log.info(nativeHeaders.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessage.setTime(time);
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s",chatMessage.getRoomId()),
                chatMessage
        );
    }

    // /topic/{roomId} 구독 시 호출되는 메서드
    @SubscribeMapping("/topic/{roomId}")
    public ChatMessage sendGreet(@DestinationVariable("roomId") Long roomId) {
        log.info("new subscription to {}", roomId);
        ChatMessage message = new ChatMessage();
        message.setSender("admin");
        message.setMessage("hello!");
        message.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        return message;
    }
}
