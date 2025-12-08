package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
