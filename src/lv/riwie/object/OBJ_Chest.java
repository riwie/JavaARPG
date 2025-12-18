package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        name = "Chest";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
