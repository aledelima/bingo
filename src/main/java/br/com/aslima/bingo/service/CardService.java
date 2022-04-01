package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Ball;
import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.repository.CardRepository;
import br.com.aslima.bingo.service.exceptions.NotEnoughBalanceException;
import br.com.aslima.bingo.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private BingoService bingoService;
    private PlayerService playerService;
    private CardRepository cardRepo;

    public List<Card> listAll() {
        return cardRepo.findAll();
    }

    public Card findById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id));
    }

    public List<Card> listByBingoId(Integer bingoId) {
        Bingo bingo = bingoService.findById(bingoId);
        return cardRepo.findCardsByBingo(bingo);
    }

    public List<Card> listByPlayerId(Integer playerId) {
        Player player = playerService.findById(playerId);
        return cardRepo.findCardsByPlayer(player);
    }

    public List<Card> listByBingoIdAndPlayerId(Integer bingoId, Integer playerId) {
        Bingo bingo = bingoService.findById(bingoId);
        Player player = playerService.findById(playerId);
        return cardRepo.findCardsByBingoAndPlayer(bingo, player);
    }

    public List<Ball> listBallsByCardId(Long id) {
        return findById(id).getBalls();
    }

    public Card purchaseCard(Integer bingoId, Integer playerId) {
        Player player = playerService.findById(playerId);
        Bingo bingo = bingoService.findById(bingoId);

        //Checks if there's enough money to buy the new ticket
        if (player.getMoney().compareTo(bingo.getTicketPrice())==-1)
            throw new NotEnoughBalanceException("Not enough balance available for this purchase.");

        CardBallsCreator ballsCreator = new CardBallsCreator(bingo);
        Card card = new Card(bingo, player);
        card.setBalls(ballsCreator.createCardBalls());
        card = cardRepo.save(card);

        //Subtract ticket price from player balance
        player.setMoney(player.getMoney().subtract(bingo.getTicketPrice()));
        playerService.update(player);
        return card;
    }




}
