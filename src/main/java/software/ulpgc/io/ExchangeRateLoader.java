package software.ulpgc.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import software.ulpgc.app.FixerAPI;
import software.ulpgc.io.pojos.ExchangeRateGetResponse;
import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.jsoup.Connection.Method.GET;
import static software.ulpgc.io.pojos.ExchangeRateGetResponse.*;

public class ExchangeRateLoader implements RateLoader {
    private static final String BaseURL = "https://api.exchangeratesapi.io/v1/latest";
    private  double percentage;
    private ExchangeRateDeserializer deserializer;

    public ExchangeRateLoader() {
        this.deserializer = new ExchangeRateDeserializer();
    }


    @Override
    public double load(Currency from, Currency to) throws IOException {


        ExchangeRateGetResponse rates = deserializer.deserialize(readFromApi(getURLforRate(from, to)));

        double rate = getRate(from, to, rates);
        percentage = rate *100;

        return  rate;
    }

    private double getRate(Currency from, Currency to, ExchangeRateGetResponse rates) {
        double fromRate = rates.rates().get(getBaseCode(from));
        double toRate = rates.rates().get(getBaseCode(to));

        double rate = toRate / fromRate;
        return rate;
    }

    private String getBaseCode(Currency currency) {
        return currency.code();
    }


    private String getURLforRate(Currency from, Currency to) throws MalformedURLException {
        String URL = BaseURL
                + "?access_key=" + FixerAPI.key
                + "&symbols=" +  from.code()+","+to.code();
        return URL;
    }

    private static String readFromApi(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .ignoreContentType(true)
                .header("accept", "text/*")
                .method(GET)
                .execute();
        if (response.statusCode()!=200) throw new RuntimeException("No se pudo conectar");
        return response.body();
    }

    public double getPercentage() {
        return percentage;
    }
}
