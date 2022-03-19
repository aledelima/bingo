package br.com.aslima.bingo.repository;

import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
