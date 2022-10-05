package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.connection.MessageSender;
import com.game.controls.InputState;
import com.game.controls.KeyboardAdapter;
import com.game.entites.Tank;
import com.game.particles.Emitter;

public class Starter extends ApplicationAdapter {
    private SpriteBatch batch;
    private String meId;
    private ObjectMap<String, Tank> tanks = new ObjectMap<String, Tank>();

    private final KeyboardAdapter inputProcessor;
    private MessageSender messageSender;

    public Starter(InputState inputState) {
        this.inputProcessor = new KeyboardAdapter(inputState);
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        Tank me = new Tank(200, 300);
        tanks.put(meId, me);

    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        // Отображение
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        for (String key : tanks.keys()) {
            Tank tank = tanks.get(key);

            InputState inputState = inputProcessor.getInputState();
            Emitter emitter = tank.emitter;
            emitter.setAngle(inputState.getAngle());
            emitter.getPosition().set(tank.getOrigin());
            if (inputState.isFire()){
                emitter.start(deltaTime);
            }
            emitter.act(deltaTime);
            emitter.render(batch);

            tank.render(batch);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Tank value : tanks.values()) {
            value.dispose();
        }
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void hedleTimer() {
        if (inputProcessor != null && !tanks.isEmpty()) {
            Tank me = tanks.get(meId);
            InputState playerState = inputProcessor.updateAndGetInputState(me.getOrigin());
            messageSender.sendMessage(playerState);
        }
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public void evict(String idToEvict) {
        tanks.remove(idToEvict);
    }

    public void updateTanks(String id, float x, float y, float angle) {
        if (tanks.isEmpty()) return;

        Tank tank = tanks.get(id);
        if (tank == null) {
            tank = new Tank(x, y, "Enemy.png");
            tanks.put(id, tank);
        } else {
            tank.movedTo(x, y);
        }

        tank.rotateTo(angle);
    }
}
