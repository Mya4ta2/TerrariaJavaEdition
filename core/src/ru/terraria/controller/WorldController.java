package ru.terraria.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Cursor;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.content.Items;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.Block;
import ru.terraria.type.Pickaxe;
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
                if (Cursor.itemStack.getItem() instanceof Pickaxe) {
                    world.getTiles().get(cY / 16, cX / 16).setBlock(Blocks.air); //need add health to block
                }
            }
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            if ((cX / 16) > 0 && (cY / 16) > 0 && (cX / 16) < world.getWidth() && (cY / 16) < world.getHeight()) {
                if (Cursor.itemStack != null && Cursor.itemStack.getItem().isPlaceble() && Cursor.itemStack.getItemsCount() > 0) {
                    world.getTiles().get(cY / 16, cX / 16).setBlock(Cursor.itemStack.getItem().getPlacebleBlock());
                    Cursor.itemStack.setItemsCount(Cursor.itemStack.getItemsCount()-1);
                }
            }
        }
    }

    public void checkPlayerGrounded() {
        int count = 0;

        Vector2 pos = world.getPlayer().getPosition();

        Tile[] nearTiles = {
                world.getTiles().get((int) pos.y, (int) pos.x),
                world.getTiles().get((int) pos.y, (int) pos.x - 1),
                world.getTiles().get((int) pos.y, (int) pos.x + 1)
        };

        for (int i = 0; i < nearTiles.length; i++) {
            if (world.getPlayer().getGroundHitBox().overlaps(nearTiles[i].getBounds()) && nearTiles[i].getBlock() != Blocks.air) {
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
        boolean collision = false;

        Vector2 pos = world.getPlayer().getPosition();

        Tile left1Tile = world.getTiles().get((int) pos.y, (int) pos.x);
        Tile left2Tile = world.getTiles().get((int) pos.y + 1, (int) pos.x);
        Tile left3Tile = world.getTiles().get((int) pos.y + 2, (int) pos.x);
        Tile right1Tile = world.getTiles().get((int) pos.y, (int) pos.x + 1);
        Tile right2Tile = world.getTiles().get((int) pos.y + 1, (int) pos.x + 1);
        Tile right3Tile = world.getTiles().get((int) pos.y + 2, (int) pos.x + 1);

        collision =
        (
                left1Tile.getBlock() != Blocks.air &&
                        world.getPlayer().getBounds().overlaps(left1Tile.getBounds()) ||
                left2Tile.getBlock() != Blocks.air &&
                        world.getPlayer().getBounds().overlaps(left2Tile.getBounds()) ||
                left3Tile.getBlock() != Blocks.air &&
                        world.getPlayer().getBounds().overlaps(left3Tile.getBounds()) ||
                right1Tile.getBlock() != Blocks.air &&
                        world.getPlayer().getBounds().overlaps(right1Tile.getBounds()) ||
                right2Tile.getBlock() != Blocks.air &&
                        world.getPlayer().getBounds().overlaps(right2Tile.getBounds()) ||
                right3Tile.getBlock() != Blocks.air &&
                world.getPlayer().getBounds().overlaps(right3Tile.getBounds())
        );

        if (collision) {
            world.getPlayer().getPosition().set(world.getPlayer().getOldPosition());
        }

    }

    public void processGravity() {
        if (!world.getPlayer().isGrounded()) {
            world.getPlayer().getPosition().y -= 0.5f;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_0: screen.getRenderer().getFastSlotBar().setSelectedSlot(9); break;
            case Input.Keys.NUM_1: screen.getRenderer().getFastSlotBar().setSelectedSlot(0); break;
            case Input.Keys.NUM_2: screen.getRenderer().getFastSlotBar().setSelectedSlot(1); break;
            case Input.Keys.NUM_3: screen.getRenderer().getFastSlotBar().setSelectedSlot(2); break;
            case Input.Keys.NUM_4: screen.getRenderer().getFastSlotBar().setSelectedSlot(3); break;
            case Input.Keys.NUM_5: screen.getRenderer().getFastSlotBar().setSelectedSlot(4); break;
            case Input.Keys.NUM_6: screen.getRenderer().getFastSlotBar().setSelectedSlot(5); break;
            case Input.Keys.NUM_7: screen.getRenderer().getFastSlotBar().setSelectedSlot(6); break;
            case Input.Keys.NUM_8: screen.getRenderer().getFastSlotBar().setSelectedSlot(7); break;
            case Input.Keys.NUM_9: screen.getRenderer().getFastSlotBar().setSelectedSlot(8); break;

            //this code is 'oh no' =( in future i remake this =)

        }
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
        screen.getRenderer().getFastSlotBar().setScroll(amountY);
        return false;
    }
}
