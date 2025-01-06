package software.ulpgc.app;

import software.ulpgc.control.Command;
import software.ulpgc.io.ExchangeRateLoader;
import software.ulpgc.control.ExchangeMoneyCommand;
import software.ulpgc.model.Currency;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                mainFrame.moneyDialog().define(currencies),
                mainFrame.currencyDialog().define(currencies),
                new ExchangeRateLoader(),
                mainFrame.moneyDisplay(),
                mainFrame.imageDisplay()
                );
        mainFrame.add("calculate", command);
        mainFrame.setVisible(true);

    }
}