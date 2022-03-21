package br.com.aslima.bingo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Card {

//    @EmbeddedId
//    private CardId id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Bingo bingo;
    @ManyToOne
    @JsonBackReference
    private Player player;
    @ManyToMany
    private List<Ball> balls = new ArrayList<>();

    public Card(Bingo bingo, Player player) {
        this.bingo = bingo;
        this.player = player;
    }

    public void setBingo(Bingo bingo) {
        this.setBingo(bingo);
    }

    public Card addBall(Ball ball) {
        this.balls.add(ball);
        return this;
    }
}
