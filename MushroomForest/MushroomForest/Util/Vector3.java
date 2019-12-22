package MushroomForest.Util;

public final class Vector3
{
	private float x;
	private float y;
	private float z;
	

	public Vector3() {
		this(0, 0, 0);
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX()
	{
		return x;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	public float getY()
	{
		return y;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	public float getZ()
	{
		return z;
	}
	public void setZ(float z)
	{
		this.z = z;
	}
	
	public void add(Vector3 vector) {
		this.x += vector.getX();
		this.y += vector.getY();
		this.z += vector.getZ();
	}
	
	public void multiply(Vector3 vector) {
		this.x *= vector.getX();
		this.y *= vector.getY();
		this.z *= vector.getZ();
	}
	
    public void normalise()
    {
        double length = lentgh();

        x /= length;
        y /= length;
        z /= length;
    }
	
	public double lentgh() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
}
