package com.example.automationframework.context;

import com.example.automationframework.annotations.LazySingleComponent;
import com.example.automationframework.enums.ContextKey;


import java.util.HashMap;
import java.util.Map;

@LazySingleComponent
public class CucumberContext {

    private Map<ContextKey, Object> context;

    public CucumberContext() {
        context = new HashMap<>();
    }

    public void saveOrUpdate(ContextKey scenarioKey, Object value) {
        context.put(scenarioKey, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(ContextKey contextKey) {
        return (T) context.get(contextKey);
    }
}
