package ec.edu.uce.persistence.service;

import ec.edu.uce.persistence.state.GameState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameStateService {

    private GameState gameState;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public GameState save(GameState gameState) {
        GameState existingGameState = findByName(gameState.getName());
        if (existingGameState != null) {
            gameState.setId(existingGameState.getId());
            existingGameState.setLevel(gameState.getLevel());
            existingGameState.setLife(gameState.getLife());
            existingGameState.setPoints(gameState.getPoints());
            return entityManager.merge(existingGameState);
        } else {

            entityManager.persist(gameState);
            return gameState;
        }
    }

    public List<GameState> findAll() {
        return entityManager.createQuery("from GameState", GameState.class).getResultList();
    }

    public GameState findById(Long id) {
        return entityManager.find(GameState.class, id);
    }

    public GameState findByName(String name) {
        try {
            return entityManager.createQuery("SELECT g FROM GameState g WHERE g.name = :name", GameState.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
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

    @Transactional
    public GameState update(String name, GameState newState) {
        GameState existingGameState = findByName(name);
        if (existingGameState != null) {
            existingGameState.setLevel(newState.getLevel());
            existingGameState.setLife(newState.getLife());
            existingGameState.setPoints(newState.getPoints());
            return entityManager.merge(existingGameState);
        } else {
            throw new RuntimeException("No se encontró ningún GameState con el nombre: " + name);
        }
    }
}
