package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.type.rounding.Rounding;
import ru.terraria.type.rounding.RoundingAtlas;

import java.util.Arrays;
import java.util.HashMap;

public class RoundingBlock extends Block {
    private RoundingType roundingType;

    public RoundingBlock(String name) {
        super(name);

        roundingType = new RoundingType(this);
    }

    public RoundingType getRoundingType() {
        return roundingType;
    }

    @Override
    public void draw(SpriteBatch batch, Tile tile) {
        batch.draw(tile.getBlockRounding().getCurrentTexture(),
                tile.getPosition().x * Vars.TILE_SIZE,
                tile.getPosition().y * Vars.TILE_SIZE,
                Vars.TILE_SIZE,
                Vars.TILE_SIZE);
    }

    public static class RoundingType {
        public final RoundingAtlas roundingAtlas;
        public HashMap<String, RoundingRule> roundingRules = new HashMap<>();
        public Array<String> names = new Array<>();
        public RoundingBlock block;

        public RoundingType(RoundingBlock roundingBlock) {
            this.block = roundingBlock;
            roundingAtlas = new RoundingAtlas();

            names.add("none");
            RoundingRule roundingRule = new RoundingRule();
            roundingRule.noup = true;
            roundingRule.noleft = true;
            roundingRule.nodown = true;
            roundingRule.noright = true;
            roundingRules.put("none", roundingRule);

            names.add("down");
            roundingRule = new RoundingRule();
            roundingRule.down = true;
            roundingRules.put("down", roundingRule);

            names.add("up");
            roundingRule = new RoundingRule();
            roundingRule.up = true;
            roundingRules.put("up", roundingRule);

            names.add("left");
            roundingRule = new RoundingRule();
            roundingRule.left = true;
            roundingRules.put("left", roundingRule);

            names.add("right");
            roundingRule = new RoundingRule();
            roundingRule.right = true;
            roundingRules.put("right", roundingRule);

            names.add("leftUp");
            roundingRule = new RoundingRule();
            roundingRule.up = true;
            roundingRule.left = true;
            roundingRules.put("leftUp", roundingRule);

            names.add("rightUp");
            roundingRule = new RoundingRule();
            roundingRule.up = true;
            roundingRule.right = true;
            roundingRules.put("rightUp", roundingRule);

            names.add("leftDown");
            roundingRule = new RoundingRule();
            roundingRule.down = true;
            roundingRule.left = true;
            roundingRules.put("leftDown", roundingRule);

            names.add("rightDown");
            roundingRule = new RoundingRule();
            roundingRule.down = true;
            roundingRule.right = true;
            roundingRules.put("rightDown", roundingRule);

            names.add("all");
            roundingRule = new RoundingRule();
            roundingRule.up = true;
            roundingRule.down = true;
            roundingRule.right = true;
            roundingRule.left = true;
            roundingRules.put("all", roundingRule);

            fillRoundingAtlas();
        }

        private void fillRoundingAtlas() {
            Vars.atlas.fillRoundingAtlas(roundingAtlas, this, block.getName(), "sprite/block/");
        }

        public void update(final Rounding rounding) {
            for (int i = 0; i < names.size; i++) {
                RoundingRule roundingRule = roundingRules.get(names.get(i));
                final int finalI = i;
                roundingRule.done(new Runnable() {
                    @Override
                    public void run() {
                        rounding.setCurrentTexture(roundingAtlas.getTextures().get(names.get(finalI)));
                    }
                }, rounding.getTile(), rounding.getTilemap());
            }
        }

        public static class RoundingRule {
            public boolean
                    leftUp, up, rightUp,
                    left, none, right,
                    leftDown, down, rightDown;

            public boolean
                    noleftUp, noup, norightUp,
                    noleft, nonone, noright,
                    noleftDown, nodown, norightDown;

            private Tiles tiles;

            public void done(Runnable onDone, Tile tile, Tiles tiles) {
                int x = (int) tile.getPosition().x;
                int y = (int) tile.getPosition().y;
                this.tiles = tiles;

                boolean done = false;

                if (up) {
                    done = getTile(x, y + 1).getBlock() == Blocks.air;
                    if (!done) return;
                }

                if (noup) {
                    done = getTile(x, y + 1).getBlock() != Blocks.air;
                    if (!done) return;
                }

                if (left) {
                    done = getTile(x - 1, y).getBlock() == Blocks.air;
                    if (!done) return;
                }

                if (noleft) {
                    done = getTile(x - 1, y).getBlock() != Blocks.air;
                    if (!done) return;
                }

                if (right) {
                    done = getTile(x + 1, y).getBlock() == Blocks.air;
                    if (!done) return;
                }

                if (noright) {
                    done = getTile(x + 1, y).getBlock() != Blocks.air;
                    if (!done) return;
                }

                if (down) {
                    done = getTile(x, y - 1).getBlock() == Blocks.air;
                    if (!done) return;
                }

                if (nodown) {
                    done = getTile(x, y - 1).getBlock() != Blocks.air;
                    if (!done) return;
                }

                if (done) onDone.run();
            }

            //return clear air Tile if xy not in world space and tile block isRounding() false
            public Tile getTile(int x, int y) {
                if (!tiles.inBounds(new Vector2(x, y))) return new Tile(new Vector2(x, y));
                if (!tiles.get(x, y).getBlock().isRounding()) return new Tile(new Vector2(x, y));
                return tiles.get(x, y);
            }
        }
    }
}
