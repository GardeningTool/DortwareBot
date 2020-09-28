package com.dortwaredevs.bot.command.impl;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.command.ICommand;
import com.dortwaredevs.bot.permission.PermissionUser;
import com.dortwaredevs.bot.util.MessageUtil;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

@AllArgsConstructor
public class PermissionAddCommand implements ICommand {

    private Bot bot;

    @Override
    public String getName() {
        return "addperm";
    }

    @Override
    public String getDescription() {
        return "Add a permission to a user";
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
            permissionUser.addPermission(permission);
            channel.sendMessage(MessageUtil.formattedMessage(new Color(66, 135, 245), "Success", null, "Added " + permission + " to <@" + id + ">'s permissions")).submit();
        } catch (Exception exc) {
            channel.sendMessage(MessageUtil.formattedMessage(new Color(66, 135, 245), "Improper usage!", null,  "Improper usage! Try -addperm (id) (permission)")).submit();
        }
    }
}
