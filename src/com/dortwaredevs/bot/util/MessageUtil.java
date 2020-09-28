package com.dortwaredevs.bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

public class MessageUtil {
	
	public static Message message(String msg) {
		return new MessageBuilder().setContent(msg).build();
	}
	
	public static Message formattedMessage(Color color, String title, String footer, String desc) {
	    return new MessageBuilder().setEmbed(new EmbedBuilder().setColor(color).setFooter(footer).setDescription(desc).setTitle(title, null).build()).build();
	}

	public static Message buildInadequatePermissionMessage() {
		return formattedMessage(new Color(199, 0, 0), "Error", null, "You don't have permission to do this!");
	}
	
}
