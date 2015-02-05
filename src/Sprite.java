import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;

abstract public class Sprite{

    /**
       Constructor
       @param xPos x coord of the grid location
       @param yPos y coord of the grid location
    */	
    
    //position of sprite on grid
	int Xpos;
	int Ypos;
	
    //set image
	
    //	buffered image get image
	
	//get x pos of the cell
	public abstract int getX();
	
	//get y pos of the cell
	public abstract int getY();
	
	//set x pos of the cell
	public abstract void setX(int x);
	
	//set y pos of the cell
	public abstract void setY(int y);

}
