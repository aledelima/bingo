package br.com.aslima.bingo.dto;

import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlayerNewDTO {

    private String name;
    private String email;

    public Player toPlayer() {
        return new Player(null, this.name, this.email);
    }
}
