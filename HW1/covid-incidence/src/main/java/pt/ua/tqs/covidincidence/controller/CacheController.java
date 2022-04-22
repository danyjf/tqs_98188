package pt.ua.tqs.covidincidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.covidincidence.service.CacheService;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @GetMapping("cache")
    public Map<String, String> getInternalCacheStats() {
        return cacheService.getInternalCacheStats();
    }
}
