package com.dortwaredevs.bot.listener;

import com.dortwaredevs.bot.Bot;
import com.dortwaredevs.bot.event.Event;
import com.dortwaredevs.bot.event.Listener;
import com.dortwaredevs.bot.event.impl.ServerMessageEvent;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ChatListener implements Listener {

    private Bot bot;
    private Cache<Long, Long> lastChat;

    public ChatListener(Bot bot) {
        this.bot = bot;
        lastChat = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.SECONDS)
                .concurrencyLevel(4)
                .build();
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ServerMessageEvent) {
            ServerMessageEvent sm = (ServerMessageEvent) event;
            final long userId = sm.getSender().getIdLong();
            if (!lastChat.asMap().containsKey(userId) && !sm.getSender().isBot()) {
                lastChat.put(userId, System.currentTimeMillis());
                bot.getLevelTracker().addXp(userId, ThreadLocalRandom.current().nextInt(0, 10));
                if (System.currentTimeMillis() - bot.getLevelTracker().getLastSave() > 60000) {
                    bot.getLevelTracker().save();
                }
            }
        }
    }
}
