package dev.gardeningtool.dortwarebot.listener;

import dev.gardeningtool.dortwarebot.event.impl.EventBotStartup;

public class TestListener extends Listener {

    @EventHandler
    public void onBotStartup(EventBotStartup eventBotStartup) {
        System.out.println("Event received!");
    }
}
