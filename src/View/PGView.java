package View;

import Controller.MyHighScores;
import Controller.PGController;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PGView extends JFrame {
    /** MENUBAR **/
    public JFrame panel;
    public JPanel contentPane;
    public JMenuBar menuBar;
    public JMenuItem mntmHowToPlay;
    public JMenuItem mntmAbout;
    public JMenuItem mntmNewGame;
    public JMenuItem mntmHighScore;
    public JMenuItem mntmExit;
    public JMenu mnLevel;
    public JMenuItem mntmLevel1;
    public JMenuItem mntmLevel2;
    public JMenuItem mntmLevel3;
    public JMenuItem mntmLevel4;
    public JMenuItem mntmLevel5;
    public JMenu mntmMultiplayer;
    public JMenuItem mntmLocal;
    public JMenuItem mntmSinglePlayerMode;
    public JMenu mnHelp;

    /** WINDOW **/
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    
    /** PANEL **/
    private JPanel optionsPanel;
    private JPanel logoSign;
    private JLabel moves;
    public JLabel movesCount;
    public JButton leftButton;
    public JButton rightButton;

    /** IMAGES **/
    public BufferedImage crate;
    public BufferedImage completedCrate;
    public BufferedImage wall;
    public BufferedImage victory_tile;
    public BufferedImage userSprite;
    public BufferedImage player2;
    public BufferedImage logo;
    private int cellSize;

    /** OBJECTS **/
    public Board board;
    public Logo topLogo;
    public PGController controller;

    /** BOARD IMPORTANT **/
    public char[][] boardContent;

    /** OPTIONS PANEL **/
    public JLabel timeText;
    public JLabel time;
    
    // Constructor
    public PGView(char[][] board, PGController controller) {
        this.controller = controller;
        this.boardContent = new char[board.length][board[0].length];
        WINDOW_WIDTH = 850;
        WINDOW_HEIGHT = 750;
        cellSize = 35;

        updateBoardContent(board);

        makeWindow(WINDOW_WIDTH, WINDOW_HEIGHT);
        loadMenuBar();
        loadImages();
        createGui();
        this.getContentPane().setBackground(new Color(59,59,59)); // set background of the ui to grey
        this.setVisible(true);
    }

    private void makeWindow(int WINDOW_WIDTH, int WINDOW_HEIGHT) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2 );
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2 );
        this.setTitle("Sokoban!");
        this.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }

    private void loadMenuBar() {
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

        JMenu mnOptions = new JMenu("Options");
        menuBar.add(mnOptions);

        mnLevel = new JMenu("Level");
        mnOptions.add(mnLevel);

        mntmLevel1 = new JMenuItem("Level 1");
        mnLevel.add(mntmLevel1);

        mntmLevel2 = new JMenuItem("Level 2");
        mnLevel.add(mntmLevel2);

        mntmLevel3 = new JMenuItem("Level 3");
        mnLevel.add(mntmLevel3);

        mntmLevel4 = new JMenuItem("Level 4");
        mnLevel.add(mntmLevel4);

        mntmLevel5 = new JMenuItem("Level 5");
        mnLevel.add(mntmLevel5);

        mntmMultiplayer = new JMenu("Multiplayer");
        mnOptions.add(mntmMultiplayer);

        mntmLocal = new JMenuItem("Local");
        mntmMultiplayer.add(mntmLocal);

        mntmSinglePlayerMode = new JMenuItem("Single Player Mode");
        mnOptions.add(mntmSinglePlayerMode);

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
    }

    private void loadImages() {
        try {
            crate = ImageIO.read(new File("images/crate_tile.gif"));
            completedCrate = ImageIO.read(new File("images/completed_crate.gif"));
            wall = ImageIO.read(new File("images/gray_tile.gif"));
            victory_tile = ImageIO.read(new File("images/victory_tile.gif"));
            userSprite = ImageIO.read(new File("images/Sprite.gif"));
            logo = ImageIO.read(new File("images/logo.png"));
            player2 = null;
        } catch (Exception e) {
            System.out.println("Images can't be found. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createGui() {
    	logoSign = new JPanel(); //top Panel
    	logoSign.setMaximumSize(new Dimension(1500, 50));
    	logoSign.setBackground(new Color(42,42,42));

        /** TOP PART OF GUI **/
    	topLogo = new Logo(logo, WINDOW_WIDTH, 100);
    	topLogo.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        logoSign.add(topLogo);
    	
        /** OPTIONS PANEL **/
        optionsPanel = new JPanel(); //bottom Panel
        optionsPanel.setMaximumSize(new Dimension(WINDOW_WIDTH, 18000));
        optionsPanel.setBackground(new Color(59, 59, 59));

        /** MOVES LABEL/# **/
        movesCount = new JLabel();
        movesCount.setForeground(Color.WHITE);
        movesCount.setText("0");

        /** MOVES Text **/
        moves = new JLabel(); //Text for moves
        moves.setForeground(Color.WHITE);
        moves.setText("Moves: ");
        moves.setBackground(new Color(59, 59, 59));

        leftButton = new JButton("<");
        leftButton.setPreferredSize(new Dimension(30,30));
        leftButton.setBackground(Color.white);
        leftButton.setForeground(Color.white);
        leftButton.setOpaque(true);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorder(null);

        rightButton = new JButton(">");
        rightButton.setPreferredSize(new Dimension(30,30));
        rightButton.setBackground(Color.white);
        rightButton.setForeground(Color.white);
        rightButton.setOpaque(true);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorder(null);

        JPanel movesPanel = new JPanel();
        movesPanel.setPreferredSize(new Dimension(10,-50));
        movesPanel.setBackground(new Color(59,59,59));

        JPanel timePanel = new JPanel();
        timePanel.setBackground(new Color(59,59,59));

        timeText = new JLabel();
        timeText.setForeground(Color.WHITE);
        timeText.setText("Time: ");
        timeText.setBackground(new Color(59, 59, 59));

        time = new JLabel();
        time.setForeground(Color.WHITE);
        time.setText("0 seconds");

        /** ADDING OBJECTS TO MOVES PANEL **/
        movesPanel.add(leftButton);
        movesPanel.add(moves);
        movesPanel.add(movesCount);
        movesPanel.add(rightButton);

        /** ADDING OBJECTS TO TIME PANEL **/
        timePanel.add(timeText);
        timePanel.add(time);

        /** ADDING OBJECTS TO OPTION PANEL **/
        optionsPanel.setBackground(new Color(42,42,42));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(movesPanel);
        optionsPanel.add(timePanel);

        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(100, 100));
        
        controller.model.locateVictoryTiles(boardContent);
        
        board = new Board(this, cellSize);
        board.setPreferredSize(new Dimension(700, 400));
        add(logoSign);
        add(contentPane);
        contentPane.add(board);
        contentPane.setBackground(new Color(59,59,59));

        add(optionsPanel);
        setFocusable(true);
    }

    public void gameWonDialog() { // once user wins game, then this dialog should show up
        controller.timer.stop();
        String wonGameMessage = "Congrats! You beat the game \nin " + controller.timerCounter +
                                "seconds! Would you \n like to move to the next level?";
        JOptionPane wonGame = new JOptionPane();
        int response = wonGame.showInternalConfirmDialog(this.getContentPane(), wonGameMessage, "You win!",
                JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if(response != 0) this.dispose(); // quit
    }
    
    public void openHighScores(){
    	String highScoresText = "";

        /** SWAP HIGH SCORES IN ASCENDING ORDER **/
    	for(int i = controller.scoreBoard.size()-1; i >=0 ; i--) {
    		for(int j = 0; j < i; j++) {
    			if(controller.scoreBoard.get(j).getScore() > controller.scoreBoard.get(j+1).getScore()){
    				int tempNum1 = controller.scoreBoard.get(j).getScore();
    				String tempText1 = controller.scoreBoard.get(j).getText();
    				
    				int tempNum2 = controller.scoreBoard.get(j+1).getScore();
    				String tempText2 = controller.scoreBoard.get(j+1).getText();

    				controller.scoreBoard.set(j, new MyHighScores(tempNum2, tempText2));
    				controller.scoreBoard.set(j+1, new MyHighScores(tempNum1, tempText1));
    			}
    		}
    	}

        /** WHAT THE USER WILL SEE IN THE HIGH SCORE POPUP **/
    	for(int i = 0; i < controller.scoreBoard.size(); i++){
    		highScoresText += controller.scoreBoard.get(i).getText() + "\n";
    	}

    	JOptionPane high = new JOptionPane();
        high.showInternalConfirmDialog(this.getContentPane(), highScoresText, "High Scores",
                JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
       
    }

    public void updateBoardContent(char[][] board) {
        this.boardContent = new char[board.length][board[0].length];

        // COPYING/UPDATING BOARD CONTENT
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                boardContent[i][j] = board[i][j];
            }
        }
    }
}