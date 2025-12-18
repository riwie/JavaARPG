package lv.riwie.object;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Key extends SuperObject {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        name = "Key";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
