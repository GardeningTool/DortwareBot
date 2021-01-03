package dev.gardeningtool.dortwarebot.command.impl;

import dev.gardeningtool.dortwarebot.DortwareBot;
import dev.gardeningtool.dortwarebot.command.Command;
import dev.gardeningtool.dortwarebot.util.MessageUtil;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class BetaCommand extends Command {

    public BetaCommand(DortwareBot dortwareBot, String name) {
        super(dortwareBot, name);
    }

    @Override
    public void execute(User user, String[] args, TextChannel textChannel) {
        if (textChannel != null) {
            textChannel.sendMessage(MessageUtil.formattedMessage(MessageUtil.RED, "Invalid channel", null,
                    "That command can only be executed in DMs!")).submit();
            return;
        }
        dortwareBot.getBetaDownloadManager().requestBeta(user);

    }
}
