package br.com.aslima.bingo.repository;

import br.com.aslima.bingo.model.Bingo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BingoRepository extends JpaRepository<Bingo, Integer> {
}
