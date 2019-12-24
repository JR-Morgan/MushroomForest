package MushroomForest.Models;

import GraphicsLab.Vertex;

public class UnitCube extends Model
{
	private static Model instance;
	
	public static Model getInstance() 
    { 
        if (instance == null) 
        	instance = new UnitCube(); 
  
        return instance; 
    } 
	
	private UnitCube() {
	    super(
    		new Vertex[] {
		        new Vertex(-0.5f, -0.5f,  0.5f),
		        new Vertex(-0.5f,  0.5f,  0.5f),
		        new Vertex( 0.5f,  0.5f,  0.5f),
		        new Vertex( 0.5f, -0.5f,  0.5f),
		        new Vertex(-0.5f, -0.5f, -0.5f),
		        new Vertex(-0.5f,  0.5f, -0.5f),
		        new Vertex( 0.5f,  0.5f, -0.5f),
		        new Vertex( 0.5f, -0.5f, -0.5f)
    		},
    		new int[][] {
	    		{3,2,1,4},
	    		{2,6,5,1},
	    		{7,3,4,8},
	    		{7,6,2,3},
	    		{4,1,5,8},
	    		{6,7,8,5}
    		}
    	);
	}
}
