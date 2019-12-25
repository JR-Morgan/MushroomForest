package MushroomForest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;
import MushroomForest.Models.OBJLoader;
import MushroomForest.Models.UnitCube;
import MushroomForest.Models.UnitCylinder;
import MushroomForest.Models.UnitPlane;

public class MainScene extends GraphicsLab
{
	private Vector3f cameraTranslation = new Vector3f();
	private Vector3f cameraRotation = new Vector3f();
	private float camRotateSpeedX = 0.15f, camRotateSpeedY = 0.1f;
	private float camTranslateSpeed = 0.025f;
	
    private final int planeList = 1;
    private final int cylinderList = 2;
    private final int cubeList = 3;
    
    private Texture groundTextures;
    

    public static void main(String args[])
    {   new MainScene().run(WINDOWED,"Mushroom Forest", 0.01f);
    }

    protected void initScene() throws Exception
    {
    	groundTextures = loadTexture("textures/grass.bmp");
    	
    	
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
        	UnitPlane.getInstance().draw();
        } GL11.glEndList();
        
        GL11.glNewList(cylinderList, GL11.GL_COMPILE); {
        	OBJLoader.ParseFromFile("OBJ/monkey.obj").draw();
        	//OBJLoader.ParseFromFile("OBJ/cylinder.obj").draw();
        	//UnitCylinder.getInstance().draw();
        } GL11.glEndList();
        
        GL11.glNewList(cubeList, GL11.GL_COMPILE); {
        	OBJLoader.ParseFromFile("OBJ/cube.obj").draw();
        	//UnitCube.getInstance().draw();
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
    			
		        if		(Keyboard.isKeyDown(Keyboard.KEY_UP))	cameraRotation.setX(-camRotateSpeedY);
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) cameraRotation.setX( camRotateSpeedY);
		        if		(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) cameraRotation.setY(-camRotateSpeedX);
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))cameraRotation.setY( camRotateSpeedX);
		        
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
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
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
        
        //Draw Cube
        GL11.glPushMatrix();
        {
        	applyCameraTransform();
    		
            // disable lighting calculations so that they don't affect the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);

            Colour.GREEN.submit();
            // enable texturing and bind an appropriate texture
            //GL11.glEnable(GL11.GL_TEXTURE_2D);
			// GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,0.0f,0.0f);
            GL11.glScalef(1f, 2f, 1f);
            GL11.glCallList(cubeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        
        //Draw Cylinder
        GL11.glPushMatrix();
        {
        	//applyCameraTransform();
    		
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);

            Colour.RED.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(10.0f,1.0f,-10.0f);
            GL11.glScalef(1f, 1f, 1f);
            GL11.glCallList(cylinderList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        
       
        
    }
    
    
    protected void setSceneCamera()
    {
        super.setSceneCamera();
       
        
       	//GLU.gluLookAt(
       	//		cameraTranslation.getX(),
       	//		cameraTranslation.getY(),
       	//		cameraTranslation.getZ(),
       	//		0,
       	//		0,
       	//		0,
       	//		0, 1, 0);
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

}

