package MushroomForest.Models;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import GraphicsLab.Normal;
import GraphicsLab.Vertex;
import MushroomForest.Utill.Face;

/**
 * This class encapsulates a 3D Model storing it's vertexes, faces, and normals.<br>
 * @author Jedd Morgan
 */
public class Model
{
	protected Vertex[] vertexes;
	
	protected Vector2f[] uv;
	
	protected Face[] faces;
	
	protected Normal[] normals;
	
	
	public Model(Vertex[] vertexes, Vector2f[] uv, Face[] faces) {
		this(vertexes, uv, faces, calculateNormals(vertexes, faces));
	}
	
	public Model(Vertex[] vertexes, Vector2f[] uv, Face[] faces, Normal[] normals) {
		this.vertexes = vertexes;
		this.uv = uv;
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
                
                for(int j = 0; j < faces[i].v.length; j++) {
                	if(uv.length > 0) {
                		GL11.glTexCoord2f(uv[faces[i].vt[j]-1].getX(),uv[faces[i].vt[j]-1].getY());
                	}
                    vertexes[faces[i].v[j]-1].submit();
                }
                
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
	private static Normal[] calculateNormals(Vertex[] vertexes, Face[] faces) {
		Normal[] normals = new Normal[faces.length];
		int index = 0;
		for(Face face : faces) {
            {
       		
            	float nX = 0f;
                float nZ = 0f;
                float nY = 0f;
        		for(int vertIndex = 0; vertIndex < face.v.length; vertIndex++)
        		{
        			nX += (vertexes[face.v[vertIndex] - 1].getY()-vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getY())*
        				  (vertexes[face.v[vertIndex] - 1].getZ()+vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getZ());
        			
        			nY += (vertexes[face.v[vertIndex] - 1].getZ()-vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getZ())*
          				  (vertexes[face.v[vertIndex] - 1].getX()+vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getX());
        			
        			nZ += (vertexes[face.v[vertIndex] - 1].getX()-vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getX())*
          				  (vertexes[face.v[vertIndex] - 1].getY()+vertexes[face.v[(vertIndex +1) % (face.v.length)] - 1].getY());
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
	
	public Vector2f[] getTextureCoordinates() {
		return uv;
	}
	
	public Normal[] getNormals() {
		return normals;
	};
	
	public Face[] getFaces() {
		return faces;
	}
}
