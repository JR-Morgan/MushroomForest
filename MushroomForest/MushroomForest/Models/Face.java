package MushroomForest.Models;

/**
 * Models a OBJ face
 * 
 * @author Jedd Morgan
 *
 */
public class Face
{
	public final Integer[] v, vn, vt;
	
	/**
	 * Creates a new face with vertex and uv
	 * @param v - vertex indices
	 * @param vn - vertex normal
	 * @param vt - vertex texture indices
	 */
	public Face(Integer[] v, Integer[]vn, Integer[]vt) {
		this.v = v;
		this.vn = vn;
		this.vt = vt;
	}
	
	public Face(Integer[] v, Integer[]vt) {
		this(v, new Integer[0], vt);
	}
}
