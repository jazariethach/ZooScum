package src;

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
import javax.swing.JButton;

/**
 * Class GameEnvironment - creates a JFrame environment to animate zoo animals
 * 						   and allows for a train to move around and collect
 * 						   the animals to return to the zoo. Tracks number
 * 						   of animals dropped off in a given amount of time.
 *
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
public class GameEnvironment extends JFrame {
    private final int 	DELAY = 140;	// time delay for animation thread
    private final int	SHIFT = 15;		// for speed of train movement
    
    GamePanel 			gamePanel;		// main panel for game animation
    JFrame 				gameFrame;		// main frame for game panels
    JPanel				buttonsPanel;	// panel for in-game buttons
    JButton				Pause;			// button to pause game
    JButton				Exit;			// button to exit game
    private Timer       timer;          // timer to run animation
    private long 		startTime;		// marks starting time
    private long 		stopTime;		// marks ending time
    private long 		pausedTime;		// stores amount of time paused
    private long 		pauseStartTime;	// marks start of pause time
    private long 		elapsedTime;	// elapsed playing time
    private int 		maxX;			// max horizontal window bounds
    private int 		maxY;			// max vertical window bounds
    private int			maxX2;
    private int			maxY2;
    private int 		numAnimals;		// starting number of escaped animals
    private int			numTypes;		// number of different animals
    private int 		numTrash;		// starting number of trash
    private int 		nNet;			// number of nets available
    private int         nBroom;         // number of brooms available
    private int 		pWidth;			// width for boundary checking of panel
    private int 		pHeight;		// height for boundary checking of panel
    private int 		zWidth;			// width for boundary checking of zoo
    private int 		zHeight;		// height for boundary checking of zoo
    private int 		zooX;			// coordinates of zoo image
    private int 		zooY;           // coordinates of zoo image
    private int 		score;			// counts number of points earned
    private int 		total;			// counts number of points earned
    private int			level;
    private boolean 	gameover;		// check for when game ends
    private boolean 	pause;          // check for when game is paused
    private boolean     aBroom;         // check for brooms available
    private boolean		aNet;			
    private Image       backgroundIM;   // image icon for background image
    private Image       trainIM;        // image icon for train image
    private Image       animalIM;       // image icon for animal image
    private Image       zooIM;          // image icon for zoo image
    private Image       pooIM;          // image icon for poo image
    private Image       trashIM;        // image icon for broon image
    private Image		netIM;
    Broom               broom;          // broom object for trash pickup
    Train 				train;			// train object for animal pickup
    Net					net;
    ArrayList<Animal> 	animalArray;	// array of animals on screen
    ArrayList<Trash>   trashArray;     // array for trash on screen
    {
        gamePanel 		= new GamePanel();
        gameFrame 		= new JFrame();
        buttonsPanel 	= new JPanel(new BorderLayout());
        Pause 			= new JButton("Pause");
        Exit 			= new JButton("Exit");
        startTime 		= System.nanoTime() / 1000000000;
        stopTime		= 0;
        pausedTime		= 0;
        pauseStartTime  = 0;
        elapsedTime		= 0;
        maxX 			= 900;
        maxY 			= 680;
        maxX2			= maxX-100;
        maxY2			= maxY-60;
        numAnimals 		= 3;
        numTrash        = 3;
        numTypes		= 6;
        nBroom          = 10;
        nNet			= 100;
        pWidth          = 40;
        pHeight         = 40;
        zWidth          = 75;
        zHeight         = 75;
        zooX            = 150;
        zooY            = 100;
        score 			= 0;
        total           = 0;
        level			= 1;
        gameover 		= false;
        aBroom          = false;
        broom           = new Broom();
        train 			= new Train();
        net				= new Net();
        animalArray 	= new ArrayList<Animal>();
        trashArray      = new ArrayList<Trash>();
    }
    
    
    /**
     * Method addNewTrash - adds Trash to trashArray and gives it an
     * 						initial position on the board
     */
    private void addNewTrash() {
        int Xpos = SHIFT*(int)Math.ceil(Math.random() * (maxX2/SHIFT));
        int Ypos = SHIFT*(int)Math.ceil(Math.random() * (maxY2/SHIFT));
        while((zooX - zWidth/2) < Xpos && (Xpos < (zooX + zWidth/2))
              && (Ypos > zooY - zHeight/2) && (Ypos < zooY + zHeight/2)){
            Xpos = SHIFT*(int)Math.ceil(Math.random() * (maxX2/SHIFT));
            Ypos = SHIFT*(int)Math.ceil(Math.random() * (maxY2/SHIFT));
        }
        Trash t = new Trash(Xpos, Ypos);
        trashArray.add(t);
    }
    /**
     * Method addNewBoardAnimal - adds Animal to animalArray and gives it an
     * 						 	  initial position on the board
     */
    private void addNewBoardAnimal() {
    	if (level < 6){
    		numTypes = level;
    	}
    	else{
    		numTypes = 6;
    	}
    	
        int randomize = (int)(Math.random() * numTypes+1); // determines animal type

		int Xpos = SHIFT*(int)Math.ceil(Math.random() * maxX2/SHIFT);
		int Ypos = SHIFT*(int)Math.ceil(Math.random() * maxY2/SHIFT);
        while((zooX - zWidth/2) < Xpos && (Xpos < (zooX + zWidth/2))
              && (Ypos > zooY - zHeight/2) && (Ypos < zooY + zHeight/2)){
            if(Xpos > maxX2 -35){
               Xpos = (SHIFT*(int)Math.ceil(Math.random() * maxX2/SHIFT))%(maxX -35);
             }
            if(Ypos > maxY2 -80)
                Ypos = (SHIFT*(int)Math.ceil(Math.random() * maxY2/SHIFT)) % (maxY -80);
        }
        if (randomize == 1){
            Penguin p = new Penguin(Xpos, Ypos);
            animalArray.add(p);
        }
		if (randomize == 2){
			Giraffe g = new Giraffe(Xpos, Ypos);
			animalArray.add(g);
		}
		if (randomize == 3){
			Koala k = new Koala(Xpos, Ypos);
			animalArray.add(k);
		}
		if (randomize == 4){
			Panda pa = new Panda(Xpos, Ypos);
			animalArray.add(pa);
		}
		if (randomize == 5){
			Fox f = new Fox(Xpos, Ypos);
			animalArray.add(f);
		}
		if (randomize == 6){
			Tiger t = new Tiger(Xpos, Ypos);
			animalArray.add(t);
		}
    }


    /**
     Method addNewTailAnimal adds Animal to ArrayList
     @param animal - an Animal object
     */
    private void addNewTailAnimal(Animal tailAnimal) {
        train.getTA().add(tailAnimal);
    }
    
    /**
     * Constructor GameEnvironment - creates JFrame and JPanel for game
     * 								 animation environment with a menu
     */
    public GameEnvironment() {
        for(int i = 0; i < numAnimals; i++) {
            addNewBoardAnimal();
        }
        for(int i = 0; i < numTrash; i++) {
            addNewTrash();
        }
        gameFrame.setContentPane(gamePanel);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setMinimumSize(new Dimension(maxX, maxY));
        gameFrame.setSize(maxX, maxY);
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gamePanel.requestFocus();
    }
    
    /**
     * Innerclass GamePanel - for JPanel animation, creates background image
     * 						  and adds zoo, animals, and train to screen along
     * 						  with a timer and score output
     */
    class GamePanel extends JPanel implements ActionListener {
        public GamePanel(){
            // TODO check size of panel ish
            setPreferredSize(new Dimension(maxX, maxY));
            setMaximumSize(new Dimension(maxX, maxY));
            addKeyListener(new KeyHandler());
            icons();
            timer = new Timer(DELAY, this);
            timer.start();
            startTime = System.nanoTime() / 1000000000;
            pack();
            setVisible(true);
        }// GamePanel
        
        /**
         * Method getTime - calculates elapsed time of game
         * @return elapsedTime - number of seconds elapsed
         */
        private long getTime(){
            stopTime = System.nanoTime() / 1000000000;
            elapsedTime = (stopTime - startTime) - pausedTime;
            return elapsedTime;
        }// getTime
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            showIcons(g);
            showScore(g);
        } // end paintComponent
        
        /**
         * Method showScore - paint text onto screen for brooms and score
         * 		  @param g	- Graphics object to draw text on screen
         */
        public void showScore(Graphics g){
            // Displays current time & score
            g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 25));
            g.setColor(Color.BLACK);
            String displayScore = "Score: " + score;
            g.drawString(displayScore, maxX - 150, 35);
            String displayBroom = "Brooms: " + nBroom;
            g.drawString(displayBroom, maxX - 150, 60);
            String displayHealth = "Health: " + train.getHealth();
            g.drawString(displayHealth, maxX - 150, 85);
            String displayNet = "Nets: " + nNet;
            g.drawString(displayNet, maxX - 150, 110);
            String displayLevel = "Level " + level;
            g.drawString(displayLevel, maxX/2 - 20, 50);
            if (level < 3){
				String displayIns = "Press SPACE to activate broom!";
				g.drawString(displayIns, 300, maxY - 50);
				String displayEsc = "Press C to activate net!";
				g.drawString(displayEsc, 300, maxY - 25);
			}
        }
        
        /**
         * Method icons - load icon images from file
         */
        public void icons(){
            URL backgroundURL = getClass().getResource("graphics/background.jpg");//background
            backgroundIM = new ImageIcon(backgroundURL).getImage();

            URL trainURL = getClass().getResource("graphics/train_right.png");//train
            trainIM = new ImageIcon(trainURL).getImage();
            
            URL zooURL = getClass().getResource("graphics/zoo.png");//zoo
            zooIM = new ImageIcon(zooURL).getImage();
            
            URL pooURL = getClass().getResource("graphics/poo.jpg");//zoo
            pooIM = new ImageIcon(pooURL).getImage();
            
            URL broomURL = getClass().getResource("graphics/broom.png");//zoo
            trashIM = new ImageIcon(broomURL).getImage();
            
            URL netURL = getClass().getResource("graphics/net.png");//zoo
            netIM = new ImageIcon(netURL).getImage();
            
        } // icons
        
        /**
         * Method showIcons - paint image icons onto screen for animation
         * 		  @param g	- Graphics object to draw images on screen
         */
        public void showIcons(Graphics g){
            if (gameover == false){
                
                g.drawImage(backgroundIM, -maxX/2, -maxY/2, this);

                for (int i=0; i<numAnimals; i++){
                    g.drawImage(animalArray.get(i).getIM(), animalArray.get(i).getX(), animalArray.get(i).getY(), this);
                }
                for (int i=0; i<numTrash; i++){
                    g.drawImage(pooIM, trashArray.get(i).getX(), trashArray.get(i).getY(), this);
                }
                g.drawImage(trainIM, train.getX(), train.getY(), this);
                g.drawImage(zooIM, zooX, zooY,this);
                //train.getTA().add(new Animal());
                for (int i=0; i<train.getTA().size(); i++){
                    if (train.getLeft()){ // change to tailArray.get(i).getIM(); both left and right
                        g.drawImage(train.getTA().get(i).getIM(), train.getTA().get(i).getX(), train.getTA().get(i).getY(), this);
                    }
                    else{
                        g.drawImage(train.getTA().get(i).getIM(), train.getTA().get(i).getX(), train.getTA().get(i).getY(), this);
                    }
                }
                if (aBroom == true){
                    g.drawImage(trashIM, broom.getX(), broom.getY(), this);
                }
                if (aNet == true){
                    g.drawImage(netIM, net.getX(), net.getY(), this);
                }
                Toolkit.getDefaultToolkit().sync();
            }
        } // showIcons
        
        /**
         * Method gameLogic - determines what to do as train moves around screen
         */
        void gameLogic(){
            if(gameover == false && pause == false) {
                // checks if train crosses paths with zoo and clears tailArray
                if ((zooX - zWidth/2) < train.getX() && (train.getX() < (zooX + zWidth/2))
                    && (train.getY() > zooY - zHeight/2) && (train.getY() < zooY + zHeight/2)){
                    int n = train.getTA().size();
                    train.getTA().clear();
                    total += n;
                    score += n;
                    // if all animals are collected
                    if (numAnimals == total){
                        pause = true;
                        nextLevel();
                        train.incShift();
                        train.incHealth();
						net.incShift();
						broom.incShift();
						level ++;
                        animalArray.clear();
                        trashArray.clear();
                        total = 0;
                        numAnimals+=2;
                        numTrash+=1;
                        for(int i = 0; i < numAnimals; i++) {
                            addNewBoardAnimal();
                        }
                        for(int i = 0; i < numTrash; i++) {
                            addNewTrash();
                        }
                    }
                }
                
                // checks if train crosses paths with tail & stops game
                for(int i = 0; i < train.getTA().size(); i++) {
                    if(train.getX() == train.getTA().get(i).getX() && train.getY() == train.getTA().get(i).getY()) {
                        gameover = true;
                    }
                }
                
                // checks if train crosses paths with animals
                for(int i = 0; i < animalArray.size(); i++) {
                    if ((animalArray.get(i).getX() - pWidth/2) < train.getX() && (train.getX() < (animalArray.get(i).getX() + pWidth/2))
                        && (train.getY() > animalArray.get(i).getY() - pHeight/2) && (train.getY() < animalArray.get(i).getY() + pHeight/2)){
                        if (!animalArray.get(i).isDead()){
                            animalArray.get(i).setX(2000);
                            animalArray.get(i).setY(2000);
                            score++;
                            int n = train.getTA().size();
                            switch(animalArray.get(i).get_animalNum()){
                                case 1:
                                    addNewTailAnimal(new Panda());
                                    break;
                                case 2:
                                    addNewTailAnimal(new Penguin());
                                    break;
                                case 3:
                                    addNewTailAnimal(new Koala());
                                    break;
                                case 4:
                                    addNewTailAnimal(new Giraffe());
                                    break;
                                case 5:
									addNewTailAnimal(new Fox());
									break;
								case 6:
									addNewTailAnimal(new Tiger());
									break;
                            }
                            if (n==0){
                                train.getTA().get(n).setX(train.getX());
                                train.getTA().get(n).setY(train.getY());
                            }
                            else{
                                train.getTA().get(n).setX(train.getTA().get(n-1).getX());
                                train.getTA().get(n).setY(train.getTA().get(n-1).getY());
                            }
                        }
                    } // endif
                }
                
                // check if net crosses path with animal - clear animal
                for(int i = 0; i < animalArray.size(); i++) {
                    if ((animalArray.get(i).getX() - pWidth/2) < net.getX() && (net.getX() < (animalArray.get(i).getX() + pWidth/2))
                        && (net.getY() > animalArray.get(i).getY() - pHeight/2) && (net.getY() < animalArray.get(i).getY() + pHeight/2)){
                        if (!animalArray.get(i).isDead()){
                            animalArray.get(i).setX(2000);
                            animalArray.get(i).setY(2000);
                            score++;
                            int n = train.getTA().size();
                            switch(animalArray.get(i).get_animalNum()){
                                case 1:
                                    addNewTailAnimal(new Panda());
                                    break;
                                case 2:
                                    addNewTailAnimal(new Penguin());
                                    break;
                                case 3:
                                    addNewTailAnimal(new Koala());
                                    break;
                                case 4:
                                    addNewTailAnimal(new Giraffe());
                                    break;
                                case 5:
									addNewTailAnimal(new Fox());
									break;
								case 6:
									addNewTailAnimal(new Tiger());
									break;
                            }
                            if (n==0){
                                train.getTA().get(n).setX(train.getX());
                                train.getTA().get(n).setY(train.getY());
                            }
                            else{
                                train.getTA().get(n).setX(train.getTA().get(n-1).getX());
                                train.getTA().get(n).setY(train.getTA().get(n-1).getY());
                            }
                        }
                    }
                }
                
                // check if broom crosses path with poo - clear poo
                for(int i = 0; i < trashArray.size(); i++) {
                    if ((trashArray.get(i).getX() - pWidth/2) < broom.getX() && (broom.getX() < (trashArray.get(i).getX() + pWidth/2))
                        && (broom.getY() > trashArray.get(i).getY() - pHeight/2) && (broom.getY() < trashArray.get(i).getY() + pHeight/2)){
                        trashArray.get(i).setX(2000);
                        trashArray.get(i).setY(2000);
                        score++;
                    }
                }
                
                // checks if train crosses paths with poo
                for(int i = 0; i < trashArray.size(); i++) {
                    if ((trashArray.get(i).getX() - pWidth/2) < train.getX() && (train.getX() < (trashArray.get(i).getX() + pWidth/2))
                        && (train.getY() > trashArray.get(i).getY() - pHeight/2) && (train.getY() < trashArray.get(i).getY() + pHeight/2)){
                        train.decHealth();
                        if (train.getHealth() == 0){
                        	gameover = true;
                        }
                    } // endif
                }
                
                // checks if train goes beyond screen boundaries & stops gameif(train.getX() > maxGridX || train.getX() < 0 ||
                if(train.getX() > (maxX) || train.getX() < 0 || train.getY() > (maxY) || train.getY() < 0) {
                    gameover = true;
                }
            }
            if(gameover == true) {
                timer.stop();
                gameOver();
            }
        } // end gameLogic
        
        private void gameOver() {
            String[] choices = {"Exit", "New Game", "Main Menu"};
            ImageIcon pooIcon = new ImageIcon(getClass().getResource("graphics/poo.jpg"));
            
            int choice = JOptionPane.showOptionDialog(null,
                                                      "Gameover! Play again?",
                                                      "GAMEOVER",
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE,
                                                      pooIcon,
                                                      choices,
                                                      choices[1]);
            switch(choice) {
                case 0 :
                    System.exit(0);
                case 1 :
                    gameFrame.dispose();
                    GameEnvironment gameEnv = new GameEnvironment();
                    break;
                case 2 :
                    gameFrame.dispose();
                    MainEnvironment mainEnv = new MainEnvironment();
                    break;
            }
        }

        private void pauseGame() {
            String[] choices = {"Exit", "Resume Game", "Main Menu"};
            ImageIcon pauseIcon = new ImageIcon(getClass().getResource("graphics/paws.png"));
            
            
            int choice = JOptionPane.showOptionDialog(null,
                                                      "Continue playing?",
                                                      "GAME PAUSED",
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE,
                                                      pauseIcon,
                                                      choices,
                                                      choices[1]);
            switch(choice) {
                case 0 :
                    gameover = true;
                    timer.stop();
                    System.exit(0);
                case 1 :
                    pausedTime += (System.nanoTime() / 1000000000 - pauseStartTime);
                    pause = false;
                    break;
                case 2 :
                    gameover = true;
                    timer.stop();
                    gameFrame.dispose();
                    MainEnvironment mainEnv = new MainEnvironment();
                    break;
            }
        }

        private void nextLevel() {
            String[] choices = {"Exit", "Next Level", "New Game", "Main Menu"};
            ImageIcon nextLevelIcon = new ImageIcon(getClass().getResource("graphics/cagedElephant.png"));

            int choice = JOptionPane.showOptionDialog(null,
                                                      "Yay you wrangled all those wild animals!\nBut there's more!",
                                                      "CONGRATULATIONS",
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE,
                                                      nextLevelIcon,
                                                      choices,
                                                      choices[1]);
            switch(choice) {
                case 0 :
                    gameover = true;
                    timer.stop();
                    System.exit(0);
                case 1 :
                    pausedTime += (System.nanoTime() / 1000000000 - pauseStartTime);
                    pause = false;
                    break;
                case 2 :
                    gameover = true;
                    timer.stop();
                    gameFrame.dispose();
                    GameEnvironment gameEnv = new GameEnvironment();
                    break;
                case 3 :
                    gameover = true;
                    timer.stop();
                    gameFrame.dispose();
                    MainEnvironment mainEnv = new MainEnvironment();
                    break;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameover == false && pause == false) {
                gameLogic();
                train.move();
                for (int i=0; i<numAnimals; i++){
                    animalArray.get(i).step();
                }
                if (aBroom == true && nBroom >= 0)
                    broom.move();
                if (aNet == true && nNet >= 0)
                    net.move();
            }
            repaint();
        }
        /**
         Class to handle keyboard events to move train on screen
         up, down, left, or right based on user input, maybe put this in game env
         */
        public class KeyHandler extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(pause == false && gameover == false){
                    if((key == KeyEvent.VK_LEFT) && (!train.getRight())){
                        URL trainURL = getClass().getResource("graphics/train_left.png");
                        trainIM = new ImageIcon(trainURL).getImage();
                        train.setLeft();
                    }
                    if((key == KeyEvent.VK_RIGHT) && (!train.getLeft())){
                        URL trainURL = getClass().getResource("graphics/train_right.png");
                        trainIM = new ImageIcon(trainURL).getImage();
                        train.setRight();
                    }
                    if((key == KeyEvent.VK_UP) && (!train.getDown())){
                        URL trainURL = getClass().getResource("graphics/train_up.png");
                        trainIM = new ImageIcon(trainURL).getImage();
                        train.setUp();
                    }
                    if((key == KeyEvent.VK_DOWN) && (!train.getUp())){
                        URL trainURL = getClass().getResource("graphics/train_down.png");
                        trainIM = new ImageIcon(trainURL).getImage();
                        train.setDown();
                    }
                    if (key == KeyEvent.VK_SPACE && nBroom > 0){
                        broom.setXY(train.getX(), train.getY());
                        aBroom = true;
                        nBroom--;
                        broom.setDirection(train);
                    }
                    if (key == KeyEvent.VK_C && nNet > 0){
                        net.setXY(train.getX(), train.getY());
                        aNet = true;
                        nNet--;
                        net.setDirection(train);
                    }
                
                    if (key == KeyEvent.VK_ESCAPE && pause == false){
                        pause = true;
                        pauseGame();
                    }
                }
                if (key == KeyEvent.VK_ESCAPE && pause == true) {
                    pause = false;
                }
            } // keyPressed
        } // KeyHandler
    } // end GamePanel    
    
    // Initialize program to display main menu on screen
    public static void main(String [] args) {
       	GameEnvironment gameEnv = new GameEnvironment();
    }// main
}
