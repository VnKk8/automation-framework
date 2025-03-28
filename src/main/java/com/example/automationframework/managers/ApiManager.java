package com.example.automationframework.managers;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.apiclients.bookservice.BookAvailabilityClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Getter
@LazyProtoComponent
@ConditionalOnProperty(
        name = "upload.clue.cumber.report.running",
        havingValue = "false",
        matchIfMissing = true
)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiManager {
    private final BookAvailabilityClient client;
}
