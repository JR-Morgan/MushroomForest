package MushroomForest.Models;

import GraphicsLab.Vertex;

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
			new int[][]
			{
				{4,3,2,1}
			}
		);
    }
}
