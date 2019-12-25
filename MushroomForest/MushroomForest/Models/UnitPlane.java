package MushroomForest.Models;

import org.lwjgl.util.vector.Vector2f;

import GraphicsLab.Vertex;
import MushroomForest.Utill.Face;

public class UnitPlane extends Model
{
	private static Model instance;
	
	public static Model getInstance() 
    { 
        if (instance == null) 
        	instance = new UnitPlane(); 
  
        return instance; 
    } 
	
	private UnitPlane()
    {
		super(
			new Vertex[]
			{
		        new Vertex(-0.5f, 0.0f,-0.5f),
		        new Vertex( 0.5f, 0.0f,-0.5f),
		        new Vertex( 0.5f, 0.0f, 0.5f),
		        new Vertex(-0.5f, 0.0f, 0.5f) 	
			},
			new Vector2f[] {
				new Vector2f(0.0f,0.0f),
				new Vector2f(1.0f,0.0f),
				new Vector2f(1.0f,1.0f),
				new Vector2f(0.0f,1.0f)
			},
			new Face[]
			{
				new Face(new int[] {4,3,2,1}, new int[] {1,2,3,4})
			}
		);
    }
}
