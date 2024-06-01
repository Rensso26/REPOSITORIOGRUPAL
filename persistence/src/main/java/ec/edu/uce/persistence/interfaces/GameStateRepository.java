package ec.edu.uce.persistence.interfaces;

import ec.edu.uce.persistence.state.GameState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStateRepository extends JpaRepository<GameState, Long> {
    List<GameState> findByPlayerId(String playerId);
}
