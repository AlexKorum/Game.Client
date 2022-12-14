package com.game.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pool;

public class Emitter {
    private String owner;
    private final Vector2 position = new Vector2();
    private float speed = 500f;
    private float angle = 0;
    private float rate = 10f;
    private float distance = 600f;
    private float size = 16;
    private float lastParticleEmit = 0;

    private Texture particleTexture = new Texture("Bullet.png");
    private float halfSizeTexture = particleTexture.getHeight() / 2f;
    private final DelayedRemovalArray<Particle> particles = new DelayedRemovalArray<Particle>();
    private final Pool<Particle> particlePool = new Pool<Particle>() {
        @Override
        protected Particle newObject() {
            return new Particle();
        }
    };

    public void start(float delta) {
        lastParticleEmit += delta;
        if (lastParticleEmit <= 1 / rate) return;
        lastParticleEmit = 0;
        Particle particle = particlePool.obtain();
        particle.fill(owner, position, angle, size, speed, distance);
        particles.add(particle);
    }

    public void act(float delta) {
        particles.begin();
        for (Particle particle : particles) {
            particle.act(delta);
            if (particle.isFinished()) {
                particles.removeValue(particle, true);
                particlePool.free(particle);
            }
        }
        particles.end();
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void render(Batch batch) {
        for (Particle particle : particles) {
            Vector2 particlePosition = particle.getPosition();
            batch.draw(particleTexture, particlePosition.x - halfSizeTexture, particlePosition.y - halfSizeTexture, size, size);
        }
    }
}
