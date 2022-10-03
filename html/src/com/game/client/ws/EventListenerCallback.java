package com.game.client.ws;

import jsinterop.annotations.JsFunction;

@JsFunction
public interface EventListenerCallback {
    void callEvent(WsEvent event);
}
