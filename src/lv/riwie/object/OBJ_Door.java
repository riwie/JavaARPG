package lv.riwie.object;

import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/door.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    } 
}
