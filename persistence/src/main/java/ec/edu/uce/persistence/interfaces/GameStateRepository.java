package ec.edu.uce.persistence.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ec.edu.uce.persistence.state.GameState;
import java.util.Optional;

@Repository
public interface GameStateRepository extends JpaRepository<GameState, Long> {
    Optional<GameState> findByName(String name);
}
