package com.example.automationframework.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiVersion {
    V1("/api/v1");
    private final String value;
}
