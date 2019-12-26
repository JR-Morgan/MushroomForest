package MushroomForest;

import org.eclipse.jdt.annotation.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;

/**
 * This class encapsulates a render instance.<br>
 * @author Jedd Morgan
 *
 */
public class RenderInstance implements Renderable
{
	protected static int animationNumber = 1;
	
	protected int displayListIndex;
	protected Vector3f position, rotation, scale;
	protected Colour colour;
	protected Texture texture;
	
	/**
	 * 
	 * @param displayListIndex - Index of the model that should be drawn when this {@link RenderInstance} is rendered ( {@link render} )
	 * @param position - The world position of this {@link RenderInstance}
	 */
	public RenderInstance(int displayListIndex, Vector3f position) {
		this(displayListIndex, 	//displayListIndex
			 position,		  	//position
			 new Vector3f(),   	//rotation
			 new Vector3f(1f,1f,1f), //scale
			 Colour.WHITE,     	//colour
			 null);				//texture
	}
	
	/**
	 * 
	 * @param displayListIndex - Index of the model that should be drawn when this {@link RenderInstance} is rendered ( {@link render} )
	 * @param position - The translation that should be applied on render
	 * @param rotation - The rotation that should be applied on render
	 * @param scale - 	The scale that should be applied on render
	 * @param colour -  The colour that should be applied
	 * @param texture - The texture that should be drawn (if null, no texture will be applied)
	 */
	public RenderInstance(int displayListIndex, Vector3f position, Vector3f rotation, Vector3f scale, Colour colour, @Nullable Texture texture) {
		this.displayListIndex = displayListIndex;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.colour = colour;
		this.texture = texture;
	}
	

	/**
	 * Update method is called every frame
	 */
	public void update(float animationScale) {
		
	}

	/**
	 * Called once a frame<br>
	 * <br>
	 * Calls the rendering of the displayList specified displayListIndex with the specified position, rotation, scale, colour and texture<br>
	 * If texture is null then no texture will be drawn.<br>
	 * <br>
	 * This method should be called between<br>
	 * {@link GL11.glPushMatrix} and {@link GL11.glPopMatrix} method calls          
	 */
	public void render() {	
        
        colour.submit();
        
        if(texture != null){
        	GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
        }
        
        // Applies translation, rotation and scale
        {
	        GL11.glTranslatef(position.getX(),
	        				  position.getY(),
	        				  position.getZ());
	        
	        GL11.glRotatef(rotation.getX(), 1, 0, 0);
			GL11.glRotatef(rotation.getY(), 0, 1, 0);
			GL11.glRotatef(rotation.getZ(), 0, 0, 1);
	        
	        GL11.glScalef(scale.getX(),
	        			  scale.getY(),
	        		      scale.getZ());
        }
        
        // Render the Object
        GL11.glCallList(displayListIndex);
        
        // Disable textures and reset any local lighting changes
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopAttrib();
    }

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}
	public void setScale(float scale)
	{
		this.scale = new Vector3f(scale,scale,scale);
	}

	public int getDisplayListIndex()
	{
		return displayListIndex;
	}

	public void setDisplayListIndex(int displayListIndex)
	{
		this.displayListIndex = displayListIndex;
	}

	public Colour getColour()
	{
		return colour;
	}

	public void setColour(Colour colour)
	{
		this.colour = colour;
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
}
