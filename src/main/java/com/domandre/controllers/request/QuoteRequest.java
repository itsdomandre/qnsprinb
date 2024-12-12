package com.domandre.controllers.request;

import com.domandre.entities.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteRequest {
    private String title;
    private String content;
    private Section section;
    private Integer authorId;
}
