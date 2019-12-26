package MushroomForest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;
import MushroomForest.Models.OBJLoader;
import MushroomForest.Models.UnitCube;
import MushroomForest.Models.UnitCylinder;
import MushroomForest.Models.UnitPlane;
/**
 * <h1>Mushroom Forest</h1>
 * This is the Main Scene and entry point for this program<br>
 * This is a 3D Scene - an interactive demo showing multiple animations and interactions.<br>
 * <br>
 * TODO: scene graph
 * <br>
 * @author Jedd Morgan
 *
 */
public class MainScene extends GraphicsLab
{
	private Vector3f cameraTranslation = new Vector3f(0f,2f,0f);
	private Vector3f cameraRotation = new Vector3f();
	private float camRotateSpeedX = 0.1f, camRotateSpeedY = 0.05f;
	private float camTranslateSpeed = 0.005f;
	
	
	private Vector3f camRotation = new Vector3f(1f,0f,0f);
	private float cameraZoom = 10f;
	private float camRotateSpeed = 0.1f, camZoomSpeed = 0.05f;
	
	private int planeList = 1, cylinderList = 2, mushroomList = 3;
	
    private List<RenderInstance> renderInstances;
    
    private Texture groundTextures;
    
    private int mushroomFieldWidth = 1, mushroomFieldHeigth = 1;
    private int maxMushroomCount = 150;
    

    public static void main(String args[])
    {   new MainScene().run(WINDOWED,"Mushroom Forest", 1f);
    }

    protected void initScene() throws Exception
    {
    	
    	renderInstances = new ArrayList<RenderInstance>();
    	
    	{ //Textures
    		groundTextures = loadTexture("textures/grass.bmp");
    	}
    	
    	{ //Lighting
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
	        
	    } 
        
    	{ //Display Lists
	        GL11.glNewList(planeList,GL11.GL_COMPILE); {
	        	UnitPlane.getInstance().draw();
	        } GL11.glEndList();
	        
	        GL11.glNewList(cylinderList, GL11.GL_COMPILE); {
	        	//OBJLoader.ParseFromFile("OBJ/monkey.obj").draw();
	        	OBJLoader.ParseFromFile("OBJ/cylinder.obj").draw();
	        	//UnitCylinder.getInstance().draw();
	        } GL11.glEndList();
	        
	        GL11.glNewList(mushroomList, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/mushroom_medium.obj").draw();
	        	//UnitCube.getInstance().draw();
	        } GL11.glEndList();
	        Mushroom.displayListIndex = mushroomList;
	    }
        
    	
    		
    	generateRenderInstances();
    	
        
       
    } 
    
