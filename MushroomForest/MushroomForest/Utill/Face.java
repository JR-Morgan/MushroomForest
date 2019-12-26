package MushroomForest.Utill;

/**
 * Models a OBJ face
 * 
 * @author Jedd Morgan
 *
 */
public class Face
{
	public final Integer[] v, vt;
	
	/**
	 * Creates a new face with vertex and uv
	 * @param v - vertex indices
	 * @param vt - vertex texture indices
	 */
	public Face(Integer[] v, Integer[]vt) {
		this.v = v;
		this.vt = vt;
	}
}
