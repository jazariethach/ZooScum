import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;


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
    
    int maxX = 1024;
    int maxGridX = maxX/64;
    int maxY = 768;
    int maxGridY = maxY/64;
    int trainX = maxGridX/2;     // initial train x position
    int trainY = 0;              // initial train y position
    
    int collected = 0;
    int numAnimals = 3;
    int score = 0;
    
    int timer;
    long time1 = System.nanoTime()/1000000000; // start time
    long time2;
    long pauseStart;
    long pauseLength;
    //int pauseDelay = 20;
    boolean stopGame = false; 
    private boolean gameover = false;
    private int animalType;

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
     Constructor gameEnv ??creates JFrame and JPanel for
     animals and train with animated JPanel??
     */
    public GameEnvironment() {
        for(int i = 0; i < numAnimals; i++) {
            addNewBoardAnimal();
        }
        animationFrame.getContentPane().add(BorderLayout.CENTER, gridPanel);
		animate = new Animate();
		animate.start();
		animationFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);    
		animationFrame.setSize(maxX, maxY);
		animationFrame.setVisible(true);
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
    
    class DrawingPanel extends JPanel {
	
		@Override
        public void paintComponent(Graphics g) {
        	int score = 0; //no way of updating this currently since it's only in drawingpanel, need to make getScore
            // sets background color and image
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            super.paintComponent(g);
            /**
             code for adding background image / zoo image / sprites
             
             BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
             JLabel picLabel = new JLabel(new ImageIcon(myPicture));
             add(picLabel);
             */
            
            // Starts ActionListener to listen for keyboard events in panel
        	Keyboard keyListener = new Keyboard();
           	gridPanel.addKeyListener(keyListener);
           	
           	//Images in the game
           	//File file = new File("tc.jpg");
			//String path = file.getAbsolutePath();
			//System.out.println(path);

	    	URL trainURL = getClass().getResource("graphics/tc.jpg");
        	Image trainIM = new ImageIcon(trainURL).getImage();
	    
	    	//Draws the image of the train
	    	train.move();
	    	int newXPos = train.getX();
	    	int newYPos = train.getY();
	    	g2.drawImage(trainIM, newXPos, newYPos, this);
           	
            // Displays current time & score
            g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            String displayScore = "Score: " + score;
            g.drawString(displayScore, 0, 35);
            String elapsed = "Time elapsed: " + timer;
            g.drawString(elapsed, 0, 65);
            
            // Stops game at end of time, or if train crashes @@TODO
            if(timer >= 60) {
                stopGame = true;
                String endStr = "Time's up, Game Over!";
                g.drawString(endStr, 500, 350);
            }
        } // end paintComponent
    } // end DrawingPanel
    
    /** 
     Innerclass for Animate thread to display game animation
     of train and animals and checks for when to stop game
     */
    class Animate extends Thread {
        public void play() {
            try {
            	train.move();
                while(true) {
                    gameLogic();//gameLogic(pauseDelay);
                }
            }
            catch(Exception e) {
                if (e instanceof InterruptedException) {}
                else {
                    System.out.println(e);
                    System.exit(1); // terminate program
                }
            }
        } // end play
        
        void gameLogic() throws InterruptedException {//void gameLogic(int pauseDelay) throws InterruptedException {
            if(!stopGame) {
                // checks if train crosses paths with animals
                for(int i = 0; i < animalArray.size(); i++) {
                    if(train.getX() == animalArray.get(i).getX() &&
                       train.getY() == animalArray.get(i).getY()) {
                        tailArray.add(animalArray.get(i));
                        animalArray.remove(i);
                    }
                }
                
                // checks if train crosses paths with zoo and clears tailArray
                if(train.getX() == 0/*zoo grid X*/ || train.getY() == 0/*zoo grid Y*/) {
                    tailArray.clear();
                }
                
                // checks if train crosses paths with tail & stops game
                for(int i = 0; i < animalArray.size(); i++) {
                    if(train.getX() == tailArray.get(i).getX() &&
                       train.getY() == tailArray.get(i).getY()) {
                        stopGame = true;
                    }
                }
                
                // checks if train goes beyond screen boundaries & stops game
                if(train.getX() > maxGridX || train.getX() < 0 ||
                   train.getY() > maxGridY || train.getY() < 0) {
                    stopGame = true;
                }
                
/*                if(!stopGame) {
                    time2 = System.nanoTime() / 1000000000 - pausetime;
                    timer = (int)(time2 - time1); /* + previous load time 
                }*/
                
                gridPanel.repaint();
                if(Thread.currentThread().interrupted()) {
                    throw(new InterruptedException());
                }
                Thread.currentThread().sleep(20);
            }
        } // end gameLogic
    } // end Animate

	/**
     Class to handle keyboard events to move train on screen
     up, down, left, or right based on user input, maybe put this in game env
     */
    private class Keyboard extends KeyAdapter {
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
 
		}
     }
	
    /**
     Class to display game menu with options to play game,
     read instructions, and exit the game
     */
    class GameMenu implements ActionListener {
        JButton Pause = new JButton("Pause");
        /* 
         import buttons if desired
         implement save if desired
         JButton Save = new JButton "Save & Exit"
         */
        JButton Exit = new JButton("Exit");
        
        // Start GUI for game environment with menu
        public void makeMenu() {
            Pause.addActionListener(this);
            // Save.addActionListener(this);
            Exit.addActionListener(this);
            
            JPanel gameButtons = new JPanel(new BorderLayout());
            animationFrame.getContentPane().add(BorderLayout.SOUTH, gameButtons);
            gameButtons.add(BorderLayout.EAST, Exit);
            // gameButtons.add(BorderLayout.CENTER, Save);
            gameButtons.add(BorderLayout.WEST, Exit);
            animationFrame.setVisible(true);
        }
        
        // Performs actions if buttons are pressed
        public void actionPerformed(ActionEvent buttonPress) {
            
            /*// Stops program if Pause is pressed
            if(buttonPress.getSource() == Pause) {
                if(stopGame == false) {
                    stopGame = true;
                    pauseStart = System.nanoTime() / 1000000000;
                }
                else {
                    stop = false;
                    pausetime += (System.nanoTime() / 1000000000 - pauseStart);
                }
            }*/
            
            // Terminates program if Exit is pressed
            if(buttonPress.getSource() == Exit) {
                System.exit(0);
            }
        
        }
        
    }
    
}