package State;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class GameStateService {

    private static final String BASE_URL = "http://localhost:8080/game-state";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Método para guardar el estado del juego as
    public void saveGameState(GameState gameState) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL);
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(gameState), ContentType.APPLICATION_JSON);
            request.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Estado del juego guardado: " + responseBody);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para obtener el estado del juego por ID
    public GameState getGameStateById(Long id) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL + "/" + id);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return objectMapper.readValue(responseBody, GameState.class);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public GameState getGameStateByName(String name) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL + "/name/" + name);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                if (statusCode != 200) {
                    throw new IOException("Unexpected status code: " + statusCode);
                }
                String responseBody = EntityUtils.toString(response.getEntity());
                return objectMapper.readValue(responseBody, GameState.class);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing the response", e);
            }
        }
    }

    // Método para actualizar el estado del juego
    public void updateGameState(Long id, GameState gameState) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut request = new HttpPut(BASE_URL + "/" + id);
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(gameState), ContentType.APPLICATION_JSON);
            request.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Estado del juego actualizado: " + responseBody);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Método para eliminar el estado del juego
    public void deleteGameState(Long id) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete request = new HttpDelete(BASE_URL + "/" + id);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println("Estado del juego eliminado con ID: " + id);
            }
        }
    }


}
