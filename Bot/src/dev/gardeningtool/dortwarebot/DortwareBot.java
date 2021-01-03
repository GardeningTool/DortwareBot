package dev.gardeningtool.dortwarebot;

import dev.gardeningtool.dortwarebot.command.CommandManager;
import dev.gardeningtool.dortwarebot.config.DortwareConfiguration;
import dev.gardeningtool.dortwarebot.download.BetaDownloadManager;
import dev.gardeningtool.dortwarebot.event.EventBus;
import dev.gardeningtool.dortwarebot.event.impl.EventBotStartup;
import dev.gardeningtool.dortwarebot.listener.jda.JDAListener;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@Getter
public class DortwareBot {

    //Contains the token and status

    private BetaDownloadManager betaDownloadManager;
    private CommandManager commandManager;
    private DortwareConfiguration dortwareConfiguration;
    private EventBus eventBus;
    private JDA jda;

    public DortwareBot() throws LoginException {

        long time = System.currentTimeMillis();

        commandManager = new CommandManager(this);
        dortwareConfiguration = new DortwareConfiguration();
        betaDownloadManager = new BetaDownloadManager();
        eventBus = new EventBus(this);
        jda = new JDABuilder()
                .setToken(dortwareConfiguration.getToken())
                .setActivity(Activity.playing(dortwareConfiguration.getStatus()))
                .addEventListeners(new JDAListener(eventBus))
                .build();

        eventBus.post(new EventBotStartup(time, System.currentTimeMillis()));
    }
}
