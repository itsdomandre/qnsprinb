package com.domandre.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorRequest {
    @NotEmpty(message = "Author is required")
    private String name;
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, message = "Minimum 10 characters for description")
    private String description;

}
