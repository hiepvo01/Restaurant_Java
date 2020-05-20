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

import com.luther.AndrewApp.MainClasses.Customer;
import com.luther.AndrewApp.MainClasses.Information;
import com.luther.AndrewApp.MainClasses.Restaurant;
@ServerEndpoint(value = "/form")

public class form {
    public Restaurant res = new Restaurant();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
    int i = 0;
    Customer cus;

    @OnMessage
    public String onMessage(String message, Session session) {
        String reply = "";
        System.out.println(message);
        System.out.println(i);
        String[] data = message.split(",");
        if(i==0){
            Information info = new Information(Integer.valueOf(data[0]), data[1], data[2], "0", 0);
            cus = new Customer(info);
            reply = cus.reserveTable(res, Integer.valueOf(data[3]));
        }
        if(i==1){
            reply = cus.order(data[0], Integer.valueOf(data[1]));
        }
        if(i==2){
            reply = cus.pay(res, Integer.valueOf(data[0]));
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
