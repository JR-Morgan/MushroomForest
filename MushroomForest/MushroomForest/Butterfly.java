package MushroomForest;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector3f;

import GraphicsLab.Colour;

public class Butterfly extends RenderInstance
{
	public static int rightWingDisplayIndex;
	public static int leftWingDisplayIndex;

	public Butterfly(Vector3f position, Vector3f rotation, Vector3f rotationOrigin)
	{
		super(0, 							//displayListIndex
			  position,   					//position
			  rotation,       				//rotation
			  rotationOrigin,   			//rotation origin
			  new Vector3f(1f,1f,1f),       //scale
			  Colour.WHITE,     	        //colour
			  true,							//disable lighting
			  null,                 		//texture   
			  new RenderInstance[] {new RenderInstance(leftWingDisplayIndex, new Vector3f()),              		    
					  				new RenderInstance(rightWingDisplayIndex, new Vector3f())}); //children
		
		for(RenderInstance c : this.children)
			c.disableLighting = true;
	}
	
	
	@Override
	public void update(float animationScale) { 
		Vector3f.add(rotation, new Vector3f(0f, (float) (0.01f * Timer.getDeltaTime()), 0), rotation);
		this.children[0].setRotation(new Vector3f(0f,0f,circularRotation(animationScale + 1,0) * 25));
		this.children[1].setRotation(new Vector3f(0f,0f,circularRotation(animationScale + 1,90)* 25));
	}
	
	
	/**
	 * 
	 * @param phaseScale - the scale of the animation ( how fast the animation plays )
	 * @param phaseOffset - the phase offset
	 * @return the new scale
	 */
	private static float circularRotation(float phaseScale, float phaseOffset) {
		
		return (float) Math.abs(Math.sin(Math.toDegrees(Sys.getTime()/5000f) + phaseOffset));
	}
		
	
}
