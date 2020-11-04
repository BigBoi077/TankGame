package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;

public class Tank extends ControllableEntity {

    private int fireCooldown;

    public Tank(MovementController controller) {
        super(controller);
        super.setDimensions(30, 30);
        super.setSpeed(2);
        super.teleport(100, 100);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public Missile fire() {
        fireCooldown = 10;
        return new Missile(this);
    }

    public boolean canFire() {
        return fireCooldown == 0;
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        fireCooldown--;
        if (fireCooldown <= 0) {
            fireCooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, height, width, Color.GREEN);
        if (hasMoved()) {
            drawHitBox(buffer);
        }
    }
}
