package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.repository.BingoRepository;
import br.com.aslima.bingo.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BingoService {

    private BingoRepository bingoRepo;

    public Bingo findById(Integer id) {
        return bingoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id));
    }

    public List<Bingo> listAll() {
        return bingoRepo.findAll();
    }

    public Bingo create(Bingo bingo) {
        bingo.setStatus(Status.STAND_BY);
        return bingoRepo.save(bingo);
    }

    public Ball raffleBall(Integer bingoId) {
        Bingo bingo = findById(bingoId);
        return (new BallsRaffle(bingo)).raffleBall();
    }

    public Ball raffle(Integer bingoId) {
        Bingo bingo = findById(bingoId);
        Ball raffledBall = (new BallsRaffle(bingo)).raffleBall();
        int seq = bingo.getBallSequence()+1;
        bingo.setBallSequence(seq);
        raffledBall.setSequence(seq);
        raffledBall.setDrawn(true);
        bingo.fulfillCards(raffledBall);
        bingoRepo.save(bingo);
        return raffledBall;
    }

}
