package com.dortwaredevs.bot.listener;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.event.Event;
import com.dortwaredevs.bot.event.Listener;
import com.dortwaredevs.bot.event.impl.ServerMessageEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandListener implements Listener {

    private Bot bot;

    @Override
    public void onEvent(Event event) {
        if (event instanceof ServerMessageEvent) {
            ServerMessageEvent sm = (ServerMessageEvent) event;
            String message = sm.getMessage();
            if (message.startsWith("-")) {
                String cmd = message.replaceFirst("-", "");
                String[] args = cmd.contains(" ") ? cmd.split(" ") : new String[] {};
                bot.getCommandManager().handleCommand(cmd.contains(" ") ? cmd.split(" ")[0] : cmd, args, sm.getSender(), sm.getChannel());
            }
        }
    }
}
