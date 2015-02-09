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
    private int Xpos, Ypos;
   	private BufferedImage image;
    
    private final int height = 10;
    private final int width = 10;
    private final int row = (int)Math.round(Math.random()*3); //for 3 animals tmp
    
    public Animal(){}
    
    //get x pos of the cell
    public int getX(){ return Xpos; }
   
    //get y pos of the cell
    public int getY(){ return Ypos; }
	
    //set x pos of the cell
    public void setX(int x){ Xpos = x; }
	
    //set y pos of the cell
    public void setY(int y){ Ypos = y; }
  /*  
    //get object's current image
    public BufferedImage getImage(){
	return this.image;
    }
    
    //set object's current image
    public void setImage(String path, int col){
	try{
	    this.image = ImageIO.read(new File(path));
	}catch(IOException e){
	    System.out.println("Image did read\n");
	}
	//waiting on sprite sheet to generate params
	this.image = this.image.getSubimage(col, this.row, width, height);
	
    }
*/
    
}
