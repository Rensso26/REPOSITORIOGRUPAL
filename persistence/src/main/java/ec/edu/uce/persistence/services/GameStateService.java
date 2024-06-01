package ec.edu.uce.persistence.services;

import ec.edu.uce.persistence.interfaces.GameStateRepository;
import ec.edu.uce.persistence.state.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// clase de prueba 
@Service
public class GameStateService {
    @Autowired
    private GameStateRepository repository;

    public GameState save(GameState gameState) {
        return repository.save(gameState);
    }

    public List<GameState> findByPlayerId(String playerId) {
        return repository.findByPlayerId(playerId);
    }

    public GameState findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
