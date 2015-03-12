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

public class Penguin extends Animal{
    private int movement = 0;
    private int health = 2;
    private URL rightURL = getClass().getResource("graphics/penguin_right.png");
    private URL leftURL = getClass().getResource("graphics/penguin_left.png");
    private URL upURL = getClass().getResource("graphics/penguin_up.png");
    private URL downURL = getClass().getResource("graphics/penguin_down.png");
    private URL redURL = getClass().getResource("graphics/blood.png");//blood
    private Image pengIM = new ImageIcon(rightURL).getImage();
    
    // Empty constructor
    public Penguin(){
    	super();
    }
    // Constructor for penguin class, sets x and y position
    public Penguin(int x, int y){
    	super(x, y, 2);
    }
    
	/*
		Method getIM - returns image
	*/
	@Override
    public Image getIM() {
    	if (super.isDead()){
    		this.pengIM = new ImageIcon(this.redURL).getImage(); 
    	}
    	else if (super.getLeft()){
    		this.pengIM = new ImageIcon(this.leftURL).getImage();
    	}
    	else if (super.getUp()){
    		this.pengIM = new ImageIcon(this.upURL).getImage();
    	}
    	else if (super.getDown()){
    		this.pengIM = new ImageIcon(this.downURL).getImage();
    	}
    	else if (super.getRight()){
    		this.pengIM = new ImageIcon(this.rightURL).getImage();
    	}
    	return this.pengIM;
    }
}
