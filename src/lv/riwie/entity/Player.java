package lv.riwie.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;
import lv.riwie.main.KeyHandler;
import lv.riwie.main.UtilityTool;

import java.io.FileInputStream;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 4;
        direction = "down";
    }

    public void update() {

        if (keyH.upPressed == true || keyH.leftPressed == true || keyH.downPressed == true
                || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
            String objectName = gp.obj[i].name;
            if (objectName == "Key") {
                gp.playSFX(2);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("You've got a key!");
            } else if (objectName == "Door") {
                if (hasKey > 0) {
                    gp.playSFX(1);
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the door!");
                } else {
                    gp.ui.showMessage("The door is locked");
                }
            } else if (objectName == "Boots") {
                speed += 1;
                gp.playSFX(3);
                gp.obj[i] = null;
                gp.ui.showMessage("Speed up!");

            } else if (objectName == "Anvil") {
                speed -= 1;
                gp.playSFX(4);
                gp.obj[i] = null;
                gp.ui.showMessage("Slow down!");
            } else if (objectName == "Chest") {
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSFX(3);
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
        up1 = setup("Character_walking_up_1");
        up2 = setup("Character_walking_up_2");
        down1 = setup("Character_walking_down_1");
        down2 = setup("Character_walking_down_2");
        left1 = setup("Character_walking_left_1");
        left2 = setup("Character_walking_left_2");
        right1 = setup("Character_walking_right_1");
        right2 = setup("Character_walking_right_2");

    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new FileInputStream("res/player/" + imageName + ".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
