public Test{

    private final int SHIFT = 64;


    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean gameover = false;
    private int animalType;
    ArrayList<Animal> tail =  new ArrayList<Animal>();
    ArrayList<Animal> animal = new ArrayList<Animal>();
    /*
    //inside constructor of GameEnv
    addKeyListener(new keyboard());

    */

    private void generateAnimal(){
        
	Animal a = new Animal();
	a.setX(Math.random() % 1024);
	a.setY(Math.random() % 760);
	animal.add(a);
    }
    
    

    private void moveTrain(){
	
       
	for ( int i = tail.size()-1; i>0; z--){
	    tail.get(i).setX(tail.get(i-1).getX());
	    tail.get(i).setY(tail.get(i-1).getY());
	}
	
	tail.get(0).setX(train.getX());
	tail.get(0).setY(train.getY());

	if (left){
	    train.setX(train.getX() - SHIFT);
	}

	if (right){
	    train.setX(train.getX() + SHIFT);
	}

	if (up){
	    train.setY(train.getY() - SHIFT);
	}

	if (down){
	    train.setY(train.getY() + SHIFT);
	}
       	
    }

    private class keyboard extends KeyAdapter {

	@Overrode
	public void keyPressed(KeyEvent e){
	    int key = e.getKeyCode();

	    if((key == KeyEvent.VK_LEFT) && (!right)){
		left = true;
		up = false;
		down = false;
	    }
	   
	    if((key == KeyEvent.VK_RIGHT) && (!left)){
		right = true;
		up = false;
		down = false;
	    }	   

	    if((key == KeyEvent.VK_UP) && (!down)){
		up = true;
		right = false;
		left = false;
	    }	
	    
	    if((key == KeyEvent.VK_DOWN) && (!up)){
		down = true;
		right = false;
		left = false;
	    }
 
	}



    }



}
