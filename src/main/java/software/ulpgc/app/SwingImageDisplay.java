package software.ulpgc.app;

import software.ulpgc.view.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private final double HORRIBLE = 50;
    private final double BAD = 75;
    private final double GOOD = 100;
    private final double GREAT = 150;
    private final double EXCELLENT = 200;

    private BufferedImage image = new BufferedImage(1,1,1);

    @Override
    public void show(double percentage) {
        getShownImage(percentage);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        int x = this.getWidth() / 2 - (image.getWidth(null) / 2);
        int y = this.getHeight() / 2 - (image.getHeight(null) / 2);
        g.drawImage(image, x,y,null);
    }

    private BufferedImage loadImage(String image) {
        try {
            return ImageIO.read(new URL(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void getShownImage(double percentage) {
        String[][] ranges = {
                {"HORRIBLE", "https://i.pinimg.com/736x/74/c8/16/74c816554fc8173a89edab6d5019943a.jpg"},
                {"BAD", "https://i.pinimg.com/736x/ca/f2/dd/caf2dd274fca529726b791c6978cd06f.jpg"},
                {"GOOD", "https://bluemoji.io/cdn-proxy/646218c67da47160c64a84d5/66b3e5e1ccde70cabd67779a_84.png"},
                {"GREAT", "https://bluemoji.io/cdn-proxy/646218c67da47160c64a84d5/66b3e5d0c2ab246786ca1d5e_86.png"},
                {"EXCELLENT", "https://i.pinimg.com/736x/3f/cd/41/3fcd41afbd0f582acd7473a43f8800ec.jpg"}
        };

        String fallbackUrl = "https://bluemoji.io/cdn-proxy/646218c67da47160c64a84d5/66b3e594607b5d1305d4f5b7_90.png";

        for (String[] range : ranges) {
            double threshold = getThreshold(range[0]);
            if (percentage <= threshold) {
                image = loadImage(range[1]);
                return;
            }
        }

        image = loadImage(fallbackUrl);
    }

    private double getThreshold(String level) {
        return switch (level) {
            case "HORRIBLE" -> HORRIBLE;
            case "BAD" -> BAD;
            case "GOOD" -> GOOD;
            case "GREAT" -> GREAT;
            case "EXCELLENT" -> EXCELLENT;
            default -> 0;
        };
    }


}
