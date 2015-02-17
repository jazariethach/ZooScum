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
    private final int SHIFT = 32;	
    private Timer timer;
    private final int DELAY = 140;
    int maxX = 1024;
   // int maxGridX = maxX/64;
    int maxY = 768;
    //int maxGridY = maxY/64;
    int numAnimals = 3;
    private Image trainIM;
    private Image animalIM;
    private boolean gameover = false;
    private int animalType;
    Train train = new Train();
    ArrayList<Animal> animalArray = new ArrayList<Animal>();
    
		
    /**
       Method addNewBoardAnimal adds Animal to ArrayList
       and gives it an initial position on the board
    */
    private void addNewBoardAnimal() {
		Animal a = new Animal();
		int Xpos = 64*(int)(Math.round((Math.random()+0.1) * 32));
		int Ypos = 64*(int)(Math.round((Math.random()+0.1) * 24));
		a.setX(Xpos);
		a.setY(Ypos);
		animalArray.add(a);
    }
    /**
       Method addNewTailAnimal adds Animal to ArrayList
       @param animal - an Animal object
    */
    private void addNewTailAnimal(Animal tailAnimal) {
		train.getTA().add(train.getTA().size(), tailAnimal);
    }

    /**
       Constructor gameEnv creates JFrame and JPanel for
       animals and train with animated JPanel
    */ 
		 
    public GameEnvironment() {
		for(int i = 0; i < numAnimals; i++) {
			addNewBoardAnimal();
		}
		train.getTA().add(new Animal());
		
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
				g.drawImage(trainIM, train.getX(), train.getY(), this);
				for (int i=0; i<train.getTA().size(); i++){
					train.getTA().get(i).setY(train.getY());
					g.drawImage(animalIM, train.getTA().get(i).getX(), train.getTA().get(i).getY(), this);
				}
				Toolkit.getDefaultToolkit().sync();
			}
		} // showIcons
		
		void gameLogic(){//void gameLogic(int pauseDelay) throws InterruptedException {
			if(!gameover) {
				// checks if train crosses paths with zoo and clears tailArray
				if(train.getX() == 0/*zoo grid X*/ || train.getY() == 0/*zoo grid Y*/) {
					train.getTA().clear();
				}
				
				// checks if train crosses paths with tail & stops game
				for(int i = 0; i < train.getTA().size(); i++) {
					if(train.getX() == train.getTA().get(i).getX() && train.getY() == train.getTA().get(i).getY()) {
						gameover = true;
					}
				}
				
				// checks if train crosses paths with animals
				for(int i = 0; i < animalArray.size(); i++) {
					if( train.getX() == animalArray.get(i).getX() && train.getY() == animalArray.get(i).getY() ) {
						addNewTailAnimal(animalArray.get(i));
						animalArray.remove(i);
					}
				}
				
			// checks if train goes beyond screen boundaries & stops gameif(train.getX() > maxGridX || train.getX() < 0 ||
			if(train.getX() > maxX || train.getX() < 0 || train.getY() > maxY || train.getY() < 0) {
					gameover = true;
				}
			}
			if(gameover == true) {
            	timer.stop();
        	}
		} // end gameLogic
		
// 		void move(){
// 			for (int i=0; i<train.getTA().size(); i++){
// 				if (i==0){
// 					train.getTA().get(i).setX(train.getX());
// 					System.out.println("" + train.getX());
// 					System.out.println("" + train.getY());
// 					train.getTA().get(i).setY(train.getY());
// 				
// 				}
// 				else{
// 					train.getTA().get(i).setX(train.getTA().get(i-1).getX());
// 					train.getTA().get(i).setY(train.getTA().get(i-1).getY());
// 				}
//         	}
// 		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameover == false) {
				gameLogic();
				train.move();
				
			}
			repaint();
		}
	 /**
       Class to handle keyboard events to move train on screen
       up, down, left, or right based on user input, maybe put this in game env
    */
		public class Keyboard extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				if((key == KeyEvent.VK_LEFT) && (!train.getRight())){
					train.setLeft(true);
					train.setUp(false);
					train.setDown(false);
				}
				if((key == KeyEvent.VK_RIGHT) && (!train.getLeft())){
					train.setRight(true);
					train.setUp(false);
					train.setDown(false);
				}
				if((key == KeyEvent.VK_UP) && (!train.getDown())){
					train.setUp(true);
					train.setRight(false);
					train.setLeft(false);
				}
				if((key == KeyEvent.VK_DOWN) && (!train.getUp())){
					train.setDown(true);
					train.setRight(false);
					train.setLeft(false);
				}
			} // keyPressed
		} // Keyboard
    } // end DrawingPanel

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
