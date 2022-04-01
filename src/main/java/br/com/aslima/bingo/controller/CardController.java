package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.BallDTO;
import br.com.aslima.bingo.dto.CardDTO;
import br.com.aslima.bingo.dto.CardNewDTO;
import br.com.aslima.bingo.dto.CardWBallsDTO;
import br.com.aslima.bingo.service.CardService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CardDTO>> listAll() {
        return ResponseEntity.ok().body(
                cardService.listAll().stream().map(x -> mapper.map(x, CardDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardWBallsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(cardService.findById(id), CardWBallsDTO.class));
    }

    @GetMapping("/{id}/balls")
    public ResponseEntity<List<BallDTO>> listBallsByCardId(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                cardService.listBallsByCardId(id).stream().map(x -> mapper.map(x, BallDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/bingo/{bingoId}")
    public ResponseEntity<List<CardDTO>> findByBingoId(@PathVariable Integer bingoId) {
        return ResponseEntity.ok().body(
                cardService.listByBingoId(bingoId).stream().map(x -> mapper.map(x, CardDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<CardDTO>> findByPlayerId(@PathVariable Integer playerId) {
        return ResponseEntity.ok().body(
                cardService.listByPlayerId(playerId).stream().map(x -> mapper.map(x, CardDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/bingo/{bingoId}/player/{playerId}")
    public ResponseEntity<List<CardWBallsDTO>> findByBingoIdAndBingoId(@PathVariable Integer bingoId, @PathVariable Integer playerId) {
        return ResponseEntity.ok().body(
                cardService.listByBingoIdAndPlayerId(bingoId, playerId).stream().map(x -> mapper.map(x, CardWBallsDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Void> purchaseCard(@RequestBody CardNewDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(cardService.purchaseCard(dto.getBingoId(), dto.getPlayerId()).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
