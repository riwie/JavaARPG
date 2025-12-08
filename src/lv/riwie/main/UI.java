package lv.riwie.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import lv.riwie.object.OBJ_Key;

public class UI {
    GamePanel gp;
    Font gameFont = new Font("Minecraft RUS", Font.PLAIN, 40);
    Font congratulationsFont = new Font("Minecraft RUS", Font.BOLD, 50);
    OBJ_Key key = new OBJ_Key();
    BufferedImage keyImage = key.image;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(gameFont);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize*5);
            g2.drawString(text, x, y);

            g2.setFont(congratulationsFont);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {

            g2.setFont(gameFont);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x" + gp.player.hasKey, 75, 50);

            playTime += (double)1/60;
            g2.drawString("Time" + dFormat.format(playTime), gp.tileSize*11, 50);

            if (messageOn == true) {
                // gp.setFont(gp.getFont(gameFont).deriveFont(30F));
                g2.drawString(message, gp.tileSize * 4, gp.tileSize * 10);
                messageCounter++;
                if (messageCounter > 120) {

                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
