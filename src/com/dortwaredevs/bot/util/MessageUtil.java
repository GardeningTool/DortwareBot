package com.dortwaredevs.bot.util;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

public class MessageUtil {
	
	public static Message message(String msg) {
		MessageBuilder mb = new MessageBuilder();
		mb.setContent(msg);
		return mb.build();
	}
	
	public static Message formattedMessage(Color color, String title, String footer, String desc) {
	    MessageBuilder mb = new MessageBuilder();
	    EmbedBuilder eb = new EmbedBuilder();
	    eb.setColor(color);
	    eb.setFooter(footer);
	    eb.setDescription(desc);
	    eb.setTitle(title, null);
	    mb.setEmbed(eb.build());
	    return mb.build();
	}
	
}
