package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BallsRaffle {

    private Bingo bingo;

    public BallsRaffle(Bingo bingo) {
        this.bingo = bingo;
    }

    public Ball raffleBall() {
        List<Ball> nonDrawnBalls = bingo.getBalls().values().stream()
                .filter(ball -> ball.getDrawn().booleanValue()==false).collect(Collectors.toList());
        Collections.shuffle(nonDrawnBalls);
        return nonDrawnBalls.get(0);
    }

}
