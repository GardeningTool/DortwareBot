package com.dortwaredevs.bot.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {
	
	private HashMap<String, Config> configs;
	
	public ConfigManager() throws IOException {
		configs = new HashMap<>();
		File configDirectory = new File("configs" + File.separator);
		if (!configDirectory.exists()) configDirectory.mkdir();
		for (File file : configDirectory.listFiles()) {
			if (file.getName().endsWith(".config")) {
				configs.put(file.getName().replace(".config", "").toUpperCase(), new Config(file));
			}
		}
	}
	
	public Config getConfig(String server) {
		return configs.get(server.toUpperCase());
	}
	
	public String getConfigs() {
		StringBuffer configs = new StringBuffer();
		configs.append("Configs: ");
		Config[] configList = this.configs.values().toArray(new Config[0]);
		for (int i = 0; i < configList.length; i++) {
			configs.append(configList[i].getConfigName());
			if (i < configList.length) configs.append(", ");
		}
		return configs.toString();
	}
}
