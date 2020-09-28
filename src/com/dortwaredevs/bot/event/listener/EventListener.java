package com.dortwaredevs.bot.event.listener;

import com.dortwaredevs.bot.event.bus.EventBus;
import com.dortwaredevs.bot.event.impl.MemberJoinEvent;
import com.dortwaredevs.bot.event.impl.MemberQuitEvent;
import com.dortwaredevs.bot.event.impl.ServerMessageEvent;

import lombok.AllArgsConstructor;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@AllArgsConstructor
public class EventListener extends ListenerAdapter {
	
	private EventBus eventBus;

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		eventBus.handleEvent(new ServerMessageEvent(event.getAuthor(), event.getMessage().getContentRaw(), event.getTextChannel()));
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		eventBus.handleEvent(new MemberJoinEvent(event.getUser(), event.getGuild()));
	}

	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		eventBus.handleEvent(new MemberQuitEvent(event.getUser(), event.getGuild()));
	}
}
