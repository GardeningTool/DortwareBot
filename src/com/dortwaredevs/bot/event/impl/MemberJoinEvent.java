package com.dortwaredevs.bot.event.impl;

import com.dortwaredevs.bot.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

@Getter
@AllArgsConstructor
public class MemberJoinEvent extends Event {

    /**
     * The user who joined the server
     */
    private User user;

    /**
     * The server which they joined
     */
    private Guild server;
}
