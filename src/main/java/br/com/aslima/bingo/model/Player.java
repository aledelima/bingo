package br.com.aslima.bingo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Player {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String email;
        private Boolean active = true;
        private BigDecimal money = new BigDecimal(0);
        @OneToMany(mappedBy = "player")
        @JsonManagedReference
        private List<Card> cards = new ArrayList<>();
        @ManyToMany(mappedBy = "players")
        @JsonBackReference
        private List<Bingo> bingos = new ArrayList<>();

        public Player(Integer id, String name, String email) {
                this.id = id;
                this.name = name;
                this.email = email;
        }

        public void addCard(Card newCard) {
                this.cards.add(newCard);
        }

}
