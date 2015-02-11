import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.image.BufferedImage;

public class Train{

 	private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
	
	int Xpos, Ypos;
 	private final int SHIFT = 64;	
   	final int height = 10;
   	final int width = 10;
    BufferedImage image;
    
    public Train(){
		this.Xpos = 0;
		this.Ypos = 512;
    }

    //get x pos of the cell
    public int getX(){ return Xpos; }
   
    //get y pos of the cell
    public int getY(){ return Ypos; }
	
    //set x pos of the cell
    public void setX(int x){ Xpos = x; }
	
    //set y pos of the cell
    public void setY(int y){ Ypos = y; }
    
    /**
     Class to handle keyboard events to move train on screen
     up, down, left, or right based on user input, maybe put this in game env
     */
    private class Keyboard extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e){
	    	int key = e.getKeyCode();

	   	 	if((key == KeyEvent.VK_LEFT) && (!right)){
				left = true;
				up = false;
				down = false;
	    	}
	   
		  	if((key == KeyEvent.VK_RIGHT) && (!left)){
				right = true;
				up = false;
				down = false;
	    	}	   

		    if((key == KeyEvent.VK_UP) && (!down)){
				up = true;
				right = false;
				left = false;
	    	}	
	    
	    	if((key == KeyEvent.VK_DOWN) && (!up)){
				down = true;
				right = false;
				left = false;
	    	}
 
		}
     }
    
     /**
       Method moveTrain moves the head of the train and makes
       all of the train parts follow
    */
  	private void move(){
  		Keyboard playerInput = new KeyBoard();
		if (left){  this.setX(this.getX() - SHIFT); }
		if (right){ this.setX(this.getX() + SHIFT); }
		if (up){ this.setY(this.getY() - SHIFT); }
		if (down){ this.setY(this.getY() + SHIFT); }
 	}

    //get object's current image
   /* public BufferedImage getImage(){
	return this.image;
    }
    
    //set object's current image
    public void setImage(String path, int col, int row){
	try{
	    this.image = ImageIO.read(new File(path));
	}catch(IOException e){
	    System.out.println("Image did read\n");
	}
	//waiting on sprite sheet to generate params
	this.image = this.image.getSubimage(col, row, width, height);
    }*/


}