    private void generateRenderInstances() {
    	
    	renderInstances = new ArrayList<RenderInstance>();
    	
    	 	renderInstances.add(new RenderInstance(planeList, 			     //displayListIndex
    											   new Vector3f(-10.0f,0.0f,-10.0f),//position
							    				   new Vector3f(0f,0f,0f),    //rotation
							    				   new Vector3f(20f,1f,20f),  //scale
							    				   Colour.WHITE,     	      //colour
							    				   groundTextures));          //texture
    		
    		renderInstances.add(new RenderInstance(planeList, 			     //displayListIndex
												   new Vector3f(10.0f,0.0f,-10.0f),//position
								 				   new Vector3f(0f,0f,0f),    //rotation
								 				   new Vector3f(20f,1f,20f),  //scale
								 				   Colour.WHITE,     	      //colour
								 				   groundTextures));          //texture
    		
    		renderInstances.add(new RenderInstance(planeList, 			     //displayListIndex
												   new Vector3f(-10.0f,0.0f,10.0f),//position
								 				   new Vector3f(0f,0f,0f),    //rotation
								 				   new Vector3f(20f,1f,20f),  //scale
								 				   Colour.WHITE,     	      //colour
								 				   groundTextures));          //texture    
    		renderInstances.add(new RenderInstance(planeList, 			     //displayListIndex
												   new Vector3f(10.0f,0.0f,10.0f),//position
								 				   new Vector3f(0f,0f,0f),    //rotation
								 				   new Vector3f(20f,1f,20f),  //scale
								 				   Colour.WHITE,     	      //colour
								 				   groundTextures));          //texture   
    	
    	for(int x = 0; x < mushroomFieldWidth; x++) {
        	for(int z = 0; z < mushroomFieldHeigth; z++) {
	        	Mushroom m = new Mushroom(new Vector3f(x - mushroomFieldWidth / 2,
	        										   0,
	        										   z - mushroomFieldHeigth / 2));
	        	renderInstances.add(m);
        	}
        }
    	
    }
        
    
    protected void checkSceneInput()
    {
    	//Camera
    	{
    		{ //Update Camera Translation
				float x = (float) Math.sin(Math.toRadians(cameraRotation.getY())) * camTranslateSpeed * (float)Timer.getDeltaTime();
				float z = (float) Math.cos(Math.toRadians(cameraRotation.getY())) * camTranslateSpeed * (float)Timer.getDeltaTime();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) Vector3f.add(this.cameraTranslation, new Vector3f(-z, 0, -x), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_D)) Vector3f.add(this.cameraTranslation, new Vector3f( z, 0,  x), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_W)) Vector3f.add(this.cameraTranslation, new Vector3f( x, 0, -z), this.cameraTranslation);
				if (Keyboard.isKeyDown(Keyboard.KEY_S)) Vector3f.add(this.cameraTranslation, new Vector3f(-x, 0,  z), this.cameraTranslation);
    		}

            
    		{ //Update Camera Rotation
    			Vector3f cameraRotation = new Vector3f();
    			
		        if		(Keyboard.isKeyDown(Keyboard.KEY_UP))	cameraRotation.setX(-camRotateSpeedY * (float)Timer.getDeltaTime());
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) cameraRotation.setX( camRotateSpeedY * (float)Timer.getDeltaTime());
		        if		(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) cameraRotation.setY(-camRotateSpeedX * (float)Timer.getDeltaTime());
		        else if	(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))cameraRotation.setY( camRotateSpeedX * (float)Timer.getDeltaTime());
		        
		        Vector3f.add(this.cameraRotation, cameraRotation, this.cameraRotation);
    		}
    		
    		
    		{ //Reset Camera
    	        if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
    	        	this.cameraRotation    = new Vector3f();
    	        	this.cameraTranslation = new Vector3f(0f,2f,0f);
    	        }
    		}
	        
    		
    		
    		{ // Key Events
    			while(Keyboard.next())
    		    {
    		        if(Keyboard.getEventKey() == Keyboard.KEY_EQUALS && !Keyboard.getEventKeyState())
    		        {
    		        	if(mushroomFieldWidth * mushroomFieldHeigth < maxMushroomCount) {
	    		        	mushroomFieldWidth++;
	        				mushroomFieldHeigth++;
	        				generateRenderInstances();
    		        	}
    		        } else if(Keyboard.getEventKey() == Keyboard.KEY_MINUS && !Keyboard.getEventKeyState())
    		        {
    		        	if(mushroomFieldWidth * mushroomFieldHeigth > 0) {
    		        		mushroomFieldWidth--;
            				mushroomFieldHeigth--;
            				generateRenderInstances();
    		        	}
    		        	
    		        }
    		        
    		    }
    		}
    		
    		
    	}
    	
    	
    	
    	
    }
    /*
    protected void _checkSceneInput()
    {
    	//Camera
    	{
    		
    		
    		
    		
    		{ // Update Camera
    			if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
    				cameraZoom += camZoomSpeed;
    	        }
    			if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
    				cameraZoom -= camZoomSpeed;
    	        }
    			if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
    				float x = (float) Math.cos(camRotateSpeed);
    				float z = (float) Math.sin(camRotateSpeed);

    				camRotation.setX(camRotation.getX() * x - camRotation.getZ() * z);
    				camRotation.setZ(camRotation.getX() * z + camRotation.getZ() * x);
    	        }
    			if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
    				float x = (float) Math.cos(-camRotateSpeed);
    				float z = (float) Math.sin(-camRotateSpeed);

    				camRotation.setX(camRotation.getX() * x - camRotation.getZ() * z);
    				camRotation.setZ(camRotation.getX() * z + camRotation.getZ() * x);
    	        }
    		}

    		
    		
    		{ //Reset Camera
    	        if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
    	        	
    	        }
    	        
    		}
	        
    	}
    	
    	
    	
    	
    }*/
    
    protected void updateScene()
    {
    	Timer.update();
    	
    	for(RenderInstance r : renderInstances) {
        	r.update(getAnimationScale());
        }
    }
    
    /**
     * Renders every {@link RenderInstance} in {@link MainScene.renderInstance}
     */
    protected void renderScene()
    {
    	        
        for(RenderInstance r : renderInstances) {
        	GL11.glPushMatrix();
        	
        	if(!isViewingAxis()) {
        		applyCameraTransform();
        	}
        	r.render();

            GL11.glPopMatrix();
        }
        
    }
    
    
    protected void setSceneCamera()
    {
        super.setSceneCamera();
       
        
        //TODO: Consider using gluLookAt for camera translation and rotation rather than applyCameraTransform()
        //This does however require a rework of the camera rotation since gluLookAt wants a direction vector rather than a rotation angle
        //This could be achieved with a rotation matrix however current solution 
        //GLU.gluLookAt(
       	//		20f,
       	//		1f,
       	//		1f,
       	//		0f,
       	//		0f,
       	//		0f,
       	//		0f, 0f, 1f);
        
        //GLU.gluLookAt(
       	//		camRotation.getX() * cameraZoom,
       	//		camRotation.getY() * cameraZoom +1,
       	//		camRotation.getZ() * cameraZoom,
       	//		0,
       	//		0,
       	//		0,
       	//		0, 1, 0);
   }
    
    /**
     * Applies the camera translation and rotation<br>
     * <br>
	 * This method should be called between<br>
	 * {@link GL11.glPushMatrix} and {@link GL11.glPopMatrix} method calls during the rendering of every model     
	 * */
    private void applyCameraTransform() {
    		GL11.glRotatef(cameraRotation.getX(), 1, 0, 0);
    		GL11.glRotatef(cameraRotation.getY(), 0, 1, 0);
    		GL11.glRotatef(cameraRotation.getZ(), 0, 0, 1);
    		GL11.glTranslatef(-cameraTranslation.getX(), -cameraTranslation.getY(), -cameraTranslation.getZ());    	
    }
    
    protected void cleanupScene()
    {
    	
    }

}

