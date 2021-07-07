package ru.terraria;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class Event {
    private static HashMap<Object, ArrayList<Runnable>> tasks = new HashMap<>();

    public static <T> void on(Class<T> type, Runnable runnable) {
        if (!tasks.containsKey(type)) tasks.put(type, new ArrayList<Runnable>());
        tasks.get(type).add(runnable);
    }

    public static <T> void fire(Class<T> type) {
        ArrayList<Runnable> runnables = tasks.get(type);
        for (int i = 0; i < runnables.size(); i++) {
            runnables.get(i).run();
        }
    }

}
