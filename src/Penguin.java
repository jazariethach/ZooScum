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
    private boolean dead = false;
    public URL pengURL = getClass().getResource("graphics/penguin.png");//cat
    private Image pengIM = new ImageIcon(pengURL).getImage();
    Image stateIM = pengIM;
    int maxX = 1000;
    int maxY = 600;
    public Penguin(){
    	super();
    }
	/*
		Method getIM - returns image
	*/
	@Override
    public Image getIM() {
    	return this.stateIM; 
    }
    
    /*
    	Method setURL - set blood.png as current state image
    */
    @Override
    public void setURL(){ 
    	URL bloodURL = getClass().getResource("graphics/blood.png");//blood
    	this.stateIM = new ImageIcon(bloodURL).getImage();
    }
    
    /*
    	Method setDead - set the status of animal to be "dead"
    */
    @Override
    public void setDead(){ 
    	URL bloodURL =  getClass().getResource("graphics/blood.png");//blood
    	stateIM = new ImageIcon(bloodURL).getImage(); 
    	this.dead = true; 
    }
}
