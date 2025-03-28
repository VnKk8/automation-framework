package com.example.automationframework.steps.api;

import com.example.automationframework.context.CucumberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseApiSteps {
    @Autowired
    protected CucumberContext cucumberContext;
}
