package MushroomForest.Utill;

/**
 * Models a WaveFront face
 * @author Jedd
 *
 */
public class Face
{
	public final int[] v, vt;
	
	/**
	 * Creates a new face with vertex and uv
	 * @param v - vertex indices
	 * @param vt - vertex texture indices
	 */
	public Face(int[] v, int[]vt) {
		this.v = v;
		this.vt = vt;
	}
}
