package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.image.BufferedImage;

public class Giraffe extends Animal{
    private int Xpos, Ypos; 
    private int movement = 10;
    private URL rightURL = getClass().getResource("graphics/giraffe_right.png");
    private URL leftURL = getClass().getResource("graphics/giraffe_left.png");
    private URL upURL = getClass().getResource("graphics/giraffe_up.png");
    private URL downURL = getClass().getResource("graphics/giraffe_down.png");
    private URL redURL = getClass().getResource("graphics/blood.png");//blood
    private Image raffeIM = new ImageIcon(rightURL).getImage();
    Image stateIM = raffeIM;
    
    public Giraffe(){
    	super();
    }
     // Constructor for giraffe class, sets x and y position
    public Giraffe(int x, int y){
    	super(x, y, 4);
    }
	
	// Method getIM() - returns image
	@Override
    public Image getIM() {
    	if (super.isDead()){
    		this.raffeIM = new ImageIcon(this.redURL).getImage(); 
    	}
    	else if (super.getLeft()){
    		this.raffeIM = new ImageIcon(this.leftURL).getImage();
    	}
    	else if (super.getUp()){
    		this.raffeIM = new ImageIcon(this.upURL).getImage();
    	}
    	else if (super.getDown()){
    		this.raffeIM = new ImageIcon(this.downURL).getImage();
    	}
    	else if (super.getRight()){
    		this.raffeIM = new ImageIcon(this.rightURL).getImage();
    	}
    	return this.raffeIM;
    }
    
  	@Override
  	public void step(){
  		int random = (int)Math.ceil(Math.random()*2);
		if ((this.Ypos + movement) < (maxY-80) && random == 1){ // move up
					this.Ypos += movement;
		}
  	}

}