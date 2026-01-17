package lv.riwie.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import lv.riwie.main.GamePanel;
import lv.riwie.main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(15, 30, 12, 9);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // PLAYER'S SPAWNPOINT COORDINATES
        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 23;

        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;

    }

    public void update() {

        if (keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter >= 12) {
                spriteIndex++;
                if (spriteIndex > 4) {
                    spriteIndex = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }

    }

    public void draw(Graphics2D g2) {
        try {
            BufferedImage image = null;
            switch (direction) {
                case "up":
                    if (spriteIndex == 1) {
                        image = up1;
                    }
                    if (spriteIndex == 2) {
                        image = up2;
                    }
                    if (spriteIndex == 3) {
                        image = up3;
                    }
                    if (spriteIndex == 4) {
                        image = up4;
                    }
                    break;
                case "down":
                    if (spriteIndex == 1) {
                        image = down1;
                    }
                    if (spriteIndex == 2) {
                        image = down2;
                    }
                    if (spriteIndex == 3) {
                        image = down3;
                    }
                    if (spriteIndex == 4) {
                        image = down4;
                    }
                    break;
                case "left":
                    if (spriteIndex == 1) {
                        image = left1;
                    }
                    if (spriteIndex == 2) {
                        image = left2;
                    }
                    if (spriteIndex == 3) {
                        image = left3;
                    }
                    if (spriteIndex == 4) {
                        image = left4;
                    }
                    break;
                case "right":
                    if (spriteIndex == 1) {
                        image = right1;
                    }
                    if (spriteIndex == 2) {
                        image = right2;
                    }
                    if (spriteIndex == 3) {
                        image = right3;
                    }
                    if (spriteIndex == 4) {
                        image = right4;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getPlayerImage() {
        up1 = setup("res/player/walking_up1.png");
        up2 = setup("res/player/walking_up2.png");
        up3 = setup("res/player/walking_up3.png");
        up4 = setup("res/player/walking_up4.png");
        down1 = setup("res/player/walking_down1.png");
        down2 = setup("res/player/walking_down2.png");
        down3 = setup("res/player/walking_down3.png");
        down4 = setup("res/player/walking_down4.png");
        left1 = setup("res/player/walking_left1.png");
        left2 = setup("res/player/walking_left2.png");
        left3 = setup("res/player/walking_left3.png");
        left4 = setup("res/player/walking_left4.png");
        right1 = setup("res/player/walking_right1.png");
        right2 = setup("res/player/walking_right2.png");
        right3 = setup("res/player/walking_right3.png");
        right4 = setup("res/player/walking_right4.png");
    }

}
