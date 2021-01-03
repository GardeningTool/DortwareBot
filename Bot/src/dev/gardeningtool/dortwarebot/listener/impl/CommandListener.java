package dev.gardeningtool.dortwarebot.listener.impl;

import dev.gardeningtool.dortwarebot.command.CommandManager;
import dev.gardeningtool.dortwarebot.config.DortwareConfiguration;
import dev.gardeningtool.dortwarebot.event.impl.EventGuildMessageReceived;
import dev.gardeningtool.dortwarebot.event.impl.EventPrivateMessageReceived;
import dev.gardeningtool.dortwarebot.listener.EventHandler;
import dev.gardeningtool.dortwarebot.listener.Listener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public class CommandListener extends Listener {

    private CommandManager commandManager;

    @EventHandler
    public void onGuildMessage(EventGuildMessageReceived event) {
        checkIfValid(event.getUser(), event.getMessage().getContentRaw(), event.getTextChannel());
    }

    @EventHandler
    public void onPrivateMessage(EventPrivateMessageReceived event) {
        checkIfValid(event.getAuthor(), event.getMessage().getContentRaw(), null);
    }

    private void checkIfValid(User user, String rawMessage, TextChannel textChannel) {
        if (user.isBot() || !rawMessage.startsWith("?")) return;
        String[] args = rawMessage.contains(" ") ? rawMessage.split(" ") : new String[]{rawMessage};
        commandManager.onCommand(args[0].replace("?", ""), user, args, textChannel);

    }
}
