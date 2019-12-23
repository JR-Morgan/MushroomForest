package MushroomForest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

public class MainScene extends GraphicsLab
{
	private Vector3f cameraTranslation = new Vector3f();
	private Vector3f cameraRotation = new Vector3f();
	private float camRotateSpeed = 0.02f;
	private float camTranslateSpeed = 0.005f;
	
    private final int planeList = 1;
    private final int cilindarList = 2;
    

    public static void main(String args[])
    {   new MainScene().run(WINDOWED,"Mushroom Forest", 0.01f);
    }

    protected void initScene() throws Exception
    {
        // global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is soft blue...
        float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
        // ...with a very dim ambient contribution...
        float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f};

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        GL11.glNewList(planeList,GL11.GL_COMPILE); {
        	drawUnitPlane();
        } GL11.glEndList();
        
        GL11.glNewList(cilindarList, GL11.GL_COMPILE); {
        	drawUnitCilinder();
        } GL11.glEndList();
    }  
        
    
    protected void checkSceneInput()
    {
    	//Camera
    	{
    		{ 
				float x = (float) Math.sin(Math.toRadians(cameraRotation.getY())) * camTranslateSpeed;
				float z = (float) Math.cos(Math.toRadians(cameraRotation.getY())) * camTranslateSpeed;
				
				if (Keyboard.isKeyDown(Keyboard.KEY_D)) Vector3f.add(this.cameraTranslation, new Vector3f(-z, 0, -x), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) Vector3f.add(this.cameraTranslation, new Vector3f( z, 0,  x), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_S)) Vector3f.add(this.cameraTranslation, new Vector3f( x, 0, -z), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_W)) Vector3f.add(this.cameraTranslation, new Vector3f(-x, 0,  z), this.cameraTranslation);
    		}

            
    		{ //Camera Rotation
    			Vector3f cameraRotation = new Vector3f();
    			
		        if		(Keyboard.isKeyDown(Keyboard.KEY_UP))	cameraRotation.setX(-camRotateSpeed);
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) cameraRotation.setX( camRotateSpeed);
		        if		(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) cameraRotation.setY(-camRotateSpeed);
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))cameraRotation.setY( camRotateSpeed);
		        
		        Vector3f.add(this.cameraRotation, cameraRotation, this.cameraRotation);
    		}
    		
    		
    		{ //Reset Camera
    	        if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
    	        	this.cameraRotation    = new Vector3f();
    	        	this.cameraTranslation = new Vector3f();
    	        }
    		}
	        
    	}
    	
    	
    	
    	
    	
    }
    
    
    
    protected void updateScene()
    {
        
    }
    protected void renderScene()
    {
    	GL11.glLoadIdentity();
    	
        // draw the ground plane
        GL11.glPushMatrix();
        {
        	applyCameraTransform();
    		
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            //GL11.glEnable(GL11.GL_TEXTURE_2D);
            //GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,-1.0f,-10.0f);
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the back plane
        GL11.glPushMatrix();
        {
        	applyCameraTransform();
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            //GL11.glEnable(GL11.GL_TEXTURE_2D);
            //GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyNightTextures.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f,4.0f,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 1.0f, 10.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        //Draw Mushroom
        GL11.glPushMatrix();
        {
        	applyCameraTransform();
    		
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.RED.submit();
            // enable texturing and bind an appropriate texture
            //GL11.glEnable(GL11.GL_TEXTURE_2D);
            //GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(10.0f,1.0f,-10.0f);
            GL11.glScalef(1f, 1f, 1f);
            GL11.glCallList(cilindarList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        
    }
    
    private void applyCameraTransform() {
    	GL11.glRotatef(cameraRotation.getX(), 1, 0, 0);
		GL11.glRotatef(cameraRotation.getY(), 0, 1, 0);
		GL11.glRotatef(cameraRotation.getZ(), 0, 0, 1);
		GL11.glTranslatef(cameraTranslation.getX(), cameraTranslation.getY(), cameraTranslation.getZ());
    }
    
    protected void cleanupScene()
    {// empty
    }
    
    /**
     * Draws a plane aligned with the X and Z axis, with its front face toward positive Y.
     *  The plane is of unit width and height, and uses the current OpenGL material settings
     *  for its appearance
     */
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draw the plane geometry. order the vertices so that the plane faces up
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();
        
        // if the user is viewing an axis, then also draw this plane
        // using lines so that axis aligned planes can still be seen
        if(isViewingAxis())
        {
            // also disable textures when drawing as lines
            // so that the lines can be seen more clearly
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
    /**
     * Draws a cube of unit length, width and height using the current OpenGL material settings
     */
    private void drawUnitCube()
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
        Vertex v3 = new Vertex( 0.5f,  0.5f,  0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);

        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            
        	v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
    
    private void drawUnitCilinder()
    {
    	Vertex[] vertexes = {
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
    	};
    	
    	int[][] faces = {
			{1,2,4,3},
			{3,4,6,5},
			{5,6,8,7},
			{7,8,10,9},
			{9,10,12,11},
			{11,12,14,13},
			{13,14,16,15},
			{15,16,18,17},
			{17,18,20,19},
			{19,20,22,21},
			{21,22,24,23},
			{23,24,26,25},
			{25,26,28,27},
			{27,28,30,29},
			{29,30,32,31},
			{31,32,34,33},
			{33,34,36,35},
			{35,36,38,37},
			{37,38,40,39},
			{39,40,42,41},
			{41,42,44,43},
			{43,44,46,45},
			{45,46,48,47},
			{47,48,50,49},
			{49,50,52,51},
			{51,52,54,53},
			{53,54,56,55},
			{55,56,58,57},
			{57,58,60,59},
			{59,60,62,61},
			//{4,2,64,62,60,58,56,54,52,50,48,46,44,42,40,38,36,34,32,30,28,26,24,22,20,18,16,14,12,10,8,6},
			{61,62,64,63},
			{63,64,2,1},
			//{1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63}
    	};
    	
    	
    	for(int[] face : faces) {
    		
    		GL11.glBegin(GL11.GL_POLYGON);
            {
            	
                new Normal(vertexes[face[3] -1].toVector(),vertexes[face[0]-1].toVector(),vertexes[face[1]-1].toVector(),vertexes[face[2]-1].toVector()).submit();
                
                vertexes[face[3]-1].submit();
                vertexes[face[0]-1].submit();
                vertexes[face[1]-1].submit();
                vertexes[face[2]-1].submit();
            }
            GL11.glEnd();
    	}
        

    }
}
