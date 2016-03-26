package View;

import Controller.PGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PGView extends JFrame {
    public PGController controller;

    /**
     * MENUBAR
     **/
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

    /**
     * WINDOW
     **/
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    /**
     * IMAGES
     **/
    BufferedImage crate;
    BufferedImage wall;
    BufferedImage victory_tile;
    BufferedImage userSprite;

    /**
     * OBJECTS
     **/
    private Board board;
    private Sprite sprite;
    private LeftComponents leftComponents;

    // Default constructor
    public PGView(PGController controller) {
        this.controller = controller;
        WINDOW_WIDTH = 450;
        WINDOW_HEIGHT = 600;

        makeWindow(WINDOW_WIDTH, WINDOW_HEIGHT);
        loadMenuBar();
        loadImages();
        createGui();
        this.setVisible(true);
    }

    void makeWindow(int WINDOW_WIDTH, int WINDOW_HEIGHT) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2);
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2);
        this.setTitle("Sokoban!");
        this.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setBackground(new Color(59, 59, 59)); // set background of the ui to grey
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
        contentPane.setPreferredSize(new Dimension(100, 100));
    }

    void loadImages() {
        try {
            crate = ImageIO.read(new File("images/crate_tile.gif"));
            wall = ImageIO.read(new File("images/gray_tile.gif"));
            victory_tile = ImageIO.read(new File("images/victory_tile.gif"));
            userSprite = ImageIO.read(new File("images/Sprite.gif"));
            victory_tile = ImageIO.read(new File("images/logo.png"));
        } catch (IOException e) {
            System.out.println("Images can't be found. " + e.getMessage());
            e.printStackTrace();
        }
    }

    void createGui() {
        //board = new Board(this);
        sprite = new Sprite();
        leftComponents = new LeftComponents();

    }
}