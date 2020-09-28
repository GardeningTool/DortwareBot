package com.dortwaredevs.bot.event.impl;

import com.dortwaredevs.bot.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

@Getter
@AllArgsConstructor
public class MemberQuitEvent extends Event {

    /**
     * The user who left the server
     */
    private User user;

    /**
     * The server which they left
     */
    private Guild server;
}
