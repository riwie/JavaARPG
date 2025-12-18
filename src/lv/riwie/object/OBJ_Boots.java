package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        name = "Boots";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/boots.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
