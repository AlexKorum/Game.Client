package com.game.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class KeyboardAdapter extends InputAdapter {
    // Направление движения и положение мыши
    private Vector2 direction = new Vector2();
    private Vector2 mousePosition = new Vector2();
    private Vector2 angle = new Vector2(1, 1);


    private final InputState inputState;

    public KeyboardAdapter(InputState inputState) {
        this.inputState = inputState;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        inputState.setFire(true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        inputState.setFire(false);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) inputState.setUp(true);
        if (keycode == Input.Keys.S) inputState.setDown(true);
        if (keycode == Input.Keys.A) inputState.setLeft(true);
        if (keycode == Input.Keys.D) inputState.setRight(true);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) inputState.setUp(false);
        if (keycode == Input.Keys.S) inputState.setDown(false);
        if (keycode == Input.Keys.A) inputState.setLeft(false);
        if (keycode == Input.Keys.D) inputState.setRight(false);

        return false;
    }

    public void updateMousePosition() {
        int x = Gdx.input.getX();
        int y = Gdx.graphics.getHeight() - Gdx.input.getY();
        mousePosition.set(x, y);
    }

    public Vector2 getDirection() {
        direction.set(0, 0);

        if (inputState.isUp()) direction.add(0, 1);
        if (inputState.isDown()) direction.add(0, -1);
        if (inputState.isLeft()) direction.add(-1, 0);
        if (inputState.isRight()) direction.add(1, 0);

        return direction;
    }

    public Vector2 getMousePosition() {
        updateMousePosition();
        return mousePosition;
    }

    public InputState updateAndGetInputState(Vector2 playerOrigin) {
        updateMousePosition();
        angle.set(mousePosition).sub(playerOrigin);
        inputState.setAngle(angle.angleDeg());
        return inputState;
    }

    public InputState getInputState() {
        return inputState;
    }
}
