package dev.gardeningtool.dortwarebot.event.impl;

import dev.gardeningtool.dortwarebot.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public class EventUserUpdateDiscriminator extends Event {

    private User user;
    private String oldDiscriminator, newDiscriminator;
}
