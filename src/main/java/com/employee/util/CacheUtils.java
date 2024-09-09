package com.employee.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class CacheUtils {
    private final ConcurrentHashMap<String, Value> cache = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        cache.put(key, new Value(value));
    }

    public Object get(String key) {
        Value value = cache.get(key);
        if (value == null || value.isExpired()) {
            return null;
        }
        return value.getValue();
    }

    private static class Value {
        private final long expiryTime;
        private final Object value;

        public Value(Object value) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }

        public Object getValue() {
            return value;
        }
    }
}