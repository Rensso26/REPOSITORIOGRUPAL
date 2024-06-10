package ec.edu.uce.persistence.controller;

import ec.edu.uce.persistence.service.GameStateService;
import ec.edu.uce.persistence.state.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-state")
public class GameStateController {

    @Autowired
    private GameStateService service;

    @PostMapping
    public GameState save(@RequestBody GameState gameState) {
        return service.save(gameState);
    }

    @GetMapping("/name/{name}")
    public GameState findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/{id}")
    public GameState findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public GameState update(@PathVariable Long id, @RequestBody GameState gameState) {
        return service.update(id, gameState);
    }
    @PutMapping("/name/{name}")
    public GameState updateByName(@PathVariable String name, @RequestBody GameState gameState) {
        return service.update(name, gameState);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
