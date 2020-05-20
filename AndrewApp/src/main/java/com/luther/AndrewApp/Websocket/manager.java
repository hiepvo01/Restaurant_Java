package com.luther.AndrewApp.Websocket;

import java.io.IOException;
import java.util.logging.Logger;
import javax.websocket.CloseReason;

import javax.websocket.OnClose;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import com.luther.AndrewApp.MainClasses.Information;
import com.luther.AndrewApp.MainClasses.Manager;
import com.luther.AndrewApp.MainClasses.Restaurant;
@ServerEndpoint(value = "/manager")

public class manager {
    public Restaurant res = new Restaurant();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
    int i = 0;
    Manager man;

    @OnMessage
    public String onMessage(String message, Session session) {
        String reply = "";
        System.out.println(message);
        System.out.println(i);
        String[] data = message.split(",");
        if(i==0){
            Information info = new Information(Integer.valueOf(data[0]), data[1], data[2], data[3], Integer.valueOf(data[4]));
            String[] man_shift = {data[5], data[6]};
            man = new Manager(info, man_shift, 0, 0);
            reply = man.addManagertoDB();
        }
        if(i==1){
            reply = man.store.ItemData();
        }
        if(i==2){
            reply = man.checkEmpData();
        }
        this.i += 1;
        switch (message) {
        case "quit":
            try {
                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Order ended"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
        return reply;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
