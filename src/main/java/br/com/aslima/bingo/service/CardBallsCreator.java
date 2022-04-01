package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CardBallsCreator {

    private Bingo bingo;

    public List<Ball> createCardBalls() {
        List<Ball> bingoBalls = this.bingo.getBalls().values().stream().collect(Collectors.toList());
        Collections.shuffle(bingoBalls);
        List<Ball> cardBalls = bingoBalls.subList(0, 23);
        return cardBalls;

//          It's missing an algorithm to avoid repeated cardBalls. A checksum attribute for the array
//          of balls is an idea for implementing this in a fast way.
//
//        boolean repeatedBallSet = false;

//        Card newCard = new Card(this.bingo, this.player);

//        boolean repeatedBallSet = false;
        //Select balls by shuffling the bingo balls
//        Collections.shuffle(bingoBalls);
//        for (int i = 0; i < 24; i++) {
//            newCard.addBall(bingoBalls.get(i));
//        }
//        Collections.sort(newCard.getBalls(), Comparator.comparing(Ball::getNumber));
//        Collections.sort(bingoBalls, Comparator.comparing(Ball::getNumber));
//        return newCard;
//        return bingoBalls.subList(0, 23);
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
