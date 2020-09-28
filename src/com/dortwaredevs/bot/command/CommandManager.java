package com.dortwaredevs.bot.command;

import lombok.Getter;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {

    /**
     * The list of registered ommands
     */
    private List<ICommand> commands;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    /**
     * Register a new command
     * @param command The command to be registered
     */
    public void registerCommand(ICommand command) {
        commands.add(command);
    }

    /**
     *
     * @param command The command name executed
     * @param args The command arguments array, split at the first space
     * @param sender The user who sent the command
     */
    public void handleCommand(String command, String[] args, User sender, TextChannel channel) {
        for (ICommand cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(command)) {
                cmd.onCommand(args, sender, channel);
            }
        }
    }
}
