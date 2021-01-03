package dev.gardeningtool.dortwarebot.command;

import dev.gardeningtool.dortwarebot.DortwareBot;
import dev.gardeningtool.dortwarebot.command.impl.BetaCommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;
import java.util.List;

public class CommandManager {

    private DortwareBot dortwareBot;
    private List<Command> commands;

    public CommandManager(DortwareBot dortwareBot) {
        this.dortwareBot = dortwareBot;
        commands = Arrays.asList(
                new BetaCommand(dortwareBot, "beta")
        );
    }

    public void onCommand(String command, User user, String[] args, TextChannel textChannel) {
        commands.stream().filter(cmd -> cmd.getName().equalsIgnoreCase(command)).forEach(cmd -> cmd.execute(user, args, textChannel));
    }
}
