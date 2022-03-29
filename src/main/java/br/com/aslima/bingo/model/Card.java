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
//    private Map<Integer, Ball> balls = new HashMap<>();
    private Integer ballsFulfilled = 0;
    private Boolean fulfilled = false;

    public Card(Bingo bingo, Player player) {
        this.bingo = bingo;
        this.player = player;
    }

    public Card addBall(Ball ball) {
        this.balls.add(ball);
//        this.balls.put(ball.getNumber(), ball);
        return this;
    }
}
