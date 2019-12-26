package MushroomForest;

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
				this.setScale((float) Math.abs(Math.sin((Timer.getTotalTime() / animationScale / 1000000000l) + this.position.getX()/10)));
				break;
			}
			case 1: {
				this.setScale((float) Math.abs(Math.sin((Timer.getTotalTime() / animationScale / 1000000000l) + (this.position.getX() + this.position.getZ())/10)));
				break;
			}
			case 2: {
				this.position.setX((float) (this.position.getX() + Math.sin(((Timer.getTotalTime() / animationScale / 100000000l) + this.position.getX()))/10));
				this.position.setZ((float) (this.position.getZ() + Math.cos(((Timer.getTotalTime() / animationScale / 100000000l) + this.position.getZ()))/10));
				break;
			}
		}
		
	}
	
	
	
}
