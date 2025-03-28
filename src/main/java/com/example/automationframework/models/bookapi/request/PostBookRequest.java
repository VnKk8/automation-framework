package com.example.automationframework.models.bookapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBookRequest {
    private String title;
    private String author;
    private String description;
    private Integer price;
    private String publisher;
    private String publishedDate;
    private Integer pages;
    private Integer availableCopies;
}
