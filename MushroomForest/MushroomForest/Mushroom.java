package MushroomForest;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Jedd Morgan
 *S
 */
public class Mushroom extends RenderInstance
{
	public static int displayListIndex;

	public Mushroom(Vector3f position)
	{
		super(displayListIndex, position);
	}
	
	@Override
	public void update(float animationScale) {
		switch(animationNumber) {
			case 0: {
				this.setScale(CircularScale(animationScale, this.position.getX() / 10));
				break;
			}
			case 1: {
				this.setScale(CircularScale(animationScale, (this.position.getX() + this.position.getZ())/10));
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param phaseScale - the scale of the animation ( how fast the animation plays )
	 * @param phaseOffset - the phase offset
	 * @return the new scale
	 */
	private static float CircularScale(float phaseScale, float phaseOffset) {
		
		return (float) Math.abs(Math.sin(Math.toDegrees(Sys.getTime()/50000f) + phaseOffset));
		
		
	}
	
	
}
