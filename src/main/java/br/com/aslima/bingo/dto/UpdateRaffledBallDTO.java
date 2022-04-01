package br.com.aslima.bingo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRaffledBallDTO {
    private Long id;
    private Boolean drawn;
}
