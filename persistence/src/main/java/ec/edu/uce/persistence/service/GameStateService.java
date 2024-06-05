package ec.edu.uce.persistence.service;

import ec.edu.uce.persistence.state.GameState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameStateService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public GameState save(GameState gameState) {
        if (gameState.getId() == null) {
            entityManager.persist(gameState);
            return gameState;
        } else {
            return entityManager.merge(gameState);
        }
    }

    public List<GameState> findAll() {
        return entityManager.createQuery("from gamestate", GameState.class).getResultList();
    }

    public GameState findById(Long id) {
        return entityManager.find(GameState.class, id);
    }

    @Transactional
    public void delete(Long id) {
        GameState gameState = findById(id);
        if (gameState != null) {
            entityManager.remove(gameState);
        }
    }

    @Transactional
    public GameState update(Long id, GameState newState) {
        GameState existingGameState = findById(id);
        if (existingGameState != null) {
            existingGameState.setName(newState.getName());
            existingGameState.setLevel(newState.getLevel());
            existingGameState.setLife(newState.getLife());
            existingGameState.setPoints(newState.getPoints());
            return entityManager.merge(existingGameState);
        } else {
            return null;
        }
    }
}
