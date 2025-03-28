package com.example.automationframework.models.bookapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBookResponse {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Integer price;
    private String publisher;
    private String publishedDate;
    private Integer pages;
    private Integer availableCopies;
}
