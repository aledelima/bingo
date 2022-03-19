package br.com.aslima.bingo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Card {

    @EmbeddedId
    private CardId id;
    @ManyToMany
    List<Ball> balls = new ArrayList<>();

    public Card(Bingo bingo, Player player) {
        this.id.setBingo(bingo);
        this.id.setPlayer(player);
    }

    public void setBingo(Bingo bingo) {
        this.id.setBingo(bingo);
    }

    public void setPlayer(Player player) {
        this.id.setPlayer(player);
    }

    public Bingo getBingo() {
        return id.getBingo();
    }

    public Player getPlayer() {
        return id.getPlayer();
    }

    public Card addBall(Ball ball) {
        this.balls.add(ball);
        return this;
    }
}
