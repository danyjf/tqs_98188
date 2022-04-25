package pt.ua.tqs.covidincidence.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class CachedData {
    private Map<String, Object> cachedDataMap;
    private int requestCount;
    private int hit;
    private int miss;
    private Timer timer;

    public CachedData() {
        cachedDataMap = new HashMap<>();
        requestCount = 0;
        timer = new Timer("Timer");
    }

    public Object getFromCache(String url) {
        requestCount++;
        Object data = cachedDataMap.get(url);
        if(data == null) {
            miss++;
        } else {
            hit++;
        }
        return data;
    }

    public void addToCache(String url, Object covidData, long ttl) {
        cachedDataMap.put(url, covidData);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deleteFromCache(url);
            }
        };
        timer.schedule(task, ttl * 1000L);
    }

    public void deleteFromCache(String url) {
        cachedDataMap.remove(url);
    }

    public Map<String, Object> getAllCachedData() {
        requestCount++;
        return cachedDataMap;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }
}
