package software.ulpgc.io;

import com.google.gson.Gson;
import software.ulpgc.io.pojos.ExchangeRateGetResponse;

public class ExchangeRateDeserializer {

    public ExchangeRateGetResponse deserialize(String json){
        ExchangeRateGetResponse response = new Gson().fromJson(json, ExchangeRateGetResponse.class);
        return response;
    }
}
