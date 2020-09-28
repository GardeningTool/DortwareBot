package com.dortwaredevs.bot.event.impl;

import com.dortwaredevs.bot.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@AllArgsConstructor
@Getter
public class ServerMessageEvent extends Event {

    /**
     * THe user who sent the message
     */
    private User sender;

    /**
     * The message sent
     */
    private String message;

    /**
     * The channel which the message is sent in
     */
    private  TextChannel channel;
}
