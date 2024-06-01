package ec.edu.uce.persistence.controller;

import ec.edu.uce.persistence.state.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ec.edu.uce.persistence.services.GameStateService;

import java.util.List;

@RestController
@RequestMapping("/api/game-state")
public class GameStateController {
    @Autowired
    private GameStateService service;

    @PostMapping
    public GameState save(@RequestBody GameState gameState) {
        return service.save(gameState);
    }

    @GetMapping("/player/{playerId}")
    public List<GameState> findByPlayerId(@PathVariable String playerId) {
        return service.findByPlayerId(playerId);
    }

    @GetMapping("/{id}")
    public GameState findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
