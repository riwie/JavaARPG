package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class OBJ_Anvil extends SuperObject {
    public OBJ_Anvil() {
        name = "Anvil";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/anvil.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
