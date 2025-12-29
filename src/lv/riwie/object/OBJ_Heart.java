package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        name = "Heart";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/heart_full.png"));
            image2 = ImageIO.read(new FileInputStream("res/objects/heart_half.png"));
            image3 = ImageIO.read(new FileInputStream("res/objects/heart_empty.png"));
            
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaledImage(image3, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
