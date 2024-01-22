package com.example.RestAPIProj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn;

    private String title;

    private AuthorDto authorEntity;
}
