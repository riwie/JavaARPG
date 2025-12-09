package lv.riwie.main;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    static JFrame launcher = new JFrame("Launcher");
    static JButton launchButton = new JButton("Launch");
    static JButton settingsButton = new JButton("Settings");
    static JFrame options = new JFrame("Options");
    static GamePanel gp = new GamePanel();
    static Properties prop = new Properties();
    
    static boolean allowMusic;

    public static void main(String[] args) throws Exception {
        loadConfig();
        setupLauncher();
    }

    public static void loadConfig() {
        try {
            File config = new File(Paths.get(System.getProperty("user.dir"), "res/configs/options.ini").toString());
            FileInputStream inputStream = new FileInputStream(config);
            prop.load(inputStream);
            allowMusic = Boolean.getBoolean(prop.getProperty("music"));
            System.out.println(allowMusic);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runGame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Boy and the Forest");

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public static void setupLauncher() {
        // LAUNCHER SETUP
        launcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launcher.setSize(400, 200);
        launcher.setResizable(false);
        launcher.setLocationRelativeTo(null);

        // BUTTONS SETUP
        launchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                options.dispose();
                launcher.dispose();
                runGame();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setupOptionsMenu();
            }
        });

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(launchButton);
        container.add(settingsButton);

        launcher.setVisible(true);
        GridBagConstraints grid = new GridBagConstraints();
        grid.anchor = GridBagConstraints.CENTER;
        grid.gridy = 3;
        grid.gridx = 0;
        grid.gridwidth = 1;
        grid.gridheight = 1;
        launcher.add(container);
    }

    public static void setupOptionsMenu() {

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridBagConstraints grid = new GridBagConstraints();
        grid.anchor = GridBagConstraints.CENTER;
        grid.gridy = 3;
        grid.gridx = 0;
        grid.gridwidth = 1;
        grid.gridheight = 1;
        options.add(container);

        options.setSize(400, 200);
        options.setResizable(false);
        options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        options.setLocationRelativeTo(null);
        options.setVisible(true);

        JLabel musicSwitchLabel = new JLabel("Music: ");
        JToggleButton musicSwitch = new JToggleButton("ON");
        musicSwitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (allowMusic) {
                    allowMusic = false;
                    musicSwitch.setText("OFF");
                } else {
                    allowMusic = true;
                    musicSwitch.setText("ON");

                }
                System.out.println(allowMusic);
                prop.setProperty("music", Boolean.toString(allowMusic));
                gp.playMusic = allowMusic;
            }
        });
        container.add(musicSwitchLabel);
        container.add(musicSwitch);
    }
}
