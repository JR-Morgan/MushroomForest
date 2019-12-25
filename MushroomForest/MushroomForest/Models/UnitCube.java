package MushroomForest.Models;

import org.lwjgl.util.vector.Vector2f;

import GraphicsLab.Vertex;
import MushroomForest.Utill.Face;

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
				new Vertex( 0.5f, 0.5f, -0.5f),
				new Vertex( 0.5f, -0.5f, -0.5f),
				new Vertex( 0.5f, 0.5f, 0.5f),
				new Vertex( 0.5f, -0.5f, 0.5f),
				new Vertex(-0.5f, 0.5f, -0.5f),
				new Vertex(-0.5f, -0.5f, -0.5f),
				new Vertex(-0.5f, 0.5f, 0.5f),
				new Vertex(-0.5f, -0.5f, 0.5f)
    		},
    		new Vector2f[] {
				new Vector2f(0.375f, 0.00f),
				new Vector2f(0.625f, 0.00f),
				new Vector2f(0.625f, 0.25f),
				new Vector2f(0.375f, 0.25f),
				new Vector2f(0.375f, 0.25f),
				new Vector2f(0.625f, 0.25f),
				new Vector2f(0.625f, 0.50f),
				new Vector2f(0.375f, 0.50f),
				new Vector2f(0.625f, 0.75f),
				new Vector2f(0.375f, 0.75f),
				new Vector2f(0.625f, 0.75f),
				new Vector2f(0.625f, 1.00f),
				new Vector2f(0.375f, 1.00f),
				new Vector2f(0.125f, 0.50f),
				new Vector2f(0.375f, 0.50f),
				new Vector2f(0.375f, 0.75f),
				new Vector2f(0.125f, 0.75f),
				new Vector2f(0.625f, 0.50f),
				new Vector2f(0.875f, 0.50f),
				new Vector2f(0.875f, 0.75f)
    		},
    		new Face[] {
	    		new Face( new int[] {1,5,7,3}, new int[] {1,2,3,4}),
	    		new Face( new int[] {4,3,7,8}, new int[] {5,6,7,8}),
	    		new Face( new int[] {8,7,5,6}, new int[] {8,7,9,10}),
	    		new Face( new int[] {6,2,4,8}, new int[] {10,11,12,13}),
	    		new Face( new int[] {2,1,3,4}, new int[] {14,15,16,17}),
	    		new Face( new int[] {6,5,1,2}, new int[] {18,19,20,11}),
    		}
    	);
	}
}
