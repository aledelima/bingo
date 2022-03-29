package br.com.aslima.bingo.repository;

import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
//    List<Card> findByBingo(Bingo bingo);
    List<Card> findCardsByBingo(Bingo bingo);
    List<Card> findCardsByPlayer(Player bingo);
//    List<Card> findByPlayer(Player player);
}
