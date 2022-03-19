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

    Bingo bingo;
    Player player;

    public Card createCardBalls() {
        List<Ball> balls = bingo.getBalls().values().stream().collect(Collectors.toList());
        Collections.shuffle(balls);
        Card card = new Card();
        balls.forEach(ball -> card.addBall(ball));
        card.setBingo(bingo);
        card.setPlayer(player);
        return card;
    }
}
