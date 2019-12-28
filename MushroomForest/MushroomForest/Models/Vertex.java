package MushroomForest.Models;

import org.lwjgl.util.vector.Vector3f;

/**
 * This class models a vertex<br>
 * @author Jedd
 *
 */
public class Vertex
{

	private Vector3f vertex;
	private Vector3f vertexNormal;
	private Vector3f UV;
	
	
	public Vertex(Vector3f vertex)
	{
		this(vertex, null, null);
	}
	
	/**
	 * Creates a new Vertex
	 * @param vertex - the vector position of the vertex
	 * @param vertexNormal - the vertexNormal
	 * @param UV - the Texture Coordinate
	 */
	public Vertex(Vector3f vertex, Vector3f vertexNormal, Vector3f UV)
	{
		this.vertex = vertex;
		this.vertexNormal = vertexNormal;
		this.UV = UV;
	}

	public Vector3f getVertex()
	{
		return vertex;
	}

	public void setVertex(Vector3f vertex)
	{
		this.vertex = vertex;
	}

	public Vector3f getVertexNormal()
	{
		return vertexNormal;
	}

	public void setVertexNormal(Vector3f vertexNormal)
	{
		this.vertexNormal = vertexNormal;
	}

	public Vector3f getUV()
	{
		return UV;
	}

	public void setUV(Vector3f uV)
	{
		UV = uV;
	}
	

}
