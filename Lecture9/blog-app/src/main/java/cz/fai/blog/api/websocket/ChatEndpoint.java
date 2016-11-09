/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.blog.api.websocket;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author sohlich
 */
@ServerEndpoint(value = "/chat/{room}")
public class ChatEndpoint {

    // Handling endpoint events
    @OnOpen
    public void onOpen(Session session, @PathParam("room") String room) throws IOException {
        session.getBasicRemote().sendText("Welcome to Blog Chat room " + room);
    }

    @OnMessage
    public String receiveMessage(String message, @PathParam("room") String room) {
        return "You said: " + message;
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.getBasicRemote().sendText("Bye bye. See you soon.");
    }
}
