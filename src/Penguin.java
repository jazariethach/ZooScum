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
    private int Xpos, Ypos; 
    private final int height = 10;
    private final int width = 10;
    private int movement = 2;
    private int health = 2;
    private boolean dead = false;
    public URL pengURL = getClass().getResource("graphics/penguin.png");//cat
    private Image pengIM = new ImageIcon(pengURL).getImage();
    Image stateIM = pengIM;
    int maxX = 1000;
    int maxY = 600;
    public Penguin(){
    	super();
    }
	
	@Override
    public Image getIM() {
    	return this.stateIM; 
    }
    
    @Override
    public void setURL(){ 
    	URL bloodURL = getClass().getResource("graphics/blood.png");//blood
    	this.stateIM = new ImageIcon(bloodURL).getImage();
    }
    
    @Override
    public void setDead(){ 
    	URL bloodURL =  getClass().getResource("graphics/blood.png");//blood
    	stateIM = new ImageIcon(bloodURL).getImage(); 
    	this.dead = true; 
    }
    
    public void damage(int dmg){
    	this.health = this.health - dmg;
    	if (health >= 0){
    		this.setDead();
    	}
    }
    
    public void step(){
    	int random = (int)(Math.random()*4);
    	switch (random){
    		case 0: // move up
    			if ((this.Ypos + movement) < maxY){
    				this.Ypos += movement;
    			}
    		case 1: // move down
    			if ((this.Ypos - movement) > 0){
    				this.Ypos -= movement;
    			}
    		case 2: // move right
    			if ((this.Xpos + movement) < maxX){
    				this.Xpos += movement;
    			}
    		case 4: // move left
    			if ((this.Xpos - movement) > 0){
    				this.Xpos -= movement;
    			}
    	}
    }
}
