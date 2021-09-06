package com.manish.springdemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private final Map<String, Feature> map = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        map.put("Department", new Feature(true));
        map.put("User", new Feature(false));
        map.put("Authentication", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> features() {
        return map;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName) {
        return map.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private boolean isEnabled;
    }

}
