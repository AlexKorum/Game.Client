package com.game.client.dto;

import com.game.controls.InputState;

public class InputStateImpl implements InputState {
    public InputStateImpl() {
        setType("state");

        setUp(false);
        setDown(false);
        setLeft(false);
        setRight(false);
        setFire(false);
        setAngle(0);

    }

    public InputStateImpl(boolean up, boolean down, boolean left, boolean right, boolean fire, float angle) {
        setType("state");
        setUp(up);
        setDown(down);
        setLeft(left);
        setRight(right);
        setFire(fire);
        setAngle(angle);
    }

    @Override
    public native boolean isUp() /*-{
        return this. up;
    }-*/;

    @Override
    public native void setUp(boolean up) /*-{
        this.up = up;
    }-*/;

    @Override
    public native boolean isDown() /*-{
        return this. down;
    }-*/;

    @Override
    public native void setDown(boolean down) /*-{
        this.down = down;
    }-*/;

    @Override
    public native boolean isLeft() /*-{
        return this. left;
    }-*/;

    @Override
    public native void setLeft(boolean left) /*-{
        this.left = left;
    }-*/;

    @Override
    public native boolean isRight() /*-{
        return this. right;
    }-*/;

    @Override
    public native void setRight(boolean right) /*-{
        this.right = right;
    }-*/;

    @Override
    public native boolean isFire() /*-{
        return this. fire;
    }-*/;

    @Override
    public native void setFire(boolean fire) /*-{
        this.fire = fire;
    }-*/;

    @Override
    public native float getAngle() /*-{
        return this. angle;
    }-*/;

    @Override
    public native void setAngle(float angle) /*-{
        this.angle = angle;
    }-*/;

    public native String getType() /*-{
        return this. type;
    }-*/;

    public native void setType(String type) /*-{
        this.type = type;
    }-*/;;
}
