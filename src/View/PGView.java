package View;

import Controller.HighScores;
import Controller.MyKeyListener;
import Controller.PGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PGView extends JFrame {
    public PGController controller;

    /** MENUBAR **/
    public JFrame panel;
    public JPanel contentPane;
    public JMenuBar menuBar;
    public JMenuItem mntmHowToPlay;
    public JMenuItem mntmAbout;
    public JMenuItem mntmNewGame;
    public JMenuItem mntmHighScore;
    public JMenuItem mntmExit;
    public JMenu mnOption;
    public JMenuItem mntmLevel1;
    public JMenuItem mntmLevel2;
    public JMenuItem mntmLevel3;
    public JMenuItem mntmLevel4;
    public JMenuItem mntmLevel5;
    
    public JMenu mnHelp;

    /** WINDOW **/
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    
    /** PANEL **/
    private JPanel optionsPanel;
    private JPanel logoSign;
    private JLabel moves;
    public JLabel movesCount;

    /** IMAGES **/
    public BufferedImage crate;
    public BufferedImage completedCrate;
    public BufferedImage wall;
    public BufferedImage victory_tile;
    public BufferedImage userSprite;
    public BufferedImage logo;
    public BufferedImage leftArrow;
    public BufferedImage rightArrow;
    private int cellSize;

    /** OBJECTS **/
    public Board board;
    public Logo topLogo;

    /** BOARD IMPORTANT **/
    public char[][] boardContent;

    /** OPTIONS PANEL **/
    public JButton leftButton;
    public JButton rightButton;
    public JLabel timeText;
    public JLabel time;
    
    // Default constructor
    public PGView() {
        WINDOW_WIDTH = 1500;
        WINDOW_HEIGHT = 1250;
    }

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
        //this.getContentP
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

        mnOption = new JMenu("Level");
        menuBar.add(mnOption);

        mntmLevel1 = new JMenuItem("Level 1");
        mnOption.add(mntmLevel1);

        mntmLevel2 = new JMenuItem("Level 2");
        mnOption.add(mntmLevel2);

        mntmLevel3 = new JMenuItem("Level 3");
        mnOption.add(mntmLevel3);

        mntmLevel4 = new JMenuItem("Level 4");
        mnOption.add(mntmLevel4);

        mntmLevel5 = new JMenuItem("Level 5");
        mnOption.add(mntmLevel5);

        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        mntmHowToPlay = new JMenuItem("How to Play");
        mnHelp.add(mntmHowToPlay);

        mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);

        JMenuItem menuItem_1 = new JMenuItem("");
        menuBar.add(menuItem_1);
        

        
        JMenuItem menuItem = new JMenuItem("");
        //panel.setLayout(new BorderLayout());
        //panel.setPreferredSize(new Dimension(800, 850));
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
        } catch (IOException e) {
            System.out.println("Images can't be found. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createGui() {
    	logoSign = new JPanel(); //top Panel
    	logoSign.setMaximumSize(new Dimension(1091, 50));
    	logoSign.setBackground(new Color(59,59,59));

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
        movesPanel.add(moves);
        movesPanel.add(movesCount);

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

    public void gameWonDialog() {
        String wonGameMessage = "Congrats! You beat the game \nin " + controller.timerCounter +
                                "seconds! Would you \n like to move to the next game?";
        JOptionPane wonGame = new JOptionPane();
        int response = wonGame.showInternalConfirmDialog(this.getContentPane(), wonGameMessage, "You win!",
                JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if(response != 0) this.dispose(); // quit

    }
    
    public void openHighScores(){
    	String highScoresText = "";
    	
    	for(int i = controller.scoreBoard.size()-1; i >=0 ; i--) {
    		for(int j = 0; j < i; j++) {
    			if(controller.scoreBoard.get(j).getScore() > controller.scoreBoard.get(j+1).getScore()){
    				int tempNum1 = controller.scoreBoard.get(j).getScore();
    				String tempText1 = controller.scoreBoard.get(j).getText();
    				
    				int tempNum2 = controller.scoreBoard.get(j+1).getScore();
    				String tempText2 = controller.scoreBoard.get(j+1).getText();
    				
    				//HighScores temp = controller.scoreBoard.get(j).getScore(), controller.scoreBoard.get(j).getText();
    				controller.scoreBoard.set(j, new HighScores(tempNum2, tempText2));
    				controller.scoreBoard.set(j+1, new HighScores(tempNum1, tempText1));
    			}
    		}
    	}
    	 
    	for(int i = 0; i < controller.scoreBoard.size(); i++){
    		highScoresText += controller.scoreBoard.get(i).getText() + "\n";
    	}
    	JOptionPane high = new JOptionPane();
        int response = high.showInternalConfirmDialog(this.getContentPane(), highScoresText, "High Scores",
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