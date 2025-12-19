package lv.riwie.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Deque;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    private final Deque<Integer> directionOrder = new ArrayDeque<>();

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

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
                rememberDirection(code);
            } else if (code == KeyEvent.VK_S) {
                downPressed = true;
                rememberDirection(code);
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
                rememberDirection(code);
            } else if (code == KeyEvent.VK_D) {
                rightPressed = true;
                rememberDirection(code);
            }

            if (code == KeyEvent.VK_P) {
                gp.playSFX(5);
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
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
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
            forgetDirection(code);
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            forgetDirection(code);
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
            forgetDirection(code);
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            forgetDirection(code);
        }
    }

    // Return the most recently pressed direction key that is still held, or null.
    public String getCurrentDirection() {
        while (!directionOrder.isEmpty()) {
            int code = directionOrder.peekLast();
            if (isDirectionStillHeld(code)) {
                return codeToDirection(code);
            }
            directionOrder.pollLast();
        }
        return null;
    }

    private void rememberDirection(int code) {
        // Maintain uniqueness then push to the back as most recent.
        directionOrder.remove(code);
        directionOrder.addLast(code);
    }

    private void forgetDirection(int code) {
        directionOrder.remove(code);
    }

    private boolean isDirectionStillHeld(int code) {
        return switch (code) {
            case KeyEvent.VK_W -> upPressed;
            case KeyEvent.VK_S -> downPressed;
            case KeyEvent.VK_A -> leftPressed;
            case KeyEvent.VK_D -> rightPressed;
            default -> false;
        };
    }

    private String codeToDirection(int code) {
        return switch (code) {
            case KeyEvent.VK_W -> "up";
            case KeyEvent.VK_S -> "down";
            case KeyEvent.VK_A -> "left";
            case KeyEvent.VK_D -> "right";
            default -> null;
        };
    }

}