package br.com.aslima.bingo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerChargeValueDto {

    private Integer playerId;
    private Double value;

}
