package lv.riwie.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public boolean confirmKey, cancelKey;

    public boolean exittingBegins = false;

    public long f4PressTime = System.nanoTime();
    public long passedTime = 0;

    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        confirmKey = code == KeyEvent.VK_Z || code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE;
        cancelKey = code == KeyEvent.VK_X || code == KeyEvent.VK_CONTROL || code == KeyEvent.VK_ESCAPE;

        if (code == KeyEvent.VK_F4) { // game quitting logic
            passedTime += f4PressTime;
            exittingBegins = true;
            if ((double) passedTime / 1000000000 >= 50000000) {
                System.exit(0);
            }
            // System.exit(0);
        }

        // TITLE STATE
        if (gp.gameState == gp.titleState) { // title screen logic
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { // menu up
                gp.ui.commandNum--;

                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = gp.ui.totalMenuOptionsCount;
                }

            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { // menu down
                gp.ui.commandNum++;
                if (gp.ui.commandNum > gp.ui.totalMenuOptionsCount) {
                    gp.ui.commandNum = 0;
                }
            } else if (confirmKey) { // menu select
                if (gp.ui.commandNum == 0) {
                    // new game
                    gp.gameState = gp.playState;
                    // gp.playMusic(0);
                } else if (gp.ui.commandNum == 1) {
                    // load game logic
                } else if (gp.ui.commandNum == 2) {
                    // options logic
                    gp.gameState = gp.optionsState;
                } else if (gp.ui.commandNum == 3) {
                    // quit logic
                    System.exit(0);
                }
            }
        }

        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) { // options logic

            if (cancelKey) {
                // if (gp.ui.currentOption == 0) {
                // // back button logic
                gp.gameState = gp.titleState;
                // }
            }
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            // movement
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            // pause
            if (code == KeyEvent.VK_P) {
                gp.playSFX(5);
                gp.gameState = gp.pauseState;
            }

            // interact
            if (confirmKey) {
                enterPressed = true;
            }

            // DEBUG
            if (code == KeyEvent.VK_T) {
                if (!checkDrawTime) {
                    checkDrawTime = true;
                } else {
                    checkDrawTime = false;
                }
            }
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.playSFX(5);
                gp.gameState = gp.playState;
            }
        }

        // DIALOGUE STATE

        else if (gp.gameState == gp.dialogueState) {
            if (confirmKey) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F4) {
            passedTime = 0;
            exittingBegins = false;
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

}