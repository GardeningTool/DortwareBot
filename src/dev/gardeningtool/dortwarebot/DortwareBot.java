package dev.gardeningtool.dortwarebot;

import dev.gardeningtool.dortwarebot.config.DortwareConfiguration;

public class DortwareBot {

    private DortwareConfiguration dortwareConfiguration;

    public DortwareBot() {
        dortwareConfiguration = new DortwareConfiguration();
        System.out.println(dortwareConfiguration.getToken());
    }
}
