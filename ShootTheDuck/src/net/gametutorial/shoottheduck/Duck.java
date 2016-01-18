package net.gametutorial.shoottheduck;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Duck {
	
	// This are starting data.
	public static final float initSpeed = 5;
	public static final long initTimeBetweenDucks = 1800; // in milliseconds
	
	// This is current speed that will be increased and current time that will be decreased.
	public static float speed;
	public static long timeBetweenDucks; // in milliseconds
	
	public static long timeOfLastDuck;
	
	// Ducks will be coming from left and right, we will use this to know which direction is next.
	public static boolean direction = true;
	
	// Needed for speeding up the game
	public static long timeBetweenSpeedups = 250; // in milliseconds
	public static long timeOfLastSpeedup;
	
	
	// Duck position on the screen.
	public float x;
	public float y;
	
	// Speed and direction.
	private float velocity;
	
	
	public Duck(int y){
		this.y = y;
		
		if(Duck.direction){
			this.x = Game.screenWidth;
			velocity = speed * -1;
		} else {
			this.x = 0 - Game.duckImage.getWidth();
			velocity = speed;
		}
		
		// We change direction for a next duck.
		Duck.direction = !Duck.direction;
	}
	
	
	/**
	 * Move the duck.
	 */
	public void update(){
		this.x += velocity;
	}
	
	/**
	 * Draw the duck to a screen.
	 * 
	 * @param canvas Canvas to draw on.
	 */
	public void draw(Canvas canvas){
		if(velocity < 0)
			canvas.drawBitmap(Game.duckImage, x, y, null);
		else
			canvas.drawBitmap(Game.duckRightImage, x, y, null);
	}
	
	
	/**
	 * Checks if the duck was touched/shoot.
	 * 
	 * @param touchX X coordinate of the touch.
	 * @param touchY Y coordinate of the touch.
	 * 
	 * @return True if touch coordinates are in the coordinates of duck rectangle, false otherwise.
	 */
	public boolean wasItShoot(int touchX, int touchY){
		Rect duckRect = new Rect((int)this.x, (int)this.y, (int)this.x + Game.duckImage.getWidth(), (int)this.y + Game.duckImage.getHeight());
		
		return duckRect.contains(touchX, touchY);
	}

}
