package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.PlayerChargeValueDto;
import br.com.aslima.bingo.dto.PlayerNewDTO;
import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private PlayerService playerService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello world from Bingo system";
    }

    @GetMapping
    public List<Player> listAll() {
        return playerService.listAll();
    }

    @PostMapping
    public Player create(@RequestBody PlayerNewDTO dto) {
        return playerService.create(dto.toPlayer());
    }

    @PostMapping("/recharge")
    public ResponseEntity<Player> chargeValue(@RequestBody PlayerChargeValueDto dto) {
        return ResponseEntity.ok().body(playerService.chargeValue(dto.getId(), dto.getValue()));
    }
}
