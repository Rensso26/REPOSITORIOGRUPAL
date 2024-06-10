package State;

import java.io.IOException;

public class GameManager {

    private GameStateService gameStateService;
    private GameState currentGameState;

    public GameManager() {
        this.gameStateService = new GameStateService();
        this.currentGameState = new GameState(); // Inicializar con valores predeterminados
    }

    // Método para guardar el estado actual del juego
    public void saveCurrentGameState(GameState gameState) {
        try {
            if (gameState.getId() != null) {
                // Si el ID no es nulo, actualiza el estado del juego existente
                gameStateService.updateGameState(gameState.getId(), gameState);
            } else {
                // Si el ID es nulo, guarda un nuevo estado del juego
                gameStateService.saveGameState(gameState);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Método para cargar el estado del juego por ID

    // Método para actualizar el estado actual del juego
    public void updateCurrentGameState() {
        try {
            gameStateService.updateGameState(currentGameState.getId(), currentGameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar el estado del juego
    public void deleteGameState(Long id) {
        try {
            gameStateService.deleteGameState(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameStateByName(String name) {
        try {
            currentGameState = gameStateService.getGameStateByName(name);
        } catch (IOException e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace for better logging practices
        } catch (RuntimeException e) {
            e.printStackTrace(); // Handle the runtime exceptions if necessary
        }
    }


    public void loadGameStateById(Long id) {
            try {
                currentGameState = gameStateService.getGameStateById(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    // Getters y Setters para el estado actual del juego
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }
}