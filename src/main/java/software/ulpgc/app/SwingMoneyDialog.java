package software.ulpgc.app;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private JTextField amountfield;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() { this.setLayout(new FlowLayout()); }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog =dialog;
        return dialog;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        this.amountfield = textField;
        return textField;
    }

    @Override
    public Money get() {
        return new Money(toLong(amountfield.getText()),currencyDialog.get());
    }

    private Long toLong(String text) {
        return Long.parseLong(text);
    }
}
