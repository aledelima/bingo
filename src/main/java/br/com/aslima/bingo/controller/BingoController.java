package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.BingoNewDTO;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.service.BingoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bingos")
public class BingoController {

    private BingoService bingoService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello world from Bingo system";
    }

    @GetMapping
    public List<Bingo> listAll() {
        return bingoService.listAll();
    }

    @PostMapping
    public Bingo create(@RequestBody BingoNewDTO dto) {
        return bingoService.create(dto.toBingo());
    }

    @PostMapping("/{id}/raffle")
    public ResponseEntity<Void> raffle(@PathVariable Integer id) {
        bingoService.raffle(id);
        return ResponseEntity.ok().build();
    }

}
