package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.repository.BingoRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BingoService {

    private BingoRepository bingoRepo;

    public List<Bingo> listAll() {
        return bingoRepo.findAll();
    }

    public Bingo create(Bingo bingo) {
        bingo.setStatus(Status.STAND_BY);
        return bingoRepo.save(bingo);
    }

    public void raffle(Integer bingoId) {
        Bingo bingo = bingoRepo.findById(bingoId)
                .orElseThrow(() -> new ObjectNotFoundException(Bingo.class, "Object not found. Id: " + bingoId));
        BallsRaffle raffle = new BallsRaffle(bingo);
        Integer number = raffle.raffleBall().getNumber();
        System.out.println(number);
        bingo.updateDrawnBalls(number);
        bingoRepo.save(bingo);
    }

}
