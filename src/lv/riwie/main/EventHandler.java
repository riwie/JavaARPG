package lv.riwie.main;

import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

       int col = 0, row = 0;
       while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
           eventRect[col][row] = new EventRect();
           eventRect[col][row].x = gp.tileSize/2;
           eventRect[col][row].y = gp.tileSize/2;
           eventRect[col][row].width = 23;
           eventRect[col][row].height = 23;
           eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
           eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
           col++;
           if (col == gp.maxWorldCol) {
               col = 0;
               row++;
           }
       }


    }

    public void checkEvent() {
        // damage pit: x = 18, y = 17
//        if (hit(18, 17, "left")) {damagePit(gp.dialogueState);}
        if (hit(15, 6, "up")) {healingPool(gp.dialogueState);}
        if (hit(18, 17, "left")) {teleport(gp.dialogueState);}
    }


    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row])) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;


        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
    }
    public void healingPool(int gameState) {
        if (gp.keyH.confirmKey) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink from the pond.\nYour life has been recovered";
            gp.player.life = gp.player.maxLife;
        }
    }
    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize*34;
        gp.player.worldY = gp.tileSize*6;
    }
}
