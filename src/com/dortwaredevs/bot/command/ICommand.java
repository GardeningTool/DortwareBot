package com.dortwaredevs.bot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface ICommand {

    /**
     * @return The name of the command
     */

    public String getName();

    /**
     * @return The description of the command
     */
    public String getDescription();

    /**
     * Handles command execution
     * @param args The command arguments
     * @param user The user who sent the message
     * @param channel The channel which the message is sent in
     */
    public void onCommand(String[] args, User user, TextChannel channel);

    /**
     *
     * @param channel The channel to get the guild of in order to find out if the user has administrator permissions
     * @param user The user to check
     * @return
     */
    default boolean isAdmin(TextChannel channel, User user) {
        for (Role role : channel.getGuild().getMember(user).getRoles()) {
            if (role.hasPermission(Permission.ADMINISTRATOR)) return true;
        }
        return false;
    }
}
