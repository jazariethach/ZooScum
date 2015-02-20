import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.image.BufferedImage;

public class Animal{
    int Xpos, Ypos; 
    private final int height = 10;
    private final int width = 10;
    private final int row = (int)Math.round(Math.random()*3); //for 3 animals tmp
    boolean dead = false;
    URL catURL = getClass().getResource("graphics/canvas.png");//cat
    URL stateURL = catURL;
  	Image stateIM = new ImageIcon(stateURL).getImage();
    
    public Animal(){}
    
    //get x pos of the cell
    public int getX(){ return Xpos; }
   
    //get y pos of the cell
    public int getY(){ return Ypos; }
    
    public void setXY(int x, int y){
    	Xpos = x;
    	Ypos = y;
    }
	
    //set x pos of the cell
    public void setX(int x){ Xpos = x; }
	
    //set y pos of the cell
    public void setY(int y){ Ypos = y; }

    public boolean isDead(){ return this.dead; }
	
	public void setDead(){ 
    	URL bloodURL =  getClass().getResource("graphics/blood.png");//blood
    	stateIM = new ImageIcon(bloodURL).getImage(); 
    	this.dead = true; 
    }
    
    public Image getIM() {
    	stateIM = new ImageIcon(stateURL).getImage();
    	return this.stateIM; 
    }
    
    public void setURL(){ 
    	URL bloodURL =  getClass().getResource("graphics/blood.png");//blood
    	stateURL = bloodURL; 
    }
    
}
