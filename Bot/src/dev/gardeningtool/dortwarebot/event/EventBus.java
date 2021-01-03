package dev.gardeningtool.dortwarebot.event;

import dev.gardeningtool.dortwarebot.DortwareBot;
import dev.gardeningtool.dortwarebot.listener.EventHandler;
import dev.gardeningtool.dortwarebot.listener.Listener;
import dev.gardeningtool.dortwarebot.listener.TestListener;
import dev.gardeningtool.dortwarebot.listener.impl.CommandListener;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

public class EventBus {

    private Deque<Event> eventQueue;
    private HashMap<Listener, Class<? extends Listener>> listeners;

    public EventBus(DortwareBot dortwareBot) {
        eventQueue = new LinkedList<>();
        listeners = new HashMap<>();
        listeners.put(new CommandListener(dortwareBot.getCommandManager()), CommandListener.class);
        listeners.put(new TestListener(), TestListener.class);
        new Thread(() -> {
            while(true) {
                if (!eventQueue.isEmpty()) {
                    Event event = eventQueue.poll();
                    Class<? extends Event> eventClazz = event.getClass();
                    for (Map.Entry<Listener, Class<? extends Listener>> entry : listeners.entrySet()) {
                        Listener listener = entry.getKey();
                        Class<? extends Listener> clazz = entry.getValue();
                        Stream.of(clazz.getMethods()).filter(method -> method.isAnnotationPresent(EventHandler.class) &&
                                method.getParameterCount() == 1 &&
                                method.getParameterTypes()[0].equals(eventClazz)).forEach(method -> {
                            try {
                                method.invoke(listener, event);
                            } catch (InvocationTargetException | IllegalAccessException exc) {
                                exc.printStackTrace();
                            }
                        });
                    }
                }
            }
        }).start();
    }
    public void post(Event event) {
        eventQueue.add(event);
    }

}
