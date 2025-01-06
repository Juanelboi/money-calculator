package software.ulpgc.io;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.io.IOException;
import java.net.MalformedURLException;

public interface RateLoader {
    double load(Currency from, Currency to) throws IOException;
}
