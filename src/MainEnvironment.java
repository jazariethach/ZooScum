package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class MainEnvironment - creates a JFrame environment to display a menu to
 * 						the user to play game, read instructions, or exit
 * 
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
class MainEnvironment extends JFrame implements ActionListener {
	int			maxX;				// max horizontal window bounds
	int			maxY;				// max vertical window bounds
    JFrame 		menuFrame;			// main frame for main menu options
    JLayeredPane menuPane;			// layered pane for different options
    JPanel  	menuPanel;			// panel to display menu screen
    JPanel		buttonsPanel;		// panel to display buttons
    JPanel 		instructionsPanel;	// panel to display instructions
    JPanel		backPanel;			// panel to display buttons to go back
    JEditorPane instructionsPane; 	// pane to display text instructions
    JButton 	Play;				// button to play game
    JButton 	Instructions;		// button to read instructions
    JButton 	Exit;				// button to exit program
    JButton 	Back;				// button to return to main menu
    File		instructionsFile;	// file to hold instructions text
    Image 		menuImg;			// image icon for background image
    {
    	maxX				= 900;
    	maxY				= 680;
    	menuFrame 			= new JFrame("Main Menu");
    	menuPane			= new JLayeredPane();
    	menuPanel			= new MenuPanel();
    	buttonsPanel 		= new JPanel();
    	instructionsPanel 	= new JPanel();
    	backPanel			= new JPanel();
    	Play 				= new JButton("Play Game");
    	Instructions 		= new JButton("How to Play");
    	Exit 				= new JButton("Exit");
    	Back				= new JButton("Back to Menu");
    	instructionsPane	= new JEditorPane();
    }
    
    // Initialize program to display main menu on screen
    public static void main(String [] args) {
       	MainEnvironment mainMenu = new MainEnvironment();
    }// main
    
    
    /**
     * Method DisplayMenu - sets up frame, pane and panels to display menu options
     */
    public MainEnvironment() {
        
        // init buttons sizes, listeners, backgrounds
        Play.setPreferredSize(new Dimension(100, 50));
        Play.addActionListener(this);
		Play.setBackground(Color.GREEN);	
        Instructions.setPreferredSize(new Dimension(100, 50));
        Instructions.addActionListener(this);
		Instructions.setBackground(Color.GREEN);
        Exit.setPreferredSize(new Dimension(100, 50));  
        Exit.addActionListener(this);
		Exit.setBackground(Color.GREEN);		
		Back.setPreferredSize(new Dimension(100, 50));
        Back.addActionListener(this);
		Back.setBackground(Color.GREEN);
		Back.setOpaque(true);

		// add buttons to panel
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setPreferredSize(new Dimension(400, 400));
        buttonsPanel.setMinimumSize(new Dimension(400, 400));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(Box.createRigidArea(new Dimension(200,0)));
		buttonsPanel.add(Play);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20,0)));
        buttonsPanel.add(Instructions);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20,0)));
        buttonsPanel.add(Exit);
		buttonsPanel.setVisible(true);
        
		// initialize instructions panel
        instructionsPanel.setPreferredSize(new Dimension(500, 500));
        instructionsPanel.setMinimumSize(new Dimension(400, 400));
        instructionsPanel.setOpaque(true);
		backPanel.setLayout(new FlowLayout());
        backPanel.setPreferredSize(new Dimension(400, 60));
		backPanel.setBackground(Color.GREEN);
        backPanel.setOpaque(true);
        backPanel.add(Back);
		// instructionsPane.setPreferredSize(new Dimension(400, 400));
        //instructionsPane.setBackground(Color.GREEN);
        JTextArea textArea = new JTextArea("Welcome to Zoo Wrangler!" + "\n All the animals have just escaped from the zoo!! Your job is to go hop on the train and wrangle in all the wild animals while you still can." + "\n Use the arrow keys (left, right, down, up) to guide the train over the animals to retrieve them and they will then be added on to the back of your train like a tail. Once the animals are collected, drive through the zoo to return them." + "\n You can collect as many animals at a time as you like at a time before dropping them off back at the zoo. Returning many animals to the zoo at one time may earn you a bonus. However, as your tail of animals grows longer be careful not to run over your own tail of animals or into any walls, or game over." + "\n All animals must be returned to the zoo (not just in the train) in the time allotted or points will not be rewarded, and you cannot move on to the next level. Extra time left over will result in bonus points." + "\n Happy wrangling!!");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	
        // add pane & button to instructions panel
		textArea.setVisible(true);
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.add(Box.createRigidArea(new Dimension(200,0)));
        instructionsPanel.add(textArea);
        instructionsPanel.add(backPanel);
        instructionsPanel.setVisible(false);
        
     // add panel components to layered frame
        menuPane.setLayout(new LayeredLayout(menuPane));
        menuPane.setPreferredSize(new Dimension(500, 500));
        menuPane.setBackground(Color.GREEN);
        menuPane.add(buttonsPanel, JLayeredPane.DEFAULT_LAYER, 0);
        menuPane.add(instructionsPanel, JLayeredPane.POPUP_LAYER, 0);
        menuPane.setVisible(true);
        
        menuPanel.setPreferredSize(new Dimension(maxX, maxY));
        menuPanel.add(menuPane, BorderLayout.CENTER);
        menuPanel.setVisible(true);
        
     // add layered pane to frame
        menuFrame.setFocusable(true);
        menuFrame.setContentPane(menuPanel);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setMinimumSize(new Dimension(maxX, maxY));
        menuFrame.setSize(maxX, maxY);
        menuFrame.pack();
        menuFrame.setResizable(false);
        menuFrame.setVisible(true);
        
    }// DisplayMenu
    
    
    /**
     * Method MenuPanel - draws background image on menu panel
     */
	class MenuPanel extends JPanel {
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
            setBackgroundImg(g);
		      
	    }// paintComponent
        
        public void getBackgroundImg() {
            URL menuBackgroundURL = getClass().getResource("graphics/madagascar.jpg");
            
            try {
                menuImg = new ImageIcon(menuBackgroundURL).getImage();
            }
            catch(NullPointerException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
        
        public void setBackgroundImg(Graphics g) {
            getBackgroundImg();
            g.drawImage(menuImg, 0, 0, getWidth(), getHeight(), null);
        }
	}// MenuPanel (JPanel)
	
	/**
	 * Override actionPerformed - sets actions when menu buttons are pressed
	 */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == Play) {
            menuFrame.setVisible(false);
            GameEnvironment gameEnv = new GameEnvironment();
        }
        if(event.getSource() == Instructions) {
        	menuPane.moveToBack(buttonsPanel);
        	instructionsPane.setVisible(true);
        	instructionsPanel.setVisible(true);
        	menuPane.moveToFront(instructionsPanel);
        }
        if(event.getSource() == Exit) {
        	dispose();
            System.exit(0);
        }
        if(event.getSource() == Back) {
        	menuPane.moveToBack(instructionsPanel);
        	instructionsPanel.setVisible(false);
        	menuPane.moveToFront(buttonsPanel);
        	buttonsPanel.setVisible(true);
        }
    }// actionPerformed  
}// MainEnvironment

    
