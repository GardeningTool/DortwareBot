package com.dortwaredevs.bot.config;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.dortwaredevs.bot.util.MessageUtil;

import net.dv8tion.jda.api.entities.Message;

public class Config {
	
	private String configName;
	private HashMap<String, String> modules;
	
	public Config(File file) throws IOException {
		configName = file.getName().replace(".config", "");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		modules = new HashMap<>();
		String line = "";
		while ((line = reader.readLine()) != null) {
			String[] args = line.split(" - ");
			modules.put(args[0], args[1]);
		}
		reader.close();
	}
	
	public Message buildMessage() {
		StringBuffer bodyBuffer = new StringBuffer();	
		modules.forEach((module, mode) -> {
			bodyBuffer.append(module + " - " + mode + "\n");
		});
		return MessageUtil.formattedMessage(new Color(168, 50, 50), configName, "", bodyBuffer.toString());
	}
}
