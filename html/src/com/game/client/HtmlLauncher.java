package com.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.game.Starter;
import com.game.client.dto.InputStateImpl;
import com.game.client.ws.EventListenerCallback;
import com.game.client.ws.WebSocket;
import com.game.client.ws.WsEvent;
import com.game.connection.MessageSender;
import com.google.gwt.user.client.Timer;

public class HtmlLauncher extends GwtApplication {
    private MessageProcessor messageProcessor;

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        return new GwtApplicationConfiguration(true);
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    private native WebSocket getWebSocket(String url)
        /*-{
            return new WebSocket(url);
        }-*/;

    private native void log(Object obj)
        /*-{
            console.log(obj);
        }-*/;

    private native String toJson(Object obj)
        /*-{
            return JSON.stringify(obj);
        }-*/;

    @Override
    public ApplicationListener createApplicationListener() {
        final WebSocket client = getWebSocket("ws://localhost:8888/ws");
        final Starter starter = new Starter(new InputStateImpl());
        messageProcessor = new MessageProcessor(starter);
        starter.setMessageSender(new MessageSender() {
            @Override
            public void sendMessage(Object message) {
                client.send(HtmlLauncher.this.toJson(message));
            }
        });

        final Timer timer = new Timer() {
            @Override
            public void run() {
                starter.hedleTimer();
            }
        };

        timer.scheduleRepeating(1000/60);
        EventListenerCallback callback = new EventListenerCallback() {
            @Override
            public void callEvent(WsEvent event) {
                messageProcessor.procesEvent(event);
            }
        };
        client.addEventListener("open", new EventListenerCallback() {
            @Override
            public void callEvent(WsEvent event) {
                timer.scheduleRepeating(1000 / 30);
                messageProcessor.procesEvent(event);
            }
        });
        client.addEventListener("close", callback);
        client.addEventListener("error", callback);
        client.addEventListener("message", callback);

        return starter;
    }
}