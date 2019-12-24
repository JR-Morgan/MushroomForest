package MushroomForest.Models;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * This class encapsulates a 3D Model storing it's vertexes, faces, and normals.<br>
 * @author Jedd Morgan
 */
public class Model
{
	protected Vertex[] vertexes;
	
	protected int[][] faces;
	
	protected Normal[] normals;
	
	public Model(Vertex[] vertexes, int[][] faces) {
		this(vertexes, faces, calculateNormals(vertexes, faces));
	}
	
	public Model(Vertex[] vertexes, int[][] faces, Normal[] normals) {
		this.vertexes = vertexes;
		this.faces = faces;
		this.normals = normals;
		
	}

	/**
	 * Submits each {@link Vertex} and {@link Normal}
	 */
	public void draw()
    {
		for(int i = 0; i < faces.length; i++) {
    		
    		GL11.glBegin(GL11.GL_POLYGON);
            {              
                normals[i].submit();
        		        		
                vertexes[faces[i][3]-1].submit();
                vertexes[faces[i][0]-1].submit();
                vertexes[faces[i][1]-1].submit();
                vertexes[faces[i][2]-1].submit();
            }
            GL11.glEnd();
    	}
    }
	
	/**
	 * Calculates normals for each face
	 * @param vertexes
	 * @param faces
	 * @return an array of {@link Normal}s for each face
	 */
	private static Normal[] calculateNormals(Vertex[] vertexes, int[][] faces) {
		Normal[] normals = new Normal[faces.length];
		int index = 0;
		for(int[] face : faces) {
            {
       		
            	float nX = 0f;
                float nZ = 0f;
                float nY = 0f;
        		for(int vertIndex = 0; vertIndex < face.length; vertIndex++)
        		{
        			nX += (vertexes[face[vertIndex] - 1].getY()-vertexes[face[(vertIndex +1) % (face.length)] - 1].getY())*
        				  (vertexes[face[vertIndex] - 1].getZ()+vertexes[face[(vertIndex +1) % (face.length)] - 1].getZ());
        			
        			nY += (vertexes[face[vertIndex] - 1].getZ()-vertexes[face[(vertIndex +1) % (face.length)] - 1].getZ())*
          				  (vertexes[face[vertIndex] - 1].getX()+vertexes[face[(vertIndex +1) % (face.length)] - 1].getX());
        			
        			nZ += (vertexes[face[vertIndex] - 1].getX()-vertexes[face[(vertIndex +1) % (face.length)] - 1].getX())*
          				  (vertexes[face[vertIndex] - 1].getY()+vertexes[face[(vertIndex +1) % (face.length)] - 1].getY());
        		}
        		
                
        		normals[index] = new Normal(nX,nY,nZ);
        		index++;
            }
            
    	}
		return normals;
	}
	
	public Vertex[] getVertexes() {
		return vertexes;
	};
	
	public Normal[] getNormals() {
		return normals;
	};
	
	public int[][] getFaces() {
		return faces;
	}
}
