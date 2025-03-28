package com.example.automationframework.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Header {
    BOOK_STORE("bookstore");
    private final String value;
}
