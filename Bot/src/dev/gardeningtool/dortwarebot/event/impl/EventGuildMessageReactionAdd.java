package dev.gardeningtool.dortwarebot.event.impl;

import dev.gardeningtool.dortwarebot.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public class EventGuildMessageReactionAdd extends Event {

    private User user;
    private Member member;
    private MessageReaction messageReaction;
    private long messageId;
    private TextChannel channel;

}
