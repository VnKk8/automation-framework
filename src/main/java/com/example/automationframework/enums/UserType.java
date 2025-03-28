package com.example.automationframework.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
     ANONYMOUS(false),
     AUTHORIZED(true);
    private final boolean value;
}
