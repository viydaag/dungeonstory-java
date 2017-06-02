package com.dungeonstory.ui.event;

import com.dungeonstory.ui.DungeonStoryUI;

/**
 * Convenience class for accessing the _UI Scoped_ EventBus. If you are using
 * something like the CDI event bus, you don't need a class like this.
 */
public class EventBus {

    public static void register(final Object listener) {
        DungeonStoryUI.getEventBus().register(listener);
    }

    public static void unregister(final Object listener) {
        DungeonStoryUI.getEventBus().unregister(listener);
    }

    public static void post(final Object event) {
        DungeonStoryUI.getEventBus().post(event);
    }
}
