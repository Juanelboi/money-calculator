package software.ulpgc.view;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;

import java.util.List;

public interface MoneyDialog {
    MoneyDialog define(List<Currency> currencies);
    Money get();
}
