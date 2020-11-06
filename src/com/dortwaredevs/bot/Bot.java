package com.dortwaredevs.bot;

import com.dortwaredevs.bot.command.CommandManager;
import com.dortwaredevs.bot.command.impl.*;
import com.dortwaredevs.bot.config.ConfigManager;
import com.dortwaredevs.bot.event.bus.EventBus;
import com.dortwaredevs.bot.event.listener.EventListener;
import com.dortwaredevs.bot.listener.ChatListener;
import com.dortwaredevs.bot.listener.CommandListener;
import com.dortwaredevs.bot.permission.PermissionManager;
import com.dortwaredevs.bot.tracker.LevelTracker;

import lombok.Getter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;

@Getter
public class Bot {

	/**
	 * The manager for the config module.
	 */
	private ConfigManager configManager;

	/**
	 * The main event listener that passes events to the EventBus
	 */
	private EventListener eventListener;

	/**
	 * The event system that passes events to listeners
	 */
	private EventBus eventBus;

	/**
	 * The command manager - Handles command registration and handles commands when a user sends a message
	 */
	private CommandManager commandManager;

	/**
	 * Manages user permissions. Permissions are similar to Minecraft plugin permissions.
	 */
	private PermissionManager permissionManager;

	/**
	 * Tracks user leveling and XP
	 */
	private LevelTracker levelTracker;

	/**
	 * The JDA instance
	 */
	private JDA jda;

	/**
	 * The bot token
	 */
	private final String TOKEN = "token";

	/**
	 * The bot status
	 */
	private final String STATUS = "Balli obfuscate Dortware on intent.store";

	public Bot() throws LoginException, IOException 
	{
		configManager = new ConfigManager();
		eventBus = new EventBus();
		commandManager = new CommandManager();
		permissionManager = new PermissionManager();
		levelTracker = new LevelTracker();
		jda = new JDABuilder().setToken(TOKEN).setActivity(Activity.watching(STATUS))
				.addEventListeners(eventListener = new EventListener(eventBus)).build();
		registerEvents();
		registerCommands();
	}

	/**
	 * Register the events
	 */
	private void registerEvents() {
		eventBus.subscribe(new CommandListener(this));
		eventBus.subscribe(new ChatListener(this));
	}

	/**
	 * Register the commands
	 */
	private void registerCommands() {
		commandManager.registerCommand(new ConfigCommand(this));
		commandManager.registerCommand(new PingCommand(this));
		commandManager.registerCommand(new MediaCommand(this));
		commandManager.registerCommand(new PermissionAddCommand(this));
		commandManager.registerCommand(new LevelCommand(this));
		commandManager.registerCommand(new LeaderboardCommand(this));
		commandManager.registerCommand(new AnnounceCommand());
	}
}
