package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;

import lombok.AllArgsConstructor;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.text.NumberFormat;

@AllArgsConstructor
public class LevelCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "level";
    }

    @Override
    public String getDescription() {
        return "Gets your current level";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        channel.sendMessage(MessageUtil.formattedMessage(new Color(181, 168, 25), "Stats for " + user.getName(), null,
                "**Level** - " + bot.getLevelTracker().getLevel(user.getIdLong()) + "\n" + "**Total XP** - " + NumberFormat.getInstance().format(bot.getLevelTracker().getXp(user.getIdLong())))).submit();
    }
}
