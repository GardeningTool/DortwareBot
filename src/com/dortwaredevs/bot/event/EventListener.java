package com.dortwaredevs.bot.event;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.util.MessageUtil;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@AllArgsConstructor
public class EventListener extends ListenerAdapter {
	
	private Bot bot;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String raw = event.getMessage().getContentRaw();
		if (raw.startsWith("-config")) {
			if (!raw.contains(" ")) {
				event.getChannel().sendMessage(MessageUtil.message(bot.getConfigManager().getConfigs())).submit();
				return;
			}
			String server = raw.replace("-config ", "");
			try {
				event.getChannel().sendMessage(bot.getConfigManager().getConfig(server).buildMessage()).submit();
			} catch (NullPointerException exc) {
				event.getChannel().sendMessage(MessageUtil.message("Invalid config!")).submit();
			}
		}
	}
}
