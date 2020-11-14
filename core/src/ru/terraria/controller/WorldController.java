package ru.terraria.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.Block;
import ru.terraria.type.Tile;
import ru.terraria.type.World;

public class WorldController implements InputProcessor {
    private World world;
    private GameScreen screen;

    public WorldController(World world, GameScreen screen) {
        this.world = world;
        this.screen = screen;
    }

    public void update(float delta) {
        world.getPlayer().update(delta);

        processInput();
        checkPlayerGrounded();
        processGravity();
        processCollisions();

        world.setBlocksNeighbourAir();
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.getPlayer().getVelocity().x += world.getPlayer().getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.getPlayer().getVelocity().x -= world.getPlayer().getSpeed();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && world.getPlayer().isGrounded()) {
            world.getPlayer().jump();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screen.inventory = !screen.inventory;
        }

        int cX = (int)(Gdx.input.getX() + (screen.getRenderer().getCamera().position.x - Gdx.graphics.getWidth() / 2));
        int cY = (int)(Vars.CAMERA_HEIGHT - Gdx.input.getY() + (screen.getRenderer().getCamera().position.y - Gdx.graphics.getHeight() / 2));

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if ((cX / 16) > 0 && (cY / 16) > 0 && (cX / 16) < world.getWidth() && (cY / 16) < world.getHeight()) {
                world.getTiles().get(cY / 16, cX / 16).setBlock(Blocks.air); //need add health to block
            }
        }
    }

    public void checkPlayerGrounded() {
        int count = 0;

        for (int i = 0; i < world.getTiles().getArray().length; i++) {

            Tile tile = world.getTiles().getArray()[i];

            if (world.getPlayer().getGroundHitBox().overlaps(tile.getBounds()) && tile.getBlock() != Blocks.air) {
                count += 1;
            }
        }

        if (count > 0) {
            world.getPlayer().setGrounded(true);
        } else {
            world.getPlayer().setGrounded(false);
        }
    }

    public void processCollisions() {
        for (int i = 0; i < world.getTiles().getArray().length; i++) {
            if (
                world.getTiles().getArray()[i].getBounds().overlaps(world.getPlayer().getBounds()) &&
                world.getTiles().getArray()[i].getBlock() != Blocks.air)
            {
                world.getPlayer().getPosition().set(world.getPlayer().getOldPosition());
            }
        }
    }

    public void processGravity() {
        if (!world.getPlayer().isGrounded()) {
            world.getPlayer().getPosition().y -= 0.5f;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
