package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.*;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.service.BingoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/bingos")
public class BingoController {

    private ModelMapper mapper;
    private BingoService bingoService;

    @GetMapping("/{id}")
    public ResponseEntity<BingoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(bingoService.findById(id), BingoDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<BingoDTO>> listAll() {
        return ResponseEntity.ok().body(
                bingoService.listAll().stream().map(x -> mapper.map(x, BingoDTO.class)).collect(Collectors.toList())
        );
    }

    @GetMapping("/{bingoId}/balls")
    public ResponseEntity<List<BallDTO>> listBalls(@PathVariable Integer bingoId) {
        return ResponseEntity.ok()
                .body(bingoService.findById(bingoId).getBalls().values().stream()
                        .map(x -> mapper.map(x, BallDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{bingoId}/cards")
    public ResponseEntity<List<CardDTO>> listCards(@PathVariable Integer bingoId) {
        return ResponseEntity.ok()
                .body(bingoService.findById(bingoId).getCards().stream()
                        .map(x -> mapper.map(x, CardDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{bingoId}/players")
    public ResponseEntity<List<PlayerDTO>> listPlayers(@PathVariable Integer bingoId) {
        return ResponseEntity.ok()
                .body(bingoService.findById(bingoId).getPlayers().stream()
                        .map(x -> mapper.map(x, PlayerDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{bingoId}/winners")
    public ResponseEntity<List<PlayerDTO>> listWinners(@PathVariable Integer bingoId) {
        return ResponseEntity.ok()
                .body(bingoService.findById(bingoId).getWinners().stream()
                        .map(x -> mapper.map(x, PlayerDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<BingoDTO> create(@RequestBody BingoNewDTO dto) {
        Bingo bingo = new Bingo(null, dto.getDate(), dto.getDescription(), new BigDecimal(dto.getTicketPrice()));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(bingoService.create(bingo).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/raffle")
    public ResponseEntity<BallDTO> playBall(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(bingoService.raffle(id), BallDTO.class));
    }

}
