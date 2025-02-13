package com.domandre.dtos;

import com.domandre.entities.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO {
    private Long Id;
    private String title;
    private String content;
    private Section section;
    private Long authorId;
}
