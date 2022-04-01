package br.com.aslima.bingo.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BingoDTO {

    private Integer id;
    private LocalDate date;
    private String description;
    private Double ticketPrice;
    private String status;
    private Integer ballSequence;

}
