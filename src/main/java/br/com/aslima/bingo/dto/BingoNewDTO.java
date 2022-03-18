package br.com.aslima.bingo.dto;

import br.com.aslima.bingo.model.Bingo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BingoNewDTO {

    private LocalDate date;
    private String description;
    private Double ticketPrice;

    public Bingo toBingo() {
        return new Bingo(null, this.date, this.description, new BigDecimal(this.ticketPrice));
    }
}
