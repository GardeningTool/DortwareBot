package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

@AllArgsConstructor
public class MediaCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "addmedia";
    }

    @Override
    public String getDescription() {
        return "Add a video to the media channel";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        if (!isAdmin(channel, user) && !bot.getPermissionManager().getUser(user.getIdLong()).hasPermission("media.add")) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(3, 252, 182), "Insufficient permission!", null, "You don't have permission to do this!")).submit();
            return;
        }
        if (args.length != 2) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(3, 252, 182), "Improper usage!", null, "Improper usage!\nCorrect Usage: -addmedia (link)")).submit();
            return;
        }
        for (TextChannel tch : channel.getGuild().getTextChannels()) {
            if (tch.getName().contains("media")) {
                tch.sendMessage(MessageUtil.message("<@" + user.getIdLong() + "> sumbitted a video!\n" + args[1])).submit();
                channel.sendMessage(MessageUtil.formattedMessage(new Color(3, 252, 182), null, null, "Media posted!")).submit();
                return;
            }
        }
        channel.sendMessage(MessageUtil.formattedMessage(new Color(3, 252, 182), "Error", null, "No media channels found!")).submit();
    }
}
