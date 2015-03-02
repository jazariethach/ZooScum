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

public class Koala extends Animal{
    private int movement = 2;
    private int health = 2;
    private int attack = 2;
    private URL rightURL = getClass().getResource("graphics/koala_right.png");
    private URL leftURL = getClass().getResource("graphics/koala_left.png");
    private URL upURL = getClass().getResource("graphics/koala_right.png");
    private URL downURL = getClass().getResource("graphics/koala_left.png");
    private URL redURL = getClass().getResource("graphics/blood.png");//blood
    private Image koalaIM = new ImageIcon(rightURL).getImage();
    Image stateIM = koalaIM;
    
    public Koala(){
    	super();
    }
     // Constructor for koala class, sets x and y position
    public Koala(int x, int y){
    	super(x, y, 3);
    }
	
		/*
		Method getIM - returns image
	*/
	@Override
    public Image getIM() {
    	if (super.isDead()){
    		this.koalaIM = new ImageIcon(this.redURL).getImage(); 
    	}
    	else if (super.getLeft()){
    		this.koalaIM = new ImageIcon(this.leftURL).getImage();
    	}
    	else if (super.getUp()){
    		this.koalaIM = new ImageIcon(this.upURL).getImage();
    	}
    	else if (super.getDown()){
    		this.koalaIM = new ImageIcon(this.downURL).getImage();
    	}
    	else if (super.getRight()){
    		this.koalaIM = new ImageIcon(this.rightURL).getImage();
    	}
    	return this.koalaIM;
    }
   /*
    	Method setURL - set blood.png as current state image
    */
    public void setURL(){ 
    	URL bloodURL = getClass().getResource("blood.png");//blood
    	this.stateIM = new ImageIcon(bloodURL).getImage();
    }


}