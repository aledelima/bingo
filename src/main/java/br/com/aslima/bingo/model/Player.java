package br.com.aslima.bingo.model;


import lombok.AllArgsConstructor;
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
        @OneToMany(mappedBy = "id.player")
        private List<Card> cards = new ArrayList<>();

        public Player(Integer id, String name, String email) {
                this.id = id;
                this.name = name;
                this.email = email;
        }

}
