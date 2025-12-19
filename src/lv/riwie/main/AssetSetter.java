package lv.riwie.main;

import lv.riwie.object.OBJ_Anvil;
import lv.riwie.object.OBJ_Boots;
import lv.riwie.object.OBJ_Chest;
import lv.riwie.object.OBJ_Door;
import lv.riwie.object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObjects() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 35 * gp.tileSize;
        gp.obj[0].worldY = 21 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 9 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 33 * gp.tileSize;
        gp.obj[7].worldY = 6 * gp.tileSize;

        gp.obj[8] = new OBJ_Anvil(gp);
        gp.obj[8].worldX = 37 * gp.tileSize;
        gp.obj[8].worldY = 8 * gp.tileSize;
    }
}
