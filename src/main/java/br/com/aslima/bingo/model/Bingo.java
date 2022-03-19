package br.com.aslima.bingo.model;

import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.service.BallsCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @ManyToMany
    private List<Player> players = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "bingo",  cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Map<Integer, Ball> balls;
    @OneToMany(mappedBy = "id.bingo")
    private List<Card> cards = new ArrayList<>();

    public Bingo(Integer id, LocalDate date, String description, BigDecimal ticketPrice) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.ticketPrice = ticketPrice;
        BallsCreator ballsCreator = new BallsCreator(this);
        balls = ballsCreator.createBalls(this);
    }

    public Map<Integer, Ball> updateDrawnBalls(Integer ballNumber) {
        if (balls.containsKey(ballNumber)) {
            balls.get(ballNumber).setDrawn(true);
        }
        return balls;
    }

}
