package com.example.frestraw.card;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> create(@RequestPart CardRequest request, @RequestParam(value = "image", required = false) MultipartFile multipartFile)
            throws IOException {
        final CardResponse response = cardService.create(request, multipartFile);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/groups/{groupId}")
    public ResponseEntity<CardResponse> createInGroup(@PathVariable Long groupId, CardRequest cardRequest, @RequestParam(value = "image", required = false) MultipartFile multipartFile)
            throws IOException {
        final CardResponse response = cardService.createInGroup(groupId, cardRequest, multipartFile);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cardId}/groups/{groupId}")
    public ResponseEntity<CardResponse> enter(@PathVariable Long cardId, @PathVariable Long groupId) {
        final CardResponse response = cardService.enterInGroup(cardId, groupId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<CardSimpleResponse>> cardsInGroup(@PathVariable Long groupId) {
        final List<CardSimpleResponse> responses = cardService.findAllCardsByGroup(groupId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> findCardById(@PathVariable Long cardId, @CookieValue(required = false) Long myId) {
        if (Objects.isNull(myId)) {
            final CardResponse response = cardService.findById(cardId);
            return ResponseEntity.ok(response);
        }
        final CardResponse response = cardService.findWithComparing(cardId, myId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> update(@PathVariable Long id, @RequestBody CardRequest request) {
        final CardResponse response = cardService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
