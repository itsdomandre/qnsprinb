package com.domandre.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorRequest {
    private String name;
    @Nullable
    private String description;
}
