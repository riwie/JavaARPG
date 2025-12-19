package lv.riwie.main;

import lv.riwie.entity.NPC_Wizard;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObjects() {

    }
    public void setNPC() {
        gp.npc[0] = new NPC_Wizard(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }
}
