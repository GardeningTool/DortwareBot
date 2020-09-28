package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

@AllArgsConstructor
public class PingCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Gets the latency of the bot to the Discord endpoint";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        channel.sendMessage(MessageUtil.formattedMessage(new Color(252, 186, 3), null, null, bot.getJda().getGatewayPing() + "ms")).submit();
    }
}
