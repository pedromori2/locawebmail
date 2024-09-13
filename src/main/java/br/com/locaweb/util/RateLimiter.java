package br.com.locaweb.util;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class RateLimiter {

    private static final int MAX_EMAILS = 2;  // Limite de emails por user
    private static final long TIME_WINDOW_MS = 1 * 1000;  // Tempo em milisegundos

    private final Map<ObjectId, Deque<Long>> emailTimestamps = new ConcurrentHashMap<>();

    public boolean canSendEmail(ObjectId userId) {
        long now = System.currentTimeMillis();
        emailTimestamps.putIfAbsent(userId, new LinkedList<>());

        Deque<Long> timestamps = emailTimestamps.get(userId);

        while (!timestamps.isEmpty() && now - timestamps.peek() > TIME_WINDOW_MS) {
            timestamps.poll();
        }

        if (timestamps.size() >= MAX_EMAILS) {
            return false;
        }

        timestamps.add(now);
        return true;
    }
}



