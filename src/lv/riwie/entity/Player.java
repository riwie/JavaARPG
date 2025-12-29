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

        if (keyH.upPressed == true || keyH.leftPressed == true || keyH.downPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if (collisionOn == false) {
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
                if (spriteIndex == 1) {
                    spriteIndex = 2;
                } else if (spriteIndex == 2) {
                    spriteIndex = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteIndex = 1;
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
        gp.keyH.enterPressed = false;
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
                    break;
                case "down":
                    if (spriteIndex == 1) {
                        image = down1;
                    }
                    if (spriteIndex == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteIndex == 1) {
                        image = left1;
                    }
                    if (spriteIndex == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteIndex == 1) {
                        image = right1;
                    }
                    if (spriteIndex == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getPlayerImage() {
        up1 = setup("res/player/Character_walking_up_1.png");
        up2 = setup("res/player/Character_walking_up_2.png");
        down1 = setup("res/player/Character_walking_down_1.png");
        down2 = setup("res/player/Character_walking_down_2.png");
        left1 = setup("res/player/Character_walking_left_1.png");
        left2 = setup("res/player/Character_walking_left_2.png");
        right1 = setup("res/player/Character_walking_right_1.png");
        right2 = setup("res/player/Character_walking_right_2.png");
    }

}
