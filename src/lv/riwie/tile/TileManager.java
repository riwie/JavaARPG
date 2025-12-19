package lv.riwie.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import lv.riwie.main.GamePanel;
import lv.riwie.main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[64];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("res/maps/map03.txt");
    }

    public void getTileImage() {
        //NOTE: don't add in-between indexes - it breaks the map
        // PLACEHOLDER
        setup(0, "placeholder", false);
        setup(1, "placeholder", false);
        setup(2, "placeholder", false);
        setup(3, "placeholder", false);
        setup(4, "placeholder", false);
        setup(5, "placeholder", false);
        setup(6, "placeholder", false);
        setup(7, "placeholder", false);
        setup(8, "placeholder", false);
        setup(9, "placeholder", false);
        setup(10, "placeholder", false);

        // TILES
        setup(11, "sand11", false);
        setup(12, "sand12", false);
        setup(13, "sand13", false);
        setup(14, "sand14", false);
        setup(15, "sand15", false);
        setup(16, "sand16", false);
        setup(17, "sand17", false);
        setup(18, "sand18", false);
        setup(19, "sand19", false);
        setup(20, "sand20", false);
        setup(21, "sand21", false);
        setup(22, "sand22", false);
        setup(23, "sand23", false);
        setup(24, "sand24", false);
        setup(25, "sand25", false);
        setup(26, "sand26", false);
        setup(27, "sand27", false);
        setup(28, "sand28", false);
        setup(29, "water29", true);
        setup(30, "water30", true);
        setup(31, "water31", true);
        setup(32, "water32", true);
        setup(33, "water33", true);
        setup(34, "water34", true);
        setup(35, "water35", true);
        setup(36, "water36", true);
        setup(37, "water37", true);
        setup(38, "water38", true);
        setup(39, "water39", true);
        setup(40, "water40", true);
        setup(41, "water41", true);
        setup(42, "grass1", false);
        setup(43, "grass2", false);
        setup(44, "dirt", false);
        setup(45, "bricks", true);
        setup(46, "tree", true);
        setup(47, "water42", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            file.close();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
