package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Bingo;
import br.com.aslima.bingo.model.Card;
import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private BingoService bingoService;
    private PlayerService playerService;
    private CardRepository cardRepo;

    public List<Card> listByBingoId(Integer bingoId) {
        Bingo bingo = bingoService.findById(bingoId);
        return cardRepo.findCardsByBingo(bingo);
    }

    public List<Card> listByPlayerId(Integer playerId) {
        Player player = playerService.findById(playerId);
        return cardRepo.findCardsByPlayer(player);
    }

    public List<Card> listAll() {
        return cardRepo.findAll();
    }

    public Card findById(Integer id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Card.class, "Object not found. Id: " + id));
    }
}
