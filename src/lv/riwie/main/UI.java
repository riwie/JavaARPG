package lv.riwie.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font purisaB;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int totalMenuOptionsCount = 3;
    public int currentOption = 0;
    public int totalOptionsCount = 1;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = new BufferedInputStream(
                    Files.newInputStream(Paths.get(System.getProperty("user.dir"), "res/font/Purisa.ttf")));
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE

        if (gp.gameState == gp.playState) {
            gp.gameTitle = "Doing something";
            // playstate
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
    }

    public void drawOptionsScreen() {
        gp.gameTitle = "Options";
        // background
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // title
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "OPTIONS";
        int x = getXforCenteredText(text);
        int y = (gp.screenHeight / 2) - gp.tileSize * 4;
        g2.drawString(text, x, y);
        // buttons
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        text = "BACK";
        x = getXforCenteredText(text) + (gp.tileSize * 6);
        y = gp.tileSize * 11;
        g2.drawString(text, x, y);
        if (currentOption == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawTitleScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "The Boy and the Forest";
        int x = getXforCenteredText(text), y = gp.tileSize * 3;
        // Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        // Main Text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // character image

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "OPTIONS";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        gp.gameTitle = "Paused";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "Paused";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 1, y + 1);
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2, y = gp.tileSize / 2, width = gp.screenWidth - (gp.tileSize * 4),
                height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - len / 2;
        return x;
    }
}
