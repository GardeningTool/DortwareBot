package dev.gardeningtool.dortwarebot.command;

import dev.gardeningtool.dortwarebot.DortwareBot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public abstract class Command {

    protected DortwareBot dortwareBot;
    protected String name;

    public abstract void execute(User user, String[] args, TextChannel textChannel);

    private final String userFromString(String input) {
        return input.replace("<", "").replace("!", "").replace(">", "");
    }
}
