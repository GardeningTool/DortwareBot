package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.permission.PermissionUser;
import com.dortwaredevs.bot.util.MessageUtil;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class PermissionRemoveCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "removeperm";
    }

    @Override
    public String getDescription() {
        return "Remove a permission from a user";
    }

    @Override
    public void onCommand(String[] args, User user, TextChannel channel) {
        if (!isAdmin(channel, user)) {
            channel.sendMessage(MessageUtil.buildInadequatePermissionMessage()).submit();
            return;
        }
        try {
            long id = Long.parseLong(args[1]);
            PermissionUser permissionUser = bot.getPermissionManager().getUser(id);
            String permission = args[2];
            permissionUser.removePermission(permission);
            channel.sendMessage(MessageUtil.formattedMessage(new Color(66, 135, 245), "Success", null, "Removed the permission " + permission + " from <@" + id + ">'s permissions")).submit();
        } catch (Exception exc) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(66, 135, 245), "❌ Improper usage!", null,  "Improper usage! Correct Usage: -removeperm (id) (permission)")).submit();
        }
    }
}
