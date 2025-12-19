package lv.riwie.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
    InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get(System.getProperty("user.dir"), "res/font/Purisa.tff")));
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
        g2.setColor(Color.white);

        // PLAY STATE

        if (gp.gameState == gp.playState) {
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
    }

    public void drawPauseScreen() {
        String text = "Paused";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2, y = gp.tileSize/2, width = gp.screenWidth - (gp.tileSize*4), height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line,x,y);
            y+= 40;  
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255,255,255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }


    public int getXforCenteredText(String text) {
        int len = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - len/2;
        return x;
    }
}
