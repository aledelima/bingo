package br.com.aslima.bingo.repository;

import br.com.aslima.bingo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
