package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.model.enums.Status;
import br.com.aslima.bingo.repository.BingoRepository;
import br.com.aslima.bingo.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BingoService {

    private BingoRepository bingoRepo;
    private CardRepository cardRepo;
    private PlayerService playerService;

    public Bingo findById(Integer id) {
        return bingoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Bingo.class, "Object not found. Id: " + id));
    }

    public List<Bingo> listAll() {
        return bingoRepo.findAll();
    }

    public Bingo create(Bingo bingo) {
        bingo.setStatus(Status.STAND_BY);
        return bingoRepo.save(bingo);
    }

    public void raffle(Integer bingoId) {
        Bingo bingo = findById(bingoId);
        Ball raffledBall = (new BallsRaffle(bingo)).raffleBall();
        bingo.setBallSequence(bingo.getBallSequence()+1);
        raffledBall.setSequence(bingo.getBallSequence());
//        BallsRaffle raffle = new BallsRaffle(bingo);
//        Integer number = raffledBall.getNumber();
        bingo.updateDrawnBalls(raffledBall, bingo.getBallSequence());
        bingo.fulfillCards(raffledBall);
        bingoRepo.save(bingo);
    }

    public void purchaseCard(Integer bingoId, Integer playerId) {
        Bingo bingo = this.findById(bingoId);
        Player player = playerService.findById(playerId);

        //Checks if there's anough money to buy the new ticket
        if (player.getMoney().compareTo(bingo.getTicketPrice())==-1)
            throw new IllegalArgumentException("Not enough balance available.");

        CardCreator cardCreator = new CardCreator(bingo, player);
        Card card = cardCreator.createCardBalls();
        cardRepo.save(card);

        //Subtract ticket price from player balance
        player.setMoney(player.getMoney().subtract(bingo.getTicketPrice()));
//        player.addCard(card);
        playerService.update(player);
    }

}
