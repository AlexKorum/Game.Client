package com.game.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class KeyboardAdapter extends InputAdapter {

    // Нажатие н кнопки
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean mouse;

    // Направление движения и положение мыши
    private Vector2 direction = new Vector2();
    private Vector2 mousePosition = new Vector2(1, 1);

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) up = true;
        if (keycode == Input.Keys.S) down = true;
        if (keycode == Input.Keys.A) left = true;
        if (keycode == Input.Keys.D) right = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) up = false;
        if (keycode == Input.Keys.S) down = false;
        if (keycode == Input.Keys.A) left = false;
        if (keycode == Input.Keys.D) right = false;

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }

    public Vector2 getDirection() {
        direction.set(0, 0);

        if (up) direction.add(0, 1);
        if (down) direction.add(0, -1);
        if (left) direction.add(-1, 0);
        if (right) direction.add(1, 0);

        return direction;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }


}
