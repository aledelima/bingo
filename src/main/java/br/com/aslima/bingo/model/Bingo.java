package br.com.aslima.bingo.model;

import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.service.BallsCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Bingo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private LocalDate date;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private BigDecimal ticketPrice;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    private List<Player> players = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Map<Integer, Ball> balls = new HashMap<>();
    @OneToMany(mappedBy = "bingo")
    @JsonManagedReference
    @Getter
    @Setter
    private List<Card> cards = new ArrayList<>();
    @Getter
    @Setter
    private Integer ballSequence = 0;
    @Getter
    @Setter
    @JsonManagedReference
    @ManyToMany
    private List<Player> winners = new ArrayList<>();

    public Bingo(Integer id, LocalDate date, String description, BigDecimal ticketPrice) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.ticketPrice = ticketPrice;
        BallsCreator ballsCreator = new BallsCreator(this);
        balls = ballsCreator.createBalls(this);
    }

    public Map<Integer, Ball> updateDrawnBalls(Ball shaffledBall, Integer sequence) {
        shaffledBall.setDrawn(true);
        shaffledBall.setSequence(sequence);
        return balls;
    }

    public void addWinner(Player player) {
        winners.add(player);
    }

    public void fulfillCards(Ball raffledBall) {
        for (Card card: this.getCards()) {
            if (card.getBalls().contains(raffledBall)) {
                card.setBallsFulfilled(card.getBallsFulfilled() + 1);
                if (card.getBallsFulfilled() >= 24) {
                    this.addWinner(card.getPlayer());
                    card.setFulfilled(true);
                    this.setStatus(Status.FINISHED);
                }
            }
        }
    }
}
