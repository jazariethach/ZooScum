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
    private int movement = 5;
    private URL rightURL = getClass().getResource("graphics/panda_right.png");
    private URL leftURL = getClass().getResource("graphics/panda_left.png");
    private URL upURL = getClass().getResource("graphics/panda_up.png");
    private URL downURL = getClass().getResource("graphics/panda_down.png");
    private URL redURL = getClass().getResource("graphics/blood.png");//blood
    private Image pandaIM = new ImageIcon(rightURL).getImage();
    Image stateIM = pandaIM;
    
    public Panda(){
    	super();
    }
        // Constructor for panda class, sets x and y position
    public Panda(int x, int y){
    	super(x, y, 1);
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
    
    @Override
  	public void step(){
  		int random = (int)Math.ceil(Math.random()*4);
		if ((this.Ypos + movement) < (maxY-80) && random == 1){ // move up
					this.Ypos += movement;
		}
		else if ((this.Ypos - movement) > 0 && random == 2){ // move down
					this.Ypos -= movement;
		}
		else if ((this.Xpos + movement) < (maxX-35) && random == 3){ // move right
					this.Xpos += movement;
		}
		else if ((this.Xpos - movement) > 0 && random == 4){ // move left
					this.Xpos -= movement;
		}
  	}

}
