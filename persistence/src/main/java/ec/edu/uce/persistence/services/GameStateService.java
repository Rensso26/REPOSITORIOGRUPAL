package ec.edu.uce.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.edu.uce.persistence.interfaces.GameStateRepository;
import ec.edu.uce.persistence.state.GameState;

import java.util.Optional;

@Service
public class GameStateService {
    @Autowired
    private GameStateRepository repository;

    public GameState saveGameState(GameState gameState) {
        return repository.save(gameState);
    }

    public Optional<GameState> getGameState(String name) {
        return repository.findByName(name);
    }
}
