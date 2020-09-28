package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;

public class AnnounceCommand implements ICommand {

    @Override
    public String getName() {
        return "announce";
    }

    @Override
    public String getDescription() {
        return "Announces a message in your current channel";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        if (user.isBot()) return;
        if (!isAdmin(channel, user)) {
            channel.sendMessage(MessageUtil.buildInadequatePermissionMessage()).submit();
            return;
        }
        boolean embed = false;
        try {
            embed = Boolean.parseBoolean(args[1]);
        } catch (Exception exc) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(114, 11, 138), null, null, "Invalid syntax! Use -announce (embed: true/false) (body)\n\n(Tip: Use <everyone> instead of tagging everyone!)")).submit();
            return;
        }
        if (args.length < 3) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(114, 11, 138), null, null, "Invalid syntax! Use -announce (embed: true/false) (body)\n\n(Tip: Use <everyone> instead of tagging everyone!)")).submit();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 2; i < args.length; i++) {
            buffer.append(args[i].replace("<everyone>", "@everyone").replace("-nl-", "\n") + " ");
        }
        channel.sendMessage(embed ? MessageUtil.formattedMessage(new Color(92, 7, 112), null, null, buffer.toString()) : MessageUtil.message(buffer.toString())).submit();
    }
}
