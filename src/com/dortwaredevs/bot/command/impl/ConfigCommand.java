package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@AllArgsConstructor
public class ConfigCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "config";
    }

    @Override
    public String getDescription() {
        return "Lists valid configurations for different servers";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        if (args.length == 0) {
            channel.sendMessage(MessageUtil.message(bot.getConfigManager().getConfigs())).submit();
            return;
        }
        String server = args[1];
        try {
            channel.sendMessage(bot.getConfigManager().getConfig(server).buildMessage()).submit();
        } catch (NullPointerException exc) {
            channel.sendMessage(MessageUtil.message("Invalid config!")).submit();
        }

    }
}
