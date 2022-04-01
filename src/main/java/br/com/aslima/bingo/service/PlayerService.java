package br.com.aslima.bingo.service;

import br.com.aslima.bingo.model.Player;
import br.com.aslima.bingo.repository.PlayerRepository;
import br.com.aslima.bingo.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepo;

    public Player findById(Integer id) {
        return playerRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id));
    }

    public List<Player> listAll() {
        return playerRepo.findAll();
    }

    public Player create(Player player) {
        return playerRepo.save(player);
    }

    public Player update(Player player) {
        findById(player.getId());
        return playerRepo.save(player);
    }

    public Player chargeValue(Integer playerId, Double value) {
        Player player = findById(playerId);
        player.setMoney(player.getMoney().add(new BigDecimal(value)));
        return playerRepo.save(player);
    }
}
