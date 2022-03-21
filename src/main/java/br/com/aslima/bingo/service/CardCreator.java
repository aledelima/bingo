package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CardCreator {

    private Bingo bingo;
    private Player player;

    public Card createCardBalls() {
        List<Ball> bingoBalls = this.bingo.getBalls().values().stream().collect(Collectors.toList());
        Card newCard = new Card(this.bingo, this.player);

        boolean repeatedBallSet = false;
        //Select balls by shuffling the bingo balls
        Collections.shuffle(bingoBalls);
        for (int i = 0; i < 24; i++) {
            newCard.addBall(bingoBalls.get(i));
        }
        Collections.sort(newCard.getBalls(), Comparator.comparing(Ball::getNumber));
        return newCard;
    }

    private Integer makeCheckSum(List<Ball> balls) {
        int sum1 = 0;
        int sum2 = 0;
        int index;

        for ( index = 0; index < balls.size(); ++index )
        {
            sum1 = (sum1 + balls.get(index).getNumber()) % 255;
            sum2 = (sum2 + sum1) % 255;
        }

        return (sum2 << 8) | sum1;
    }
}
