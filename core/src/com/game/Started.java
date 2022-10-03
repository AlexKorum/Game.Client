package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.controls.KeyboardAdapter;
import com.game.entites.Tank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Started extends ApplicationAdapter {
    private SpriteBatch batch;
    private Tank me;


    private KeyboardAdapter inputProcessor = new KeyboardAdapter();

    @Override
    public void create() {
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        me = new Tank(200, 300);


    }

    @Override
    public void render() {
        // Движение
        me.movedTo(inputProcessor.getDirection());
        me.rotateTo(inputProcessor.getMousePosition());

        // Отображение
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();

        me.render(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        me.dispose();
    }
}
