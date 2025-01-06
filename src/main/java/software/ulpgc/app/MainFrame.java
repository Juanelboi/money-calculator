package software.ulpgc.app;

import software.ulpgc.control.Command;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.ImageDisplay;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog  ;
    private MoneyDisplay moneyDisplay;
    private ImageDisplay imageDisplay;

    public MainFrame() throws HeadlessException{
        this.setTitle("Money Calculator");
        this.setSize(1200,1000);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(toolbar(),BorderLayout.NORTH);
        this.add(createMoneyDisplay(),BorderLayout.SOUTH);
        this.add(createImageDisplay(),BorderLayout.CENTER);
    }


    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay=display;
        return display;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay=display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;

    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(createMoneyDialog());
        panel.add(createCurrencyDialog());
        panel.add(CalculateButton());
        return panel;
    }

    private JButton CalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> {
            try {
                commands.get("calculate").execute();
            } catch (IOException ex) {
                System.out.println("error");;
            }
        });
        return button;
    }

    public void add(String name, Command command){ commands.put(name,command); }

    public MoneyDisplay moneyDisplay() { return moneyDisplay; }

    public ImageDisplay imageDisplay() { return imageDisplay; }
    public MoneyDialog moneyDialog() { return moneyDialog; }

    public CurrencyDialog currencyDialog() { return currencyDialog; }
}
