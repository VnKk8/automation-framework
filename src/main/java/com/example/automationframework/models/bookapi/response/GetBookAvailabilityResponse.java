package com.example.automationframework.models.bookapi.response;

import com.example.automationframework.models.bookapi.request.PostBookRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBookAvailabilityResponse {
    private Long id;
    private Integer availableCopies;
    private PostBookRequest book;
    private Long bookId;
    private Integer orderedCopies;
}
