package br.com.aslima.bingo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class CardId implements Serializable {

    @ManyToOne
    private Bingo bingo;
    @ManyToOne
    private Player player;

}
