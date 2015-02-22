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

/**
 * Class Animal - creates a Animal class to track and move animal around screen
 * 
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
public class Animal{
    int Xpos, Ypos; 
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean dead = false;
    Image defaultIM;
    URL defaultURL = getClass().getResource("graphics/poo.png");
    private int movement = 15;
    private int animalNum;
    int maxX = 1024;
    int maxY = 768;
  		
    public Animal(){}

    public Animal(int x, int y, int aN){
    	this.Xpos = x;
    	this.Ypos = y;
        this.animalNum = aN;
    }

    public int get_animalNum(){
        return animalNum;
    }

	public int getX() {
		return Xpos;
	}

	public int getY() {
		return Ypos;
	}

	public void setX(int x) {
		Xpos = x;
	}

	public void setY(int y) {
		Ypos = y;
	}

    public void setXY(int x, int y){ // Set Xpos to x and Ypos to y
    	Xpos = x;
    	Ypos = y;
    }
    
    public void setLeft() {   // Set position of animal to left
    	this.left = true; 
    	this.right = false;
    	this.up = false;
    	this.down = false;
    }
    
    public void setRight() { // Set position of animal to right
    	this.left = false; 
    	this.right = true;
    	this.up = false;
    	this.down = false;
    }    
    
    public void setUp() { // Set position of animal to up
    	this.left = false; 
    	this.right = false;
    	this.up = true;
    	this.down = false;
    }

    public void setDown() { // Set position of animal to down
		this.left = false; 
    	this.right = false;
    	this.up = false;
    	this.down = true;
    }
	
	// check the direction of Animal
    public boolean getLeft() { return left; }  
    public boolean getRight() { return right; }
    public boolean getUp() { return up; }
    public boolean getDown() { return down; }
	
    public boolean isDead(){ return this.dead; }
	
	public void setDead(){ 
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false; 
    	this.dead = true; 
    }
    
    public Image getIM() {
    	defaultIM = new ImageIcon(defaultURL).getImage();
    	return this.defaultIM; 
    }
    
    public void step(){
		int random = (int)(Math.random()*4);
		if ((this.Ypos + movement) < maxY && random == 1){
					this.Ypos += movement;
		}
		else if ((this.Ypos - movement) > 0 && random == 2){
					this.Ypos -= movement;
		}
		else if ((this.Xpos + movement) < maxX && random == 3){
					this.Xpos += movement;
		}
		else if ((this.Xpos - movement) > 0 && random == 4){
					this.Xpos -= movement;
		}
    }
}
