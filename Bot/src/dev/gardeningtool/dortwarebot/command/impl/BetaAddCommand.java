package dev.gardeningtool.dortwarebot.command.impl;

import dev.gardeningtool.dortwarebot.DortwareBot;
import dev.gardeningtool.dortwarebot.command.Command;
import dev.gardeningtool.dortwarebot.util.MessageUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class BetaAddCommand extends Command {

    public BetaAddCommand(DortwareBot dortwareBot, String name) {
        super(dortwareBot, name);
    }

    @Override
    public void execute(User user, String[] args, TextChannel textChannel) {
        if (user.getIdLong() != 726179896333697184L) {
            if (textChannel == null) {
                user.openPrivateChannel().complete().sendMessage(MessageUtil.INSUFFICIENT_PERMISSION).submit();
            } else {
                textChannel.sendMessage(MessageUtil.INSUFFICIENT_PERMISSION).submit();
            }
            return;
        }
        if (args.length != 1) {
            Message message = MessageUtil.formattedMessage(Color.RED, "Invalid syntax!", null, "Try ?betaadd <user>");
            if (textChannel == null) {
                user.openPrivateChannel().complete().sendMessage(message).submit();
            } else {
                textChannel.sendMessage(message).submit();
            }
            return;
        }
    }
}
