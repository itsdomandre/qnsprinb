package com.domandre.controllers.request;

import com.domandre.enums.Section;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteRequest {
    private Long Id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Content is required")
    private String content;
    @NotEmpty(message = "Section is required")
    private Section section;
    @NotEmpty(message = "Author is required")
    private Long authorId;
}
