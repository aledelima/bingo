package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CardCreator {

    private Bingo bingo;
    private Player player;

    public Card createCardBalls() {
        List<Ball> balls = this.bingo.getBalls().values().stream().collect(Collectors.toList());
        Collections.shuffle(balls);
        Card card = new Card(this.bingo, this.player);
        for (int i=0; i<24; i++) {
            card.addBall(balls.get(i));
        }
        return card;
    }
}
