package src;

import java.util.ArrayList;

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
public class Train extends Sprite {
	private final int 	SHIFT 	= 24;		// number of pixels to move train
    final int 			height 	= 10;		// height of train icon ?
    final int 			width 	= 10;		// width of train icon ?
    
	private boolean 			left;		// check if train is moving left
    private boolean 			right;		// check if train is moving right
    private boolean 			up;			// check if train is moving up
    private boolean 			down;		// check if train is moving down
    private ArrayList<Animal> 	tailArray;	// array of animals on train
    private int 				Xpos;		// x-coordinate position
    private int 				Ypos;    	// y-coordinate position
    {
    left 		= false;
    right 		= true;
    up 			= false;
    down 		= false;
    tailArray 	= new ArrayList<Animal>();
    }
    
    /**
     * Constructor Train - initializes train on screen
     */
    public Train() {
		this.Xpos = 0;
		this.Ypos = 512;
    }
    
    /**
     * Method getTA - returns animal array of captured animals
     * 		@return - tailArray of Animal objects
     */
    public ArrayList<Animal> getTA() {
    	return tailArray; 
    }
    
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

	/**
     * Method setLeft - sets value of left check
     */
	public void setLeft(boolean x) { 
		left = x; 
	}
	
	/**
     * Method setRight - sets value of right check
     */
    public void setRight(boolean x) { 
    	right = x; 
	}
    
    /**
     * Method setUp - sets value of up check
     */
    public void setUp(boolean x) { 
    	up = x; 
	}
    
    /**
     * Method setDown - sets value of down check
     */
    public void setDown(boolean x) { 
    	down = x; 
	}
	
    /**
     * Method getLeft - gets value of left check
     * 		  @return - left bool check
     */
    public boolean getLeft() { 
    	return left; 
	}  
    
    /**
     * Method getRightt - gets value of right check
     * 		  @return - right bool check
     */
    public boolean getRight() { 
    	return right; 
	}
    
    /**
     * Method getUp - gets value of up check
     * 		  @return - up bool check
     */
    public boolean getUp() { 
    	return up; 
	}
    
    /**
     * Method getDown - gets value of down check
     * 		  @return - down bool check
     */
    public boolean getDown() { 
    	return down; 
	}
    
    /**
     *  Method Move - moves the head of the train and tail follows behind
     */
    public void Move() {
    	for (int i = 0; i < this.getTA().size(); i++){
    		if (i == 0) {
    			this.getTA().get(i).setX(this.getX());
    			System.out.println("" + this.getX());
				System.out.println("" + this.getY());
    			this.getTA().get(i).setY(this.getY());
    			
    		}
    		else{
				this.getTA().get(i).setX(this.getTA().get(i - 1).getX());
				this.getTA().get(i).setY(this.getTA().get(i - 1).getY());
			}
        }
    	if (left) { 
    		this.setX(this.getX() - SHIFT); 
    	}
    	if (right) { 
    		this.setX(this.getX() + SHIFT); 
    	}
		if (up) { 
			this.setY(this.getY() - SHIFT); 
		}
		if (down) { 
			this.setY(this.getY() + SHIFT); 
		}
    }// Move

}
