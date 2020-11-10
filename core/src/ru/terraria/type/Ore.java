package ru.terraria.type;

public class Ore extends Block {

    private int spawnRate;

    public Ore(String name) {
        super(name);
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }
}
