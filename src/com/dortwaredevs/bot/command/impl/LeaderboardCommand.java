package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.util.MessageUtil;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

@AllArgsConstructor
public class LeaderboardCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "leaderboard";
    }

    @Override
    public String getDescription() {
        return "Returns the user with the Highest Level and XP";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        List<Long> users = bot.getLevelTracker().getLeaderboard();
        StringBuffer buffer = new StringBuffer().append("\n");
        try {
            for (int i = 0; i < 10; i++) {
                buffer.append("#" + (i + 1) + " - " + channel.getJDA().getUserById(users.get(i)).getAsMention() + " - " + NumberFormat.getInstance().format(bot.getLevelTracker().getXp(users.get(i))) + "\n");
            }
        } catch (Exception exc) {}
        channel.sendMessage(MessageUtil.formattedMessage(new Color(181, 25, 69), null, null, buffer.toString())).submit();
    }
}
