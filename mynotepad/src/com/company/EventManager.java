package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventManager {
    public enum Event {
        FILE_DRAG_DROP
    }

    //INPUT_COMPLETED --> [x, y , z ....]
    //TEST ---> [y, z ....]
    private static Map<Event, List<Consumer<Object>>> subscribersList = new HashMap<>();

    //发布消息
    public  static void publish(Event event, Object calculator) {
        List<Consumer<Object>> consumers = subscribersList.get(event);
        if(consumers!=null) {
            consumers.forEach(consumer -> new Thread(() -> consumer.accept(calculator)).start());
        }

    }


    //订阅消息
    public  static void subscribe(Event event, Consumer<Object> consumer) {
        List<Consumer<Object>> consumers = subscribersList.computeIfAbsent(event, k -> new ArrayList<>());
        consumers.add(consumer);
    }

}
