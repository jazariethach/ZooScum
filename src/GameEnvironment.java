import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.Timer;
import java.awt.Color;

/** 
    Creates a JFrame that animates various animals 
    and allows for a train to move around and collect 
    the animals to return to the zoo. Tracks number 
    of animals dropped off in a given amount of time.
 
    @author  Jennifer Cryan
    @author  Jessica Huang
    @author  Jazarie Thach
    @author  Felica Truong
    @author  Josephine Vo
    @version for CS48, Winter 2015, UCSB
*/

public class GameEnvironment extends JFrame {

    Thread animate;
    DrawingPanel gridPanel = new DrawingPanel();
    JFrame animationFrame = new JFrame();
	
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private final int SHIFT = 5;	
    private Timer timer;
    private final int DELAY = 140;
    int maxX = 1024;
    int maxGridX = maxX/64;
    int maxY = 768;
    int maxGridY = maxY/64;
    int numAnimals = 3;
    private Image trainIM;
    private Image animalIM;
    private boolean gameover = false;
    private int animalType;

    private int x = 10;
    private int y = 510;
	
    Train train = new Train();
    ArrayList<Animal> animalArray = new ArrayList<Animal>();
    ArrayList<Animal> tailArray = new ArrayList<Animal>();
		
    /**
       Method addNewBoardAnimal adds Animal to ArrayList
       and gives it an initial position on the board
    */
    private void addNewBoardAnimal() {
		Animal a = new Animal();
		a.setX((int)Math.round(Math.random() * 1024));
		a.setY((int)Math.round(Math.random() * 760));
		animalArray.add(a);
    }
    /**
       Method addNewTailAnimal adds Animal to ArrayList
       @param animal - an Animal object
    */
    private void addNewTailAnimal(Animal tailAnimal) {
		tailArray.add(tailAnimal);
    }

    /**
       Constructor gameEnv creates JFrame and JPanel for
       animals and train with animated JPanel
    */ 
		 
    public GameEnvironment() {
		for(int i = 0; i < numAnimals; i++) {
			addNewBoardAnimal();
		}

		animationFrame.getContentPane().add(BorderLayout.CENTER, gridPanel);
		animationFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);    
		animationFrame.setSize(maxX, maxY);
		animationFrame.setVisible(true);
		gridPanel.requestFocus(); 
		GameMenu game = new GameMenu();
		game.makeMenu();
		
	//open gameEnvr
	//setBackground
	//start Animation
    }
	
	
    /**
       Innerclass for JPanel animation to create background
       image and add animals and train to screen along with
       a timer and score.
    */
	
    class DrawingPanel extends JPanel implements ActionListener {
		public DrawingPanel(){
			addKeyListener(new Keyboard());
			icons();
			timer = new Timer(DELAY, this);
			timer.start();		
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			showIcons(g);
		} // end paintComponent
		
		public void icons(){
			URL trainURL = getClass().getResource("graphics/tc.jpg");
			trainIM = new ImageIcon(trainURL).getImage();
				
			URL animalURL = getClass().getResource("graphics/cat.png");
			animalIM = new ImageIcon(animalURL).getImage();
		} // icons
		public void showIcons(Graphics g){
			if (gameover == false){
				for (int i=0; i<3; i++){
					g.drawImage(animalIM, animalArray.get(i).getX(), animalArray.get(i).getY(), this);
				}
				g.drawImage(trainIM, x, y, this);
				Toolkit.getDefaultToolkit().sync();
			}
		} // showIcons
	  
		public void move(){
			if (left){ x = x - SHIFT; }
			if (right){ x = x + SHIFT; }
			if (up){ y = y - SHIFT; }
			if (down){ y = y + SHIFT; }
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameover == false) {
			move();
			}
			repaint();
		}
		public class Keyboard extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				if((key == KeyEvent.VK_LEFT) && (!right)){
					left=(true);
					up=(false);
					down=(false);
				}
	   
				if((key == KeyEvent.VK_RIGHT) && (!left)){
					right=(true);
					up=(false);
					down=(false);
				}	   

				if((key == KeyEvent.VK_UP) && (!down)){
					up = true;
					right = (false);
					left=(false);
				}	
		
				if((key == KeyEvent.VK_DOWN) && (!up)){
					down=(true);
					right=(false);
					left=(false);
				}
			} // keyPressed
		} // Keyboard
    } // end DrawingPanel
	  
    /**
       Class to handle keyboard events to move train on screen
       up, down, left, or right based on user input, maybe put this in game env
    */
	
	
    /**
       Class to display game menu with options to play game,
       read instructions, and exit the game
    */
    class GameMenu implements ActionListener {
		JButton Pause = new JButton("Pause");
			/* 
		   import buttons if desired
		   implement save if desired
		   JButton Save = new JButton "Save & Exit"*/
		
		JButton Exit = new JButton("Exit");
		
        // Start GUI for game environment with menu
        public void makeMenu() {
            Pause.addActionListener(this);
            // Save.addActionListener(this);
            Exit.addActionListener(this);
            
            JPanel gameButtons = new JPanel(new BorderLayout());
            animationFrame.getContentPane().add(BorderLayout.SOUTH, gameButtons);
            gameButtons.add(BorderLayout.EAST, Pause);
            // gameButtons.add(BorderLayout.CENTER, Save);
            gameButtons.add(BorderLayout.WEST, Exit);
            animationFrame.setVisible(true);
        }
        
        // Performs actions if buttons are pressed
    	@Override
		public void actionPerformed(ActionEvent buttonPress) {
			if(buttonPress.getSource() == Exit) {
				System.exit(0);
			}
		}
        
    }
	
}
