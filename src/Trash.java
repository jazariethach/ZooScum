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
 * Class Trash - creates a Trash class to track and move animal around screen
 * 
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
public class Trash{
    int Xpos, Ypos; 
    Image defaultIM;
    URL defaultURL = getClass().getResource("graphics/poo.png");
    
    int maxX = 1024;
    int maxY = 768;
  		
    public Trash(){}

    public Trash(int x, int y){
    	this.Xpos = x;
    	this.Ypos = y;
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
    
    public Image getIM() {
    	defaultIM = new ImageIcon(defaultURL).getImage();
    	return this.defaultIM; 
    }

}
