package software.ulpgc.app;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {



    @Override
    public void show(Money money, double percentage) {
        removeAll();
        getDisplayOfMoney(money,percentage);
        revalidate();
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.GRAY);

    }

    private void getDisplayOfMoney(Money money,double percentage) {
        String formatedPercentage = String.format("%.3f", percentage);
        JTextArea text = new JTextArea();
        if (percentage >100){
            text.setForeground(Color.GREEN);
        }else {
            text.setForeground(Color.RED);
        }
        text.setFont(new Font("Arial", Font.PLAIN, 30));
        text.setBackground(new Color(238,238,238));
        text.setText("Is equal to "+money.toString() + "    " +"Rate= "+ formatedPercentage+"%");
        this.add(text);

    }

}
