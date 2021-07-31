package springboot.demo.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springboot.demo.util.ServerEncoder;

@Component
@ServerEndpoint(value = "/websocket", encoders = ServerEncoder.class)
public class WebSocket {

    Logger log = LoggerFactory.getLogger(WebSocket.class);

    private static ConcurrentHashMap<String, Session> concurrentHashMap = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session) {
        concurrentHashMap.put(session.getQueryString(), session);
        Map<String, Object> userProperties = session.getUserProperties();
        System.out.println(session.getQueryString());
        log.info("websocket connect");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("websocket push message:{}", message);
    }

    @OnClose
    public void onClose(Session session) {
        concurrentHashMap.remove(session.getQueryString());
        System.out.println("close: " + session.getQueryString());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(session.getQueryString());
    }

    public void sendMessage(String message, String id) {
        try {
            concurrentHashMap.get(id).getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
