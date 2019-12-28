/* MainScene.java
 * A Mushroom Forest, with sine wave animated mushrooms and circling butterflies
 * 27/12/2019
 * 
 * Scene Graph:
 * Scene origin
 * |
 * +-- [S(30,1,30) T(-15,0,-15)] Ground plane
 * |
 * +-- [S(30,1,30) T( 15,0,-15)] Ground plane
 * |
 * +-- [S(30,1,30) T(-15,0, 15)] Ground plane
 * |
 * +-- [S(30,1,30) T( 15,0, 15)] Ground plane
 * |
 * +-- [S(100,125,100) T(25,0, 25)] Large Mushroom
 * |
 * +-- [S(25,25,25) T(15,0, 20) R(0,0,10)] Medium Mushroom
 * |
 * +-- [S(50,50,50) T(-25,0, -25)] Medium Mushroom
 * |
 * +-- [S(25,25,-25) T(40,40, 40)] Medium Mushroom
 * |
 * +-- [S(45,45,45) T(-25,0, 25)] Medium Mushroom
 * |
 * +-- [T(10,14,0) R(Random(1-360) then increasing each frame) RO(0,0,0)] Butterfly
 * |   |
 * |   +-- [R(0,0, Scaling Value)] Left Wing
 * |   +-- [R(0,0, Scaling Value)] Right Wing
 * |
 * +-- [T(7,10,0f) R(Random(1-360) then increasing each frame) RO(0,0,0)] Butterfly
 * |   |
 * |   +-- [R(0,0, Scaling Value)] Left Wing
 * |   +-- [R(0,0, Scaling Value)] Right Wing
 * |
 * +-- [T(15,20,0) R(Random(1-360) then increasing each frame) RO(0,0,0))] Butterfly
 * |   |
 * |   +-- [R(0,0, Scaling Value)] Left Wing
 * |   +-- [R(0,0, Scaling Value)] Right Wing
 * |
 * +-- [T(12,25,0) R(Random(1-360) then increasing each frame) RO(0,0,0)] Butterfly
 * |   |
 * |   +-- [R(0,0, Scaling Value)] Left Wing
 * |   +-- [R(0,0, Scaling Value)] Right Wing
 * |
 * +--[T(X index,0, Z index)] Small Mushroom - Increasing as more mushrooms are added upto maxMushroomCount
 * 
 *  
 */
