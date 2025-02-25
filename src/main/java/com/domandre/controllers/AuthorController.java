package com.domandre.controllers;

import com.domandre.controllers.request.AuthorRequest;
import com.domandre.entities.Author;
import com.domandre.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Authors", description = "Endpoints to add, update, list and delete Authors.")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Get Authors", description = "List all author added.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Authors list"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @Operation(summary = "Add Author", description = "Add a new Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author created successfully"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequest request) {
        Author savedAuthor = authorService.addAuthor(request);
        return ResponseEntity.ok(savedAuthor);
    }

    @Operation(summary = "Update Author", description = "Update info of a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Author updated successfully"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request) {
        Author updatedAuthor = authorService.updateAuthor(id, request);
        return ResponseEntity.noContent().build();
    }

    // TODO: Implantaremos @PreAuthorize - Just admin can delete a Author
    @Operation(summary = "Delete Author", description = "Delete a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Author updated successfully"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
