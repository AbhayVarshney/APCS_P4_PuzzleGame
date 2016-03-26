package View;

import Controller.PGController;
import javax.swing.*;
import java.awt.*;

// hello. how are you...

public class PGView extends JFrame {
    public PGController controller;

    /** MENUBAR **/
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenuItem mntmHowToPlay;
    private JMenuItem mntmAbout;
    private JMenuItem mntmNewGame;
    private JMenuItem mntmHighScore;
    private JMenuItem mntmExit;
    private JMenu mnOption;
    private JMenuItem mntmGameType;
    private JMenu mnHelp;

    /** WINDOW **/
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    private Board board;
    private Sprite sprite;
    private LeftComponents leftComponents;

    // Default constructor
    public PGView(PGController controller) {
        this.controller = controller;

        WINDOW_WIDTH = 450;
        WINDOW_HEIGHT = 600;

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2);
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2);
        this.setTitle("Sokoban!");
        this.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        loadMenuBar();
        createGui();
        this.setVisible(true);
    }

    void loadMenuBar() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu mnGame = new JMenu("Game");
        menuBar.add(mnGame);

        mntmNewGame = new JMenuItem("New Game");
        mnGame.add(mntmNewGame);

        mntmHighScore = new JMenuItem("High Scores");
        mnGame.add(mntmHighScore);

        mntmExit = new JMenuItem("Exit");
        mnGame.add(mntmExit);

        mnOption = new JMenu("Option");
        menuBar.add(mnOption);

        mntmGameType = new JMenuItem("Game Type");
        mnOption.add(mntmGameType);

        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        mntmHowToPlay = new JMenuItem("How to Play");
        mnHelp.add(mntmHowToPlay);

        mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);

        JMenuItem menuItem_1 = new JMenuItem("");
        menuBar.add(menuItem_1);

        JMenuItem menuItem = new JMenuItem("");
        menuBar.add(menuItem);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(100,100));
    }

    void createGui() {

    }

}

class Board {

}

class Sprite {

}

class LeftComponents {

}
