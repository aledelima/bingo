package br.com.aslima.bingo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BallDTO {
    private Integer id;
    private Integer number;
    private Boolean drawn;
    private Integer sequence;
}
