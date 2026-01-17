package lv.riwie.main;

import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = gp.tileSize/2;
        eventRect.y = gp.tileSize/2;
        eventRect.width = 23;
        eventRect.height = 23;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        // damage pit: x = 18, y = 17
//        if (hit(18, 17, "left")) {damagePit(gp.dialogueState);}
        if (hit(15, 6, "up")) {healingPool(gp.dialogueState);}
        if (hit(18, 17, "left")) {teleport(gp.dialogueState);}
    }


    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;


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
