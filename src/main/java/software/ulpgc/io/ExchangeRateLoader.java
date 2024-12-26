package software.ulpgc.control;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import software.ulpgc.app.FixerAPI;
import software.ulpgc.io.RateLoader;
import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ExchangeRateLoader implements RateLoader {
    private static final String BaseURL = "https://api.exchangeratesapi.io/v1/latest";
    private  double percentage;

    @Override
    public ExchangeRate load(Currency from, Currency to) throws IOException {

        HttpURLConnection connection = getConnection(getURLforRate(from, to));

        try (InputStream is = connection.getInputStream()) {
            String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

            if (!jsonObject.get("success").getAsBoolean()) this
        }

            return ;


    }

    private HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode()!=200) throw new RuntimeException("Couldn't connect");
        return connection;
    }

    private URL getURLforRate(Currency from, Currency to) throws MalformedURLException {
        String URL = BaseURL
                + "?access_key=" + FixerAPI.key
                + "&symbols=" + from.code() + "," + to.code();
        java.net.URL url= new URL(URL);
        return url;
    }
}
