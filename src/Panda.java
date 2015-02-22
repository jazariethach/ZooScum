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

public class Panda extends Animal{
    private int movement = 2;
    private int health = 2;
    private int attack; // Override attack
    private URL rightURL = getClass().getResource("graphics/panda_right.png");
    private URL leftURL = getClass().getResource("graphics/panda_left.png");
    private URL upURL = getClass().getResource("graphics/panda_right.png");
    private URL downURL = getClass().getResource("graphics/panda_left.png");
    private URL redURL = getClass().getResource("graphics/blood.png");//blood
    private Image pandaIM = new ImageIcon(rightURL).getImage();
    Image stateIM = pandaIM;
    
    public Panda(){
    	super();
    }
        // Constructor for panda class, sets x and y position
    public Panda(int x, int y){
    	super(x, y);
    }
	
	/*
		Method getIM - returns image
	*/
	@Override
    public Image getIM() {
    	if (super.isDead()){
    		this.pandaIM = new ImageIcon(this.redURL).getImage(); 
    	}
    	else if (super.getLeft()){
    		this.pandaIM = new ImageIcon(this.leftURL).getImage();
    	}
    	else if (super.getUp()){
    		this.pandaIM = new ImageIcon(this.upURL).getImage();
    	}
    	else if (super.getDown()){
    		this.pandaIM = new ImageIcon(this.downURL).getImage();
    	}
    	else if (super.getRight()){
    		this.pandaIM = new ImageIcon(this.rightURL).getImage();
    	}
    	return this.pandaIM;
    }
    
    public void damage(int dmg){
    	this.health = this.health - dmg;
    	if (health >= 0){
    		this.setDead();
    	}
    }
}
