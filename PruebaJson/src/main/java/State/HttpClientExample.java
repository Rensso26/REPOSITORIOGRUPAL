package State;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class HttpClientExample {

    private static final String BASE_URL = "http://localhost:8080/gamestate";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Crear un nuevo estado de juego
        GameState newGameState = new GameState();
        newGameState.setName("Rensso");
        newGameState.setLevel(1);
        newGameState.setLife(3);
        newGameState.setPoints(1000);
        saveGameState(httpClient, newGameState);

        // Obtener todos los estados de juego
        //getAllGameStates(httpClient);

        // Obtener un estado de juego por ID
        //getGameStateById(httpClient, 1L);

        // Actualizar un estado de juego
       // newGameState.setLevel(2);
        updateGameState(httpClient, 1L, newGameState);

        // Eliminar un estado de juego
        deleteGameState(httpClient, 1L);

        httpClient.close();
    }

    private static void saveGameState(CloseableHttpClient httpClient, GameState gameState) throws IOException {
        HttpPost request = new HttpPost(BASE_URL);
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(gameState), ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Saved GameState: " + responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getAllGameStates(CloseableHttpClient httpClient) throws IOException {
        HttpGet request = new HttpGet(BASE_URL);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("All GameStates: " + responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getGameStateById(CloseableHttpClient httpClient, Long id) throws IOException {
        HttpGet request = new HttpGet(BASE_URL + "/" + id);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("GameState by ID: " + responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateGameState(CloseableHttpClient httpClient, Long id, GameState gameState) throws IOException {
        HttpPut request = new HttpPut(BASE_URL + "/" + id);
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(gameState), ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Updated GameState: " + responseBody);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteGameState(CloseableHttpClient httpClient, Long id) throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/" + id);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println("Deleted GameState with ID: " + id);
        }
    }
}
