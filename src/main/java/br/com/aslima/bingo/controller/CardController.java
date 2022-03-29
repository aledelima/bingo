package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.BingoNewDTO;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.service.BingoService;
import br.com.aslima.bingo.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {

    private BingoService bingoService;
    private CardService cardService;

    @GetMapping
    public List<Card> listAll() {
        return cardService.listAll();
    }

    @GetMapping("/{id}")
    public Card findById(@PathVariable Integer id) {
        return cardService.findById(id);
    }

    @GetMapping("/bingo/{bingoId}")
    public List<Card> findByBingoId(@PathVariable Integer bingoId) {
        return cardService.listByBingoId(bingoId);
    }

    @GetMapping("/player/{playerId}")
    public List<Card> findByPlayerId(@PathVariable Integer playerId) {
        return cardService.listByPlayerId(playerId);
    }

    @PostMapping("/{bingoId}/player/{playerId}/purchaseCard")
    public ResponseEntity<Void> purchaseCard(@PathVariable Integer bingoId, @PathVariable Integer playerId) {
        bingoService.purchaseCard(bingoId, playerId);
        return ResponseEntity.ok().build();
    }

}
