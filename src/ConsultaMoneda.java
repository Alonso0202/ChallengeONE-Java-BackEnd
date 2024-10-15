import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaMoneda {

    private static final String API_URL_BASE = "https://v6.exchangerate-api.com/v6/45b28f44a06b2d2e88df38bf/pair/";

    // Modificar el método para recibir monedas de origen y destino
    public ApiResponse obtenerTasas(String monedaOrigen, String monedaDestino) throws IOException {
        // Crear la URL dinámica con las monedas seleccionadas
        String apiUrl = API_URL_BASE + monedaOrigen + "/" + monedaDestino;
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), ApiResponse.class);
        } else {
            throw new IOException("Error en la solicitud: " + connection.getResponseCode());
        }
    }
}
