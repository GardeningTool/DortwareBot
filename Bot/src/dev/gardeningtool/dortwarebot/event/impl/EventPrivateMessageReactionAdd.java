package dev.gardeningtool.dortwarebot.event.impl;

import dev.gardeningtool.dortwarebot.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public class EventPrivateMessageReactionAdd extends Event {

    private User user;
    private PrivateChannel privateChannel;
    private MessageReaction messageReaction;
    private long messageId;

}
