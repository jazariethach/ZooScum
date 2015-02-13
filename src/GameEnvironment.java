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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

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
 
		private Image trainIM;
		private boolean gameover = false;
		private int animalType;

		private int x = 10;
		private int y = 510;
	
		Train train = new Train();
		/**
		 Constructor gameEnv creates JFrame and JPanel for
		 animals and train with animated JPanel
		 */ 
		public GameEnvironment() {
	
			// for(int i = 0; i < numAnimals; i++) {
	//             addNewBoardAnimal();
	//         }
		
	
			animationFrame.getContentPane().add(BorderLayout.CENTER, gridPanel);
			animationFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);    
			animationFrame.setSize(maxX, maxY);
			animationFrame.setVisible(true);
			gridPanel.requestFocus(); 
		//	GameMenu game = new GameMenu();
		//	game.makeMenu();
		
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
				doDrawing(g);
			} // end paintComponent
		
			public void icons(){
				URL trainURL = getClass().getResource("graphics/tc.jpg");
				trainIM = new ImageIcon(trainURL).getImage();
			}
			public void doDrawing(Graphics g){
				if (gameover == false){
					g.drawImage(trainIM, x, y, this);
					 Toolkit.getDefaultToolkit().sync();
				}
			}
	  
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
			}
				
			
		}
	} // end DrawingPanel
	  
		/**
		 Class to handle keyboard events to move train on screen
		 up, down, left, or right based on user input, maybe put this in game env
		 */
	
	
		/**
		 Class to display game menu with options to play game,
		 read instructions, and exit the game
		 */
	//     class GameMenu implements ActionListener {
	//         JButton Pause = new JButton("Pause");
	//         /* 
	//          import buttons if desired
	//          implement save if desired
	//          JButton Save = new JButton "Save & Exit"*/
	//         
	//         JButton Exit = new JButton("Exit");
	//         
	//         // Start GUI for game environment with menu
	//         public void makeMenu() {
	//             Pause.addActionListener(this);
	//             // Save.addActionListener(this);
	//             Exit.addActionListener(this);
	//             
	//             JPanel gameButtons = new JPanel(new BorderLayout());
	//             animationFrame.getContentPane().add(BorderLayout.SOUTH, gameButtons);
	//             gameButtons.add(BorderLayout.EAST, Pause);
	//             // gameButtons.add(BorderLayout.CENTER, Save);
	//             gameButtons.add(BorderLayout.WEST, Exit);
	//             animationFrame.setVisible(true);
	//         }
	//         
	//         // Performs actions if buttons are pressed
	//     	@Override
	//    		public void actionPerformed(ActionEvent e) {
	//     	}
	//         
	//     }
	
	}