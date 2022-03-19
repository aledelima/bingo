package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class BallsCreator {

    Bingo bingo;

    public Map<Integer, Ball> createBalls(Bingo bingo) {

        this.bingo = bingo;

        Map<Integer, Ball> tempMap = new HashMap<>();
        for (int i=1; i<=99; i++) {
            Ball newBall = new Ball(null, i, false, bingo);
            tempMap.put(i, newBall);
        }
        return tempMap;

    }

}
