package MushroomForest.Models;

import org.lwjgl.util.vector.Vector2f;

import GraphicsLab.Vertex;
import MushroomForest.Utill.Face;

/**
 * Models a cylinder of unit size<br>
 * 
 * This class was used for testing purposes prior to {@link OBJLoader}
 * @author Jedd Morgan
 */
public class UnitCylinder extends Model
{
	private static Model instance;
	
	public static Model getInstance() 
    { 
        if (instance == null) 
        	instance = new UnitCylinder(); 
  
        return instance; 
    } 
	
	private UnitCylinder()
	{
	    super(
	    	new Vertex[]
	    	{
    			new Vertex(0.000000f,-0.500000f,-0.500000f),
    			new Vertex(0.000000f,0.500000f,-0.500000f),
    			new Vertex(0.097545f,-0.500000f,-0.490393f),
    			new Vertex(0.097545f,0.500000f,-0.490393f),
    			new Vertex(0.191342f,-0.500000f,-0.461940f),
    			new Vertex(0.191342f,0.500000f,-0.461940f),
    			new Vertex(0.277785f,-0.500000f,-0.415735f),
    			new Vertex(0.277785f,0.500000f,-0.415735f),
    			new Vertex(0.353553f,-0.500000f,-0.353553f),
    			new Vertex(0.353553f,0.500000f,-0.353553f),
    			new Vertex(0.415735f,-0.500000f,-0.277785f),
    			new Vertex(0.415735f,0.500000f,-0.277785f),
    			new Vertex(0.461940f,-0.500000f,-0.191342f),
    			new Vertex(0.461940f,0.500000f,-0.191342f),
    			new Vertex(0.490393f,-0.500000f,-0.097545f),
    			new Vertex(0.490393f,0.500000f,-0.097545f),
    			new Vertex(0.500000f,-0.500000f,-0.000000f),
    			new Vertex(0.500000f,0.500000f,-0.000000f),
    			new Vertex(0.490393f,-0.500000f,0.097545f),
    			new Vertex(0.490393f,0.500000f,0.097545f),
    			new Vertex(0.461940f,-0.500000f,0.191342f),
    			new Vertex(0.461940f,0.500000f,0.191342f),
    			new Vertex(0.415735f,-0.500000f,0.277785f),
    			new Vertex(0.415735f,0.500000f,0.277785f),
    			new Vertex(0.353553f,-0.500000f,0.353553f),
    			new Vertex(0.353553f,0.500000f,0.353553f),
    			new Vertex(0.277785f,-0.500000f,0.415735f),
    			new Vertex(0.277785f,0.500000f,0.415735f),
    			new Vertex(0.191342f,-0.500000f,0.461940f),
    			new Vertex(0.191342f,0.500000f,0.461940f),
    			new Vertex(0.097545f,-0.500000f,0.490393f),
    			new Vertex(0.097545f,0.500000f,0.490393f),
    			new Vertex(-0.000000f,-0.500000f,0.500000f),
    			new Vertex(-0.000000f,0.500000f,0.500000f),
    			new Vertex(-0.097545f,-0.500000f,0.490393f),
    			new Vertex(-0.097545f,0.500000f,0.490393f),
    			new Vertex(-0.191342f,-0.500000f,0.461940f),
    			new Vertex(-0.191342f,0.500000f,0.461940f),
    			new Vertex(-0.277785f,-0.500000f,0.415735f),
    			new Vertex(-0.277785f,0.500000f,0.415735f),
    			new Vertex(-0.353554f,-0.500000f,0.353553f),
    			new Vertex(-0.353554f,0.500000f,0.353553f),
    			new Vertex(-0.415735f,-0.500000f,0.277785f),
    			new Vertex(-0.415735f,0.500000f,0.277785f),
    			new Vertex(-0.461940f,-0.500000f,0.191341f),
    			new Vertex(-0.461940f,0.500000f,0.191341f),
    			new Vertex(-0.490393f,-0.500000f,0.097545f),
    			new Vertex(-0.490393f,0.500000f,0.097545f),
    			new Vertex(-0.500000f,-0.500000f,-0.000000f),
    			new Vertex(-0.500000f,0.500000f,-0.000000f),
    			new Vertex(-0.490393f,-0.500000f,-0.097546f),
    			new Vertex(-0.490393f,0.500000f,-0.097546f),
    			new Vertex(-0.461940f,-0.500000f,-0.191342f),
    			new Vertex(-0.461940f,0.500000f,-0.191342f),
    			new Vertex(-0.415734f,-0.500000f,-0.277786f),
    			new Vertex(-0.415734f,0.500000f,-0.277786f),
    			new Vertex(-0.353553f,-0.500000f,-0.353554f),
    			new Vertex(-0.353553f,0.500000f,-0.353554f),
    			new Vertex(-0.277785f,-0.500000f,-0.415735f),
    			new Vertex(-0.277785f,0.500000f,-0.415735f),
    			new Vertex(-0.191341f,-0.500000f,-0.461940f),
    			new Vertex(-0.191341f,0.500000f,-0.461940f),
    			new Vertex(-0.097544f,-0.500000f,-0.490393f),
    			new Vertex(-0.097544f,0.500000f,-0.490393f)	
	    	},
	    	new Vector2f[] {
    			new Vector2f(1.000000f, 0.500000f),
    			new Vector2f(1.000000f, 1.000000f),
    			new Vector2f(0.968750f, 1.000000f),
    			new Vector2f(0.968750f, 0.500000f),
    			new Vector2f(0.937500f, 1.000000f),
    			new Vector2f(0.937500f, 0.500000f),
    			new Vector2f(0.906250f, 1.000000f),
    			new Vector2f(0.906250f, 0.500000f),
    			new Vector2f(0.875000f, 1.000000f),
    			new Vector2f(0.875000f, 0.500000f),
    			new Vector2f(0.843750f, 1.000000f),
    			new Vector2f(0.843750f, 0.500000f),
    			new Vector2f(0.812500f, 1.000000f),
    			new Vector2f(0.812500f, 0.500000f),
    			new Vector2f(0.781250f, 1.000000f),
    			new Vector2f(0.781250f, 0.500000f),
    			new Vector2f(0.750000f, 1.000000f),
    			new Vector2f(0.750000f, 0.500000f),
    			new Vector2f(0.718750f, 1.000000f),
    			new Vector2f(0.718750f, 0.500000f),
    			new Vector2f(0.687500f, 1.000000f),
    			new Vector2f(0.687500f, 0.500000f),
    			new Vector2f(0.656250f, 1.000000f),
    			new Vector2f(0.656250f, 0.500000f),
    			new Vector2f(0.625000f, 1.000000f),
    			new Vector2f(0.625000f, 0.500000f),
    			new Vector2f(0.593750f, 1.000000f),
    			new Vector2f(0.593750f, 0.500000f),
    			new Vector2f(0.562500f, 1.000000f),
    			new Vector2f(0.562500f, 0.500000f),
    			new Vector2f(0.531250f, 1.000000f),
    			new Vector2f(0.531250f, 0.500000f),
    			new Vector2f(0.500000f, 1.000000f),
    			new Vector2f(0.500000f, 0.500000f),
    			new Vector2f(0.468750f, 1.000000f),
    			new Vector2f(0.468750f, 0.500000f),
    			new Vector2f(0.437500f, 1.000000f),
    			new Vector2f(0.437500f, 0.500000f),
    			new Vector2f(0.406250f, 1.000000f),
    			new Vector2f(0.406250f, 0.500000f),
    			new Vector2f(0.375000f, 1.000000f),
    			new Vector2f(0.375000f, 0.500000f),
    			new Vector2f(0.343750f, 1.000000f),
    			new Vector2f(0.343750f, 0.500000f),
    			new Vector2f(0.312500f, 1.000000f),
    			new Vector2f(0.312500f, 0.500000f),
    			new Vector2f(0.281250f, 1.000000f),
    			new Vector2f(0.281250f, 0.500000f),
    			new Vector2f(0.250000f, 1.000000f),
    			new Vector2f(0.250000f, 0.500000f),
    			new Vector2f(0.218750f, 1.000000f),
    			new Vector2f(0.218750f, 0.500000f),
    			new Vector2f(0.187500f, 1.000000f),
    			new Vector2f(0.187500f, 0.500000f),
    			new Vector2f(0.156250f, 1.000000f),
    			new Vector2f(0.156250f, 0.500000f),
    			new Vector2f(0.125000f, 1.000000f),
    			new Vector2f(0.125000f, 0.500000f),
    			new Vector2f(0.093750f, 1.000000f),
    			new Vector2f(0.093750f, 0.500000f),
    			new Vector2f(0.062500f, 1.000000f),
    			new Vector2f(0.062500f, 0.500000f),
    			new Vector2f(0.296822f, 0.485388f),
    			new Vector2f(0.250000f, 0.490000f),
    			new Vector2f(0.203179f, 0.485389f),
    			new Vector2f(0.158156f, 0.471731f),
    			new Vector2f(0.116663f, 0.449553f),
    			new Vector2f(0.080295f, 0.419706f),
    			new Vector2f(0.050447f, 0.383337f),
    			new Vector2f(0.028269f, 0.341844f),
    			new Vector2f(0.014612f, 0.296822f),
    			new Vector2f(0.010000f, 0.250000f),
    			new Vector2f(0.014611f, 0.203179f),
    			new Vector2f(0.028269f, 0.158156f),
    			new Vector2f(0.050447f, 0.116663f),
    			new Vector2f(0.080294f, 0.080294f),
    			new Vector2f(0.116663f, 0.050447f),
    			new Vector2f(0.158156f, 0.028269f),
    			new Vector2f(0.203178f, 0.014612f),
    			new Vector2f(0.250000f, 0.010000f),
    			new Vector2f(0.296822f, 0.014612f),
    			new Vector2f(0.341844f, 0.028269f),
    			new Vector2f(0.383337f, 0.050447f),
    			new Vector2f(0.419706f, 0.080294f),
    			new Vector2f(0.449553f, 0.116663f),
    			new Vector2f(0.471731f, 0.158156f),
    			new Vector2f(0.485388f, 0.203178f),
    			new Vector2f(0.490000f, 0.250000f),
    			new Vector2f(0.485388f, 0.296822f),
    			new Vector2f(0.471731f, 0.341844f),
    			new Vector2f(0.449553f, 0.383337f),
    			new Vector2f(0.419706f, 0.419706f),
    			new Vector2f(0.383337f, 0.449553f),
    			new Vector2f(0.341844f, 0.471731f),
    			new Vector2f(0.031250f, 1.000000f),
    			new Vector2f(0.031250f, 0.500000f),
    			new Vector2f(0.000000f, 1.000000f),
    			new Vector2f(0.000000f, 0.500000f),
    			new Vector2f(0.750000f, 0.490000f),
    			new Vector2f(0.796822f, 0.485388f),
    			new Vector2f(0.841844f, 0.471731f),
    			new Vector2f(0.883337f, 0.449553f),
    			new Vector2f(0.919706f, 0.419706f),
    			new Vector2f(0.949553f, 0.383337f),
    			new Vector2f(0.971731f, 0.341844f),
    			new Vector2f(0.985388f, 0.296822f),
    			new Vector2f(0.990000f, 0.250000f),
    			new Vector2f(0.985388f, 0.203178f),
    			new Vector2f(0.971731f, 0.158156f),
    			new Vector2f(0.949553f, 0.116663f),
    			new Vector2f(0.919706f, 0.080294f),
    			new Vector2f(0.883337f, 0.050447f),
    			new Vector2f(0.841844f, 0.028269f),
    			new Vector2f(0.796822f, 0.014612f),
    			new Vector2f(0.750000f, 0.010000f),
    			new Vector2f(0.703178f, 0.014612f),
    			new Vector2f(0.658156f, 0.028269f),
    			new Vector2f(0.616663f, 0.050447f),
    			new Vector2f(0.580294f, 0.080294f),
    			new Vector2f(0.550447f, 0.116663f),
    			new Vector2f(0.528269f, 0.158156f),
    			new Vector2f(0.514611f, 0.203179f),
    			new Vector2f(0.510000f, 0.250000f),
    			new Vector2f(0.514612f, 0.296822f),
    			new Vector2f(0.528269f, 0.341844f),
    			new Vector2f(0.550447f, 0.383337f),
    			new Vector2f(0.580295f, 0.419706f),
    			new Vector2f(0.616663f, 0.449553f),
    			new Vector2f(0.658156f, 0.471731f),
    			new Vector2f(0.703179f, 0.485389f)
	    	},
	    	new Face[]
	    	{
				new Face(new Integer[] {1,2,4,3},	 new Integer[] {1,2,3,4}),
				new Face(new Integer[] {3,4,6,5},	 new Integer[] {4,3,5,6}),
				new Face(new Integer[] {5,6,8,7},	 new Integer[] {6,5,7,8}),
				new Face(new Integer[] {7,8,10,9},	 new Integer[] {8,7,9,10}),
				new Face(new Integer[] {9,10,12,11}, new Integer[] {10,9,11,12}),
				new Face(new Integer[] {11,12,14,13},new Integer[] {12,11,13,14}),
				new Face(new Integer[] {13,14,16,15},new Integer[] {14,13,15,16}),
				new Face(new Integer[] {15,16,18,17},new Integer[] {16,15,17,18}),
				new Face(new Integer[] {17,18,20,19},new Integer[] {18,17,19,20}),
				new Face(new Integer[] {19,20,22,21},new Integer[] {20,19,21,22}),
				new Face(new Integer[] {21,22,24,23},new Integer[] {22,21,23,24}),
				new Face(new Integer[] {23,24,26,25},new Integer[] {24,23,25,26}),
				new Face(new Integer[] {25,26,28,27},new Integer[] {26,25,27,28}),
				new Face(new Integer[] {27,28,30,29},new Integer[] {28,27,29,30}),
				new Face(new Integer[] {29,30,32,31},new Integer[] {30,29,31,32}),
				new Face(new Integer[] {31,32,34,33},new Integer[] {32,31,33,34}),
				new Face(new Integer[] {33,34,36,35},new Integer[] {34,33,35,36}),
				new Face(new Integer[] {35,36,38,37},new Integer[] {36,35,37,38}),
				new Face(new Integer[] {37,38,40,39},new Integer[] {38,37,39,40}),
				new Face(new Integer[] {39,40,42,41},new Integer[] {40,39,41,42}),
				new Face(new Integer[] {41,42,44,43},new Integer[] {42,41,43,44}),
				new Face(new Integer[] {43,44,46,45},new Integer[] {44,43,45,46}),
				new Face(new Integer[] {45,46,48,47},new Integer[] {46,45,47,48}),
				new Face(new Integer[] {47,48,50,49},new Integer[] {48,47,49,50}),
				new Face(new Integer[] {49,50,52,51},new Integer[] {50,49,51,52}),
				new Face(new Integer[] {51,52,54,53},new Integer[] {52,51,53,54}),
				new Face(new Integer[] {53,54,56,55},new Integer[] {54,53,55,56}),
				new Face(new Integer[] {55,56,58,57},new Integer[] {56,55,57,58}),
				new Face(new Integer[] {57,58,60,59},new Integer[] {58,57,59,60}),
				new Face(new Integer[] {59,60,62,61},new Integer[] {60,59,61,62}),
				new Face(new Integer[] {4,2,64,62,60,58,56,54,52,50,48,46,44,42,40,38,36,34,32,30,28,26,24,22,20,18,16,14,12,10,8,6}, new Integer[] {63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94}),
				new Face(new Integer[] {61,62,64,63},new Integer[] {62,61,95,96}),
				new Face(new Integer[] {63,64,2,1},  new Integer[] {96,95,97,98}),
				new Face(new Integer[] {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63}, new Integer[] {99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130})
	    	}
	    );
    }
	
}
