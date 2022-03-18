package br.com.aslima.bingo.model;

import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.service.BallsCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
@Data
public class Bingo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private String description;
    private BigDecimal ticketPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    private Map<Integer, Ball> balls;
    @JsonIgnore
    @Transient
    private Map<Integer, Ball> drawnBall;
    @JsonIgnore
    @Transient
    private Map<Integer, Ball> nonDrawnBalls = new HashMap<Integer, Ball>();

    public Bingo(Integer id, LocalDate date, String description, BigDecimal ticketPrice) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.ticketPrice = ticketPrice;
        BallsCreator ballsCreator = new BallsCreator(this);
        balls = ballsCreator.createBalls();
        drawnBall = new HashMap<>(balls);
    }

    public Map<Integer, Ball> updateDrawnBalls(Integer ballNumber) {
        if (balls.containsKey(ballNumber)) {
            balls.get(ballNumber).setDrawn(true);
        }
        return balls;
    }

}
