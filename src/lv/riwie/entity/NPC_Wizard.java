package lv.riwie.entity;

import java.util.Random;

import lv.riwie.main.GamePanel;

public class NPC_Wizard extends Entity {
    public NPC_Wizard(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("res/npc/wizard/walking_up1.png");
        up2 = setup("res/npc/wizard/walking_up2.png");
        up3 = setup("res/npc/wizard/walking_up3.png");
        up4 = setup("res/npc/wizard/walking_up4.png");
        down1 = setup("res/npc/wizard/walking_down1.png");
        down2 = setup("res/npc/wizard/walking_down2.png");
        down3 = setup("res/npc/wizard/walking_down3.png");
        down4 = setup("res/npc/wizard/walking_down4.png");
        left1 = setup("res/npc/wizard/walking_left1.png");
        left2 = setup("res/npc/wizard/walking_left2.png");
        left3 = setup("res/npc/wizard/walking_left3.png");
        left4 = setup("res/npc/wizard/walking_left4.png");
        right1 = setup("res/npc/wizard/walking_right1.png");
        right2 = setup("res/npc/wizard/walking_right2.png");
        right3 = setup("res/npc/wizard/walking_right3.png");
        right4 = setup("res/npc/wizard/walking_right4.png");
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }

    public void setDialogue() {
        dialogues[0] = "Hello, adventurer.";
        dialogues[1] = "So you've come to this land, \nto find the treasure.";
        dialogues[2] = "I used to be a great magitian - \nan adventurer, just like you!";
        dialogues[3] = "But now.. ";
        dialogues[4] = "all my magic powers have \nbeen drained by a mythical \ncreature that nested nearby.";
        dialogues[5] = "It was a long time ago.";
        dialogues[6] = "So long, that I barely remember \nhow having magic powers felt";
    }
}
