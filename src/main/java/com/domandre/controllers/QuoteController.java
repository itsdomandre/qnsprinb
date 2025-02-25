package com.domandre.controllers;

import com.domandre.controllers.request.QuoteRequest;
import com.domandre.dtos.QuoteDTO;
import com.domandre.entities.Quote;
import com.domandre.services.QuoteService;
import com.domandre.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
@Tag(name = "Quotes", description = "Endpoints to add, update and delete quotes. Increased more features like, search a word by quote, private quotes and search quote By an Author.")
@SecurityRequirement(name = "bearerAuth")


public class QuoteController {
    private final QuoteService quoteService;
    private final UserService userService;

    @Operation(summary = "Get Quotes", description = "List all quotes added. Admin Privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Quotes list"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuoteDTO>> getQuotes() {
        List<QuoteDTO> quotes = quoteService.getAll();
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Get quote by Author ", description = "List quotes by Author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Quotes list"),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(@PathVariable Long authorId) {
        List<Quote> quotes = quoteService.getQuotesByAuthor(authorId);
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Create Quote ", description = "Create a new quote.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created a new quote successfully."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<QuoteDTO> addQuote(@RequestBody QuoteRequest request) {
        QuoteDTO savedQuote = quoteService.addQuote(request);
        return ResponseEntity.ok(savedQuote);
    }

    @Operation(summary = "Update Quote ", description = "Update an existing quote.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated a new quote successfully."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody QuoteRequest request) {
        Quote updatedQuote = quoteService.updateQuote(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Quote ", description = "Delete quote.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted quote successfully."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get My Quotes ", description = "Get all quotes by User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all quotes of the user."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/my-quotes")
    public ResponseEntity<List<QuoteDTO>> myQuotes() {
        List<QuoteDTO> quotes = quoteService.getMyQuotes();
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Get User Quotes By Word ", description = "Get quotes by specific word.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found quotes by specific word."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/my-quotes/search")
    public ResponseEntity<List<QuoteDTO>> searchQuotes(@RequestParam String keyword) {
        List<QuoteDTO> quotes = quoteService.quotesByUserAndKeyword(keyword);
        return ResponseEntity.ok(quotes);
    }
}
