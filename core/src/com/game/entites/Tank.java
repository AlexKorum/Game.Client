package com.game.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tank {
    // Технические данные объекта
    private final float size = 64;
    private final float halfSize = size / 2;

    private final Texture texture;
    private final TextureRegion textureRegion;


    // Физические параметры объекта
    private final Vector2 position = new Vector2();
    private final Vector2 angle = new Vector2();
    private final Vector2 origin = new Vector2();
    private final float speed = 15f;


    public Tank(float x, float y) {
        this(x, y, "Tank.png");
    }

    public Tank(float x, float y, String textureUrl) {
        texture = new Texture(textureUrl);
        textureRegion = new TextureRegion(texture);
        position.set(x, y);
        origin.set(position).add(halfSize, halfSize);
        angle.set(1, 1);
    }

    public void render(Batch batch) {
        batch.draw(
                textureRegion,
                position.x,
                position.y,
                halfSize,
                halfSize,
                size,
                size,
                1,
                1,
                angle.angleDeg()
        );
    }


    public void dispose() {
        texture.dispose();
    }

    public void movedTo(Vector2 direction) {
        position.mulAdd(direction, speed);
        if (position.x < 0) position.set(0, position.y);
        if (position.x > Gdx.graphics.getWidth() - size) position.set(Gdx.graphics.getWidth() - size, position.y);
        if (position.y < 0) position.set(position.x, 0);
        if (position.y > Gdx.graphics.getHeight() - size) position.set(position.x, Gdx.graphics.getHeight() - size);
        origin.set(position).add(halfSize, halfSize);
    }

    public void rotateTo(Vector2 mousePosition) {
        angle.set(mousePosition).sub(origin);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void movedTo(float x, float y) {
        position.set(x, y);
        origin.set(position).add(halfSize, halfSize);
    }

    public void rotateTo(float angle) {
        this.angle.setAngleDeg(angle);
    }
}
