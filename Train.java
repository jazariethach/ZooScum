public class Train{
    private int Xpos, Ypos;
    private BufferedImage image;
    
    private final int height = 10;
    private final int width = 10;
    
    public Train(){
	this.Xpos = 0;
	this.Ypos = 512;
    }
    


    //get x pos of the cell
    public int getx(){ return Xpos; }
   
    //get y pos of the cell
    public int gety(){ return Ypos; }
	
    //set x pos of the cell
    public void setx(int x){ Xpos = x; }
	
    //set y pos of the cell
    public void sety(int y){ Ypos = y; }

    //get object's current image
    public BufferedImage getImage(){
	return this.image;
    }
    
    //set object's current image
    public void setImage(String path, int col, int row){
	try{
	    this.image = ImageIO.read(new File(path));
	}catch(IOException e){
	    System.out.println("Image did read\n");
	}
	//waiting on sprite sheet to generate params
	this.image = this.image.getSubimage(col, row, width, height);
    }


}
