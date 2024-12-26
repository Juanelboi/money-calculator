package software.ulpgc.io;

import software.ulpgc.app.SwingImageDisplay;
import software.ulpgc.control.Command;
import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.ImageDisplay;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;

import java.io.IOException;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;
    private final ImageDisplay imageDisplay;


    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay, ImageDisplay imageDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
        this.imageDisplay = imageDisplay;
    }


    @Override
    public void execute() throws IOException {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        //double exchangeRate = exchangeRateLoader.load(money.currency(),currency);

        //Money result = new Money((long) (money.amount()*exchangeRate),currency);
        //System.out.println(exchangeRate);

        //double percentage = exchangeRateLoader.getPercentage();

       // moneyDisplay.show(result, percentage);
        imageDisplay.show(100);
    }
}
