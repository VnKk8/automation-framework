package com.example.automationframework.steps.ui;

import com.example.automationframework.context.CucumberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseSteps {
    @Autowired
    protected CucumberContext cucumberContext;
}
