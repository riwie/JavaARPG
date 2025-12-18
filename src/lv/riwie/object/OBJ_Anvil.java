package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;

public class OBJ_Anvil extends SuperObject {
    GamePanel gp;

    public OBJ_Anvil(GamePanel gp) {
        name = "Anvil";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/anvil.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
