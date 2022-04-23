package pt.ua.tqs.covidincidence.cache;

import org.springframework.stereotype.Component;
import pt.ua.tqs.covidincidence.model.CovidHistoryData;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class CachedData {
    private Map<String, Object> cachedData;
    private int requestCount;
    private int hit;
    private int miss;
    private Timer timer;

    public CachedData() {
        cachedData = new HashMap<>();
        requestCount = 0;
        timer = new Timer("Timer");
    }

    public Object getFromCache(String url) {
        requestCount++;
        Object data = cachedData.get(url);
        if(data == null) {
            miss++;
        } else {
            hit++;
        }
        return data;
    }

    public void addToCache(String url, Object covidData, long ttl) {
        cachedData.put(url, covidData);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deleteFromCache(url);
            }
        };
        timer.schedule(task, ttl * 1000L);
    }

    public void deleteFromCache(String url) {
        cachedData.remove(url);
    }

    public Map<String, Object> getAllCachedData() {
        requestCount++;
        return cachedData;
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
