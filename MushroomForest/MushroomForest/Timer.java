package MushroomForest;

import org.lwjgl.Sys;

/**
 * Timer class keeps track of the last frametime.<br>
 * This class can be used to statically get delta time.
 * @author Jedd Morgan
 *
 */
public class Timer
{
	private static long lastFrame;
	private static long currentFrame;
	private static float animationScale;
	/**
	 * Updates the timer<br>
	 * This should be called every frame in the main update loop.
	 */
	public static void update() {
		lastFrame = currentFrame;
		currentFrame = Sys.getTime();
		
	}
	
	/**
	 * Calculates the time elapsed between the last frame and the current frame
	 * @return {@code currentFrame - lastFrame}
	 */
	public static double getDeltaTime() {
		return currentFrame - lastFrame;
	}
	
	/**
	 * Sets the animation scale for the scene
	 * @param value - the new value
	 */
	public static void setAnimationScale(float value) {
		animationScale = value;
	}
	
	public static float getAnimationScale() {
		return animationScale;
	}
}
