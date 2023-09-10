package com.example.first.handler;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {

   private final List<WebSocketSession> sessions = new ArrayList<>();

   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      sessions.add(session);
   }

   @Override
   public void afterConnectionClosed(WebSocketSession session,
                                     CloseStatus status) throws Exception {
      sessions.remove(session);
   }
   //웹소켓 메시지 session id 와 메시지 전송
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      for (WebSocketSession webSocketSession : sessions) {
         webSocketSession.sendMessage(new TextMessage(session.getId()+":"+message.getPayload()));
      }
   }

}
