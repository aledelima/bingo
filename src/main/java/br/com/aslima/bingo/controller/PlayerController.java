package br.com.aslima.bingo.controller;

import br.com.aslima.bingo.dto.PlayerChargeValueDTO;
import br.com.aslima.bingo.dto.PlayerDTO;
import br.com.aslima.bingo.dto.PlayerNewDTO;
import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.service.PlayerService;
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
@RequestMapping("/players")
public class PlayerController {

    private ModelMapper mapper;
    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(playerService.findById(id), PlayerDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> listAll() {
        return ResponseEntity.ok().body(playerService.listAll().stream()
                .map(x -> mapper.map(x, PlayerDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerNewDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(playerService.create(mapper.map(dto, Player.class))
                        .getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/recharge")
    public ResponseEntity<PlayerDTO> chargeValue(@RequestBody PlayerChargeValueDTO dto) {
        return ResponseEntity.ok()
                .body(mapper.map(playerService.chargeValue(dto.getId(), dto.getValue()), PlayerDTO.class));
    }
}