package MushroomForest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;
import MushroomForest.Models.OBJLoader;
import MushroomForest.Models.UnitPlane;
/**
 * <h1>Mushroom Forest</h1>
 * This is the Main Scene and entry point for this program<br>
 * This is a 3D Scene - an interactive demo showing multiple animations and interactions.<br>
 * <br>
 *  The user can interact with this scene<br>
 * <br>
 *  W S A and D will move the camera around the scene<br>
 * Arrow Keys will rotate the camera<br><br>
 * The = key will increse the number of mushrooms<br>
 * The - key will decrese the number of mushrooms<br>
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
	
	private HashMap<String, Integer> displayLists;
	
	
    private List<RenderInstance> renderInstances;
    
    private Texture groundTextures;
    private Texture bigMushroomTexture;
    private Texture smallMushroomTexture;
    
    private int mushroomFieldWidth = 1, mushroomFieldHeigth = 1;
    private int maxMushroomCount = 500;
    

    public static void main(String args[])
    {   new MainScene().run(WINDOWED,"Mushroom Forest", 1f);
    }

    @Override
    protected void initScene() throws Exception
    {
    	displayLists = new HashMap<String, Integer>();
    	renderInstances = new ArrayList<RenderInstance>();
    	
    	{ //Textures
    		groundTextures = loadTexture("textures/grass.bmp");
    		bigMushroomTexture = loadTexture("textures/mushroom.bmp");
    		smallMushroomTexture = loadTexture("textures/mushroom_small.bmp");
    	}
    	
    	{ //Lighting
	        // global ambient light level
	        float globalAmbient[]   = {0.7f,  0.7f,  0.7f, 1.0f};
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
    	  //A display list is setup for each model
    		
    		int counter = 0;
    		
    		displayLists.put("plane", ++counter);
	        GL11.glNewList(1,GL11.GL_COMPILE); {
	        	UnitPlane.getInstance().draw();
	        } GL11.glEndList();
	        
	        //displayLists.put("test", 2);
	        //GL11.glNewList(2, GL11.GL_COMPILE); {
	        //	//OBJLoader.ParseFromFile("OBJ/monkey.obj").draw();
	        //	OBJLoader.ParseFromFile("OBJ/cylinder.obj").draw();
	        //	//UnitCylinder.getInstance().draw();
	        //} GL11.glEndList();
	        
	        displayLists.put("sMushroom", ++counter);
	        GL11.glNewList(counter, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/mushroom_low.obj").draw();
	        } GL11.glEndList();
	        
	        displayLists.put("mMushroom", ++counter);
	        Mushroom.displayListIndex =  counter;
	        GL11.glNewList(counter, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/mushroom_medium.obj").draw();
	        } GL11.glEndList();
	        displayLists.put("lMushroom", ++counter);
	        GL11.glNewList(counter, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/mushroom_high.obj").draw();
	        } GL11.glEndList();
	        
	        displayLists.put("lButterflyWing", ++counter);
	        Butterfly.leftWingDisplayIndex =  counter;
	        GL11.glNewList(counter, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/leftButterfly.obj").draw();
	        } GL11.glEndList();
	        
	        displayLists.put("rButterflyWing", ++counter);
	        Butterfly.rightWingDisplayIndex =  counter;
	        GL11.glNewList(counter, GL11.GL_COMPILE); {
	        	OBJLoader.ParseFromFile("OBJ/rightButterfly.obj").draw();
	        } GL11.glEndList();
	        
	    }
        
    	
    		
    	generateRenderInstances();
    	
        
       
    } 
    
    /**
     * This method will generate / regenerate {@link RenderInstance} in the scene.<br>
     * This method is used on first initialisation of the scene<br>
     * And when the objects in the scene need to be regenerated e.g. to change the number of mushrooms<br>
     */
    private void generateRenderInstances() {
    	
    	renderInstances = new ArrayList<RenderInstance>();
    	
    	{ // Ground Planes
    	 	renderInstances.add(new RenderInstance(displayLists.get("plane"),       //displayListIndex
    											   new Vector3f(-15.0f,0.0f,-15.0f),//position
							    				   new Vector3f(0f,0f,0f),          //rotation
							    				   new Vector3f(),   				//rotation origin
							    				   new Vector3f(30f,1f,30f),        //scale
							    				   Colour.WHITE,     	            //colour
							    				   false,							//disable lighting
							    				   groundTextures, 					//texture
							    				   null));               			//children
    	 								
    		
    		renderInstances.add(new RenderInstance(displayLists.get("plane"), 	   //displayListIndex
												   new Vector3f(15.0f,0.0f,-15.0f),//position
								 				   new Vector3f(0f,0f,0f),         //rotation
							    				   new Vector3f(),   			   //rotation origin
								 				   new Vector3f(30f,1f,30f),       //scale
								 				   Colour.WHITE,     	           //colour
												   false,						   //disable lighting
								 				   groundTextures,                 //texture
								 				   null));               		   //children
    		
    		renderInstances.add(new RenderInstance(displayLists.get("plane"),      //displayListIndex
												   new Vector3f(-15.0f,0.0f,15.0f),//position
								 				   new Vector3f(0f,0f,0f),         //rotation
							    				   new Vector3f(),   			   //rotation origin
								 				   new Vector3f(30f,1f,30f),       //scale
								 				   Colour.WHITE,     	           //colour
												   false,						   //disable lighting
								 				   groundTextures,                 //texture   
								 				   null));               		   //children
    		
    		renderInstances.add(new RenderInstance(displayLists.get("plane"),      //displayListIndex
												   new Vector3f(15.0f,0.0f,15.0f), //position
								 				   new Vector3f(0f,0f,0f),         //rotation
							    				   new Vector3f(),   			   //rotation origin
								 				   new Vector3f(30f,1f,30f),       //scale
								 				   Colour.WHITE,     	           //colour
												   false,						   //disable lighting
								 				   groundTextures,                 //texture   
								 				   null));               		   //children
    		
    	}
    	
    	{ // Big Mushrooms
	    	renderInstances.add(new RenderInstance(displayLists.get("lMushroom"),      //displayListIndex
					   new Vector3f(25.0f,0.0f,25.0f),  //position
					   new Vector3f(0f,0f,0f),          //rotation
					   null,   						    //rotation origin
					   new Vector3f(100f,125f,100f),    //scale
					   Colour.WHITE,     	            //colour
					   false,							//disable lighting
					   bigMushroomTexture,              //texture   
					   null));               		    //children
	    	renderInstances.add(new RenderInstance(displayLists.get("mMushroom"),      //displayListIndex
					   new Vector3f(15.0f,0.0f,20.0f),  //position
					   new Vector3f(0f,0f,10f),         //rotation
					   null,   			  				//rotation origin
					   new Vector3f(25f,25f,25f),       //scale
					   Colour.WHITE,     	            //colour
					   false,							//disable lighting
					   bigMushroomTexture,              //texture   
					   null));               		    //children
	    	
	    	renderInstances.add(new RenderInstance(displayLists.get("mMushroom"),      //displayListIndex
					   new Vector3f(-25.0f,0.0f,-25.0f),//position
					   new Vector3f(0f,0f,0f),          //rotation
					   null,   						    //rotation origin
					   new Vector3f(50f,50f,50f),       //scale
					   Colour.WHITE,     	            //colour
					   false,							//disable lighting
					   bigMushroomTexture,              //texture   
					   null));               		    //children
	 	
	    	renderInstances.add(new RenderInstance(displayLists.get("mMushroom"),      //displayListIndex
					   new Vector3f(25.0f,0.0f,-25.0f), //position
					   new Vector3f(0f,0f,0f),          //rotation
					   null,   			    			//rotation origin
					   new Vector3f(40f,40f,40f),       //scale
					   Colour.WHITE,     	            //colour
					   false,							//disable lighting
					   bigMushroomTexture,              //texture   
					   null)); 
	    	renderInstances.add(new RenderInstance(displayLists.get("mMushroom"),      //displayListIndex
					   new Vector3f(-25.0f,0.0f,25.0f), //position
					   new Vector3f(0f,0f,0f),          //rotation
					   null,   			    			//rotation origin
					   new Vector3f(45f,45f,45f),       //scale
					   Colour.WHITE,     	            //colour
					   false,							//disable lighting
					   bigMushroomTexture,              //texture   
					   null));               		    //children
    	}
    	
    	{ // Butterflies with random starting rotations
    		Random rand = new Random();
        	
        	renderInstances.add(	new Butterfly(new Vector3f(10f,14f,0f),			//position
        							new Vector3f(0f,  rand.nextFloat()* 360, 0f),	//start rotation
        							new Vector3f(0,0,0)));							//rotation origin
        	
        	renderInstances.add(	new Butterfly(new Vector3f(7f,10f,0f),			//position
    								new Vector3f(0f, rand.nextFloat()* 360, 0f),	//start rotation
    								new Vector3f(0,0,0)));							//rotation origin
        	
        	renderInstances.add(	new Butterfly(new Vector3f(15f,20f,0f),			//position
    								new Vector3f(0f, rand.nextFloat()* 360, 0f),	//start rotation
    								new Vector3f(0,0,0)));							//rotation origin
        	
        	renderInstances.add(	new Butterfly(new Vector3f(12f,25f,0f),			//position
    								new Vector3f(0f, rand.nextFloat()* 360, 0f),	//start rotation
    								new Vector3f(0,0,0)));							//rotation origin
    	}
    	
    	
    	
    	
    	
    	//Generate Small Mushrooms
    		
    	for(int x = 0; x < mushroomFieldWidth; x++) {
        	for(int z = 0; z < mushroomFieldHeigth; z++) {
	        	Mushroom m = new Mushroom(new Vector3f((x) - mushroomFieldWidth / 2,
	        										   0,
	        										   (z) - mushroomFieldHeigth / 2));
	        	m.setTexture(smallMushroomTexture);
	        	renderInstances.add(m);
        	}
        }
    	
    }
        
    /**
     * {@inheritDoc}<br>
     * This method will check for user input controlling the camera, and interactions within the scene
     */
    @Override
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
	        				checkMushroomLOD();
	        				generateRenderInstances();
	        				
    		        	}
    		        } else if(Keyboard.getEventKey() == Keyboard.KEY_MINUS && !Keyboard.getEventKeyState())
    		        {
    		        	if(mushroomFieldWidth * mushroomFieldHeigth > 0) {
    		        		mushroomFieldWidth--;
            				mushroomFieldHeigth--;
            				checkMushroomLOD();
            				generateRenderInstances();
            				
    		        	}
    		        	
    		        }
    		        
    		    }
    		}
    		
    		
    	}
    	
    	
    	
    	
    }
    
    /**
     * This method will check if a quarter of the max number of mushrooms ({@link maxMushroomCount}) are being drawn<br>
     * If over a quarter are drawn, then this will switch out to a lower level of detail model<br>
     * <br>
     * sMushroom should have roughly a quarter of the number of faces as mMushroom, so 4 times as many can be drawn<br>
     */
    private void checkMushroomLOD() {
    	if(mushroomFieldWidth * mushroomFieldHeigth > maxMushroomCount / 4) {
    		Mushroom.displayListIndex = displayLists.get("sMushroom");
    	} else {
    		Mushroom.displayListIndex = displayLists.get("mMushroom");
    	}
    }
        
    @Override
    protected void updateScene()
    {
    	Timer.update();
    	
    	for(RenderInstance r : renderInstances) {
        	r.update(getAnimationScale());
        }
    }
    
    /**
     * Renders every RenderInsatnce in { @code MainScene.renderInstance}
     */
    @Override
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
    
    @Override
    protected void setSceneCamera()
    {
        super.setSceneCamera();
       
        
        //TODO: Consider using gluLookAt for camera translation and rotation rather than applyCameraTransform()
        //This does however require a rework of the camera rotation since gluLookAt wants a direction vector rather than a rotation angle
        //This could be achieved with a rotation matrix however current solution of rotating the entire world is suitable.
        
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
     * Applies the camera translation and rotation to an object<br>
     * <br>
	 * This method should be called between<br>
	 * {@code GL11.glPushMatrix} and {@code GL11.glPopMatrix} method calls during the rendering of every model
	 * that should move relative to the camera
	 * */
    private void applyCameraTransform() {
    		GL11.glRotatef(cameraRotation.getX(), 1, 0, 0);
    		GL11.glRotatef(cameraRotation.getY(), 0, 1, 0);
    		GL11.glRotatef(cameraRotation.getZ(), 0, 0, 1);
    		GL11.glTranslatef(-cameraTranslation.getX(), -cameraTranslation.getY(), -cameraTranslation.getZ());    	
    }

}

