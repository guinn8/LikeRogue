package actors;

public abstract class Actors {
	private static int moveRes = 1;
	private double deltaX = 0;
	private double deltaY = 0;
	
	private int xPos;
	private int yPos;
	private int health;
	private int damage;
	
	
	Actors(int setX, int setY, int setHealth, int setDamage){
		xPos=setX;
		yPos=setY;
		damage=setDamage;
		health=setHealth;
	}
	
	public static int getMoveRes() {
		return moveRes;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	
	public void setDeltaX(double setDeltaX) {
		deltaX=setDeltaX;
	}
	
	public double getDeltaY() {
		return deltaY;
	}
	
	public void setDeltaY(double setDeltaY) {
		deltaY=setDeltaY;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int setHealth) {
		health=setHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int setDamage) {
		damage=setDamage;
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public void setXpos(int setXpos) {
		xPos=setXpos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public void setYpos(int setYpos) {
		yPos=setYpos;
	}
	


}
