package pt.ua.tqs.covidincidence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.tqs.covidincidence.cache.CachedData;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {
    @Autowired
    private CachedData cachedData;

    public Map<String, String> getInternalCacheStats() {
        Map<String, String> cacheStats = new HashMap<>();

        cacheStats.put("requests", Integer.toString(cachedData.getRequestCount()));
        cacheStats.put("hits", Integer.toString(cachedData.getHit()));
        cacheStats.put("misses", Integer.toString(cachedData.getMiss()));

        return cacheStats;
    }
}
