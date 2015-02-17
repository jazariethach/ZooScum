package src;

import java.awt.image.BufferedImage;

/**
 * Class Train - creates a Train class to track and move train around screen
 * 
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
public class Animal extends Sprite {
	final int 	height 	= 10;	// height of train icon ?
    final int 	width 	= 10;	// width of train icon ?
    
	private int Xpos;			// x-coordinate position
	private int Ypos;			// y-coordinate position
	
    //private final int row = (int)Math.round(Math.random()*3); 
	
	 /**
     * Constructor Animal - empty default constructor
     */
    public Animal(){}

    /**
     * Override getX - returns horizontal position of train
     * 		 @return - Xpos integer coordinate
     */
	@Override
	public int getX() {
		return Xpos;
	}

	/**
     * Override getY - returns vertical position of train
     * 		 @return - Ypos integer coordinate
     */
	@Override
	public int getY() {
		return Ypos;
	}

	/**
     * Override setX - sets x-coordinate position
     */
	@Override
	public void setX(int x) {
		Xpos = x;
	}

	/**
     * Override setY - sets y-coordinate position
     */
	@Override
	public void setY(int y) {
		Ypos = y;
	}
	
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
