package com.dortwaredevs.bot;

import java.io.File;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.dortwaredevs.bot.config.ConfigManager;
import com.dortwaredevs.bot.event.EventListener;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Getter
public class Bot {
	
	private ConfigManager configManager;
	private EventListener eventListener;
	private JDA jda;
	private final String TOKEN = "token";
	private final String STATUS = "Buy dortware on https://intent.store!";
	
	public Bot() throws LoginException, IOException 
	{
		configManager = new ConfigManager();
		jda = new JDABuilder().setToken(TOKEN).setActivity(Activity.watching(STATUS))
				.addEventListeners(eventListener = new EventListener(this)).build();
	}
}
