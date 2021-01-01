package dev.gardeningtool.dortwarebot.event.impl;

import dev.gardeningtool.dortwarebot.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class EventBotStartup extends Event {

    private long start, end;

    public long getTimeElapsed() {
        return end - start;
    }
}
