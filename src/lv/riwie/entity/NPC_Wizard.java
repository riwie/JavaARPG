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
        up1 = setup("res/npc/wizard_up1.png");
        up2 = setup("res/npc/wizard_up2.png");
        down1 = setup("res/npc/wizard_down1.png");
        down2 = setup("res/npc/wizard_down2.png");
        left1 = setup("res/npc/wizard_left1.png");
        left2 = setup("res/npc/wizard_left2.png");
        right1 = setup("res/npc/wizard_right1.png");
        right2 = setup("res/npc/wizard_right2.png");
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
