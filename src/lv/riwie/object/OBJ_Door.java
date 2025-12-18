package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Door extends SuperObject {

    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        name = "Door";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/door.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
