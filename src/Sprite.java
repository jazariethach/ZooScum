package src;

/**
 * Abstract Sprite - skeleton framework for animated sprite objects (Animal, Train)
 * 
 * @author	Jennifer Cryan
 * @author 	Jessica Huang
 * @author 	Jazarie Thach
 * @author 	Felica Truong
 * @author 	Josephine Vo
 * @version for CS48, Winter 2015, UCSB
 */
public abstract class Sprite {
	int Xpos;	// x-coordinate position
	int Ypos;	// y-coordinate position
	
	
	/**
	 * Abstract getX - retrieves x-coordinate value
	 * 		@return  - Xpos at current location of sprite
	 */
	public abstract int getX();
	
	/**
	 * Abstract getY - retrieves y-coordinate value
	 * 		@return  - Ypos at current location of sprite
	 */
	public abstract int getY();
	
	/**
	 * Abstract setX - sets x-coordinate value
	 */	
	public abstract void setX(int x);
	
	/**
	 * Abstract setY - sets y-coordinate value
	 */	
	public abstract void setY(int y);

}
				