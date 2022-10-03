package com.game.controls;

public interface InputState {
    boolean isUp();

    void setUp(boolean up);

    boolean isDown();

    void setDown(boolean down);

    boolean isLeft();

    void setLeft(boolean left);

    boolean isRight();

    void setRight(boolean right);

    boolean isFire();

    void setFire(boolean fire);

    float getAngle();

    void setAngle(float angle);
}
