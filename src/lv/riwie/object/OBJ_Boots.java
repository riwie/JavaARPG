package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
        public OBJ_Boots() {
        name = "Boots";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/boots.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
