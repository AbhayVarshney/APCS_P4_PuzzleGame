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

    /** MENUBAR **/
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenuItem mntmHowToPlay;
    private JMenuItem mntmAbout;
    private JMenuItem mntmNewGame;
    private JMenuItem mntmHighScore;
    private JMenuItem mntmExit;
    private JMenu mnOption;
    private JMenuItem mntmLevel1;
    private JMenuItem mntmLevel2;
    private JMenuItem mntmLevel3;
    private JMenuItem mntmLevel4;
    private JMenuItem mntmLevel5;
    private JMenu mnHelp;

    /** WINDOW **/
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    /** IMAGES **/
    public BufferedImage crate;
    public BufferedImage wall;
    public BufferedImage victory_tile;
    public BufferedImage userSprite;
    private int cellSize;

    /** OBJECTS **/
    private Board board;
    private Sprite sprite;
    private LeftComponents leftComponents;

    /** BOARD IMPORTANT **/
    public char[][] boardContent;

    // Default constructor
    public PGView(char[][] board, PGController controller) {
        this.controller = controller;
        this.boardContent = new char[board.length][board[0].length];
        WINDOW_WIDTH = 850;
        WINDOW_HEIGHT = 600;
        cellSize = 35;

        // COPYING/UPDATING BOARD CONTENT
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                boardContent[i][j] = board[i][j];
            }
        }

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
        this.getContentPane().setBackground(new Color(59,59,59)); // set background of the ui to grey
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

        mnOption = new JMenu("Level");
        menuBar.add(mnOption);

        mntmLevel1 = new JMenuItem("Level 1");
        mnOption.add(mntmLevel1);

        mntmLevel2 = new JMenuItem("Level 2");
        mnOption.add(mntmLevel1);

        mntmLevel3 = new JMenuItem("Level 3");
        mnOption.add(mntmLevel1);

        mntmLevel4 = new JMenuItem("Level 4");
        mnOption.add(mntmLevel1);

        mntmLevel5 = new JMenuItem("Level 5");
        mnOption.add(mntmLevel1);

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
        } catch (IOException e) {
            System.out.println("Images can't be found. " + e.getMessage());
            e.printStackTrace();
        }
    }

    void createGui() {
        board = new Board(this, cellSize);
        board.setPreferredSize(new Dimension(700, 400));
        contentPane.add(board);
        sprite = new Sprite();
        leftComponents = new LeftComponents();
    }

    void newLevelPrompter() {
        String wonGameMessage = "Looks like you want to skip to the next level!";
        JOptionPane wonGame = new JOptionPane();
        wonGame.showInternalMessageDialog(this.getContentPane(), wonGameMessage, "Next level!",
                JOptionPane.WARNING_MESSAGE, null);
    }

    void gameWonDialog() {
        String wonGameMessage = "Congrats! You beat the game!\n Would you like to play again?";
        JOptionPane wonGame = new JOptionPane();
        int response = wonGame.showInternalConfirmDialog(this.getContentPane(), wonGameMessage, "You win!",
                JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if(response == 0) controller.userLevel++; // move to the next level!
        else this.dispose();           // quit

    }
}