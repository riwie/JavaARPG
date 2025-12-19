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
        loadMap("res/maps/map02.txt");
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

        // TILES
        setup(10, "sand1", false);
        setup(11, "sand2", false);
        setup(13, "sand3", false);
        setup(14, "sand4", false);
        setup(15, "sand5", false);
        setup(16, "sand6", false);
        setup(17, "sand7", false);
        setup(18, "sand8", false);
        setup(19, "sand9", false);
        setup(20, "sand10", false);
        setup(21, "sand11", false);
        setup(22, "sand12", false);
        setup(23, "sand13", false);
        setup(24, "sand14", false);
        setup(25, "sand15", false);
        setup(26, "sand16", false);
        setup(27, "sand17", false);
        setup(28, "sand18", false);
        setup(29, "water1", false);
        setup(30, "water2", false);
        setup(30, "water3", false);
        setup(30, "water4", false);
        setup(30, "water5", false);
        setup(30, "water6", false);
        setup(30, "water7", false);
        setup(30, "water8", false);
        setup(30, "water9", false);
        setup(30, "water10", false);
        setup(30, "water11", false);
        setup(30, "water12", false);
        setup(30, "water13", false);
        setup(30, "water14", false);
        setup(31, "grass1", false);
        setup(32, "grass2", false);
        setup(33, "dirt", false);
        setup(34, "bricks", false);
        setup(35, "tree", false);

        
        // setup(1, "grass2", false);
        // setup(2, "bricks", true);
        // // setup(3, "water", true);
        // setup(4, "dirt", false);
        // setup(6, "tree", true);
        // setup(7, "sand1", false);
        // setup(8, "sand2", false);
        // setup(10, "water1", true);
        // setup(11, "water2", true);
        // setup(12, "water3", true);
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
