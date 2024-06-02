package ec.edu.uce.persistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.edu.uce.persistence.services.GameStateService;
import ec.edu.uce.persistence.state.GameState;

import java.util.Optional;

@RestController
@RequestMapping("/api/game-state")
public class GameStateController {
    @Autowired
    private GameStateService service;

    @PostMapping
    public ResponseEntity<GameState> saveGameState(@RequestBody GameState gameState) {
        GameState savedGameState = service.saveGameState(gameState);
        return ResponseEntity.ok(savedGameState);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GameState> getGameState(@PathVariable String name) {
        Optional<GameState> gameState = service.getGameState(name);
        return gameState.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
