package software.ulpgc.io.pojos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record ExchangeRateGetResponse(boolean success, long timeStamp, String Base, String date, Map<String, Double> rates) {

}
