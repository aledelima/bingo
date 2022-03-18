package br.com.aslima.bingo.dto;

import br.com.aslima.bingo.model.Bingo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class BingoDto {

    private Integer id;
    private LocalDate date;
    private String description;
    private Double ticketPrice;
    private String status;

    BingoDto(Bingo bingo) {
        this.id = bingo.getId();
        this.date = bingo.getDate();
        this.description = bingo.getDescription();
        this.ticketPrice = bingo.getTicketPrice().doubleValue();
        this.status = bingo.getStatus().toString();
    }

}
