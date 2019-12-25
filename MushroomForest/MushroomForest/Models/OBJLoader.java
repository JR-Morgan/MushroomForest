package MushroomForest.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import GraphicsLab.Vertex;
import MushroomForest.Utill.Face;

/**
 * This class is OBJ loader that can parse OBJ data into a model
 * This class is not a full OBJ loader and expects a OBJ file with Vertexes, Texture Coordinates, and Faces in (v/vt) format
 * @author Jedd Morgan
 *
 */
public final class OBJLoader
{
	private OBJLoader() { }
	
	/**
	 * Parses a Model from an OBJ file
	 * @param filePath - File Path for the OBJ file
	 * @return Model containing v, vt, and f data
	 */
	public static Model ParseFromFile(String filePath)
	{
		List<String> lines = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
            	lines.add(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage() + " File: \"" + filePath + "\"");
        }
		
		
		return ParseModel(lines.toArray(new String[lines.size()]));
		
	}
	
	private static Model ParseModel(String[] lines)
	{
		List<Vertex> vertexes = new ArrayList<Vertex>();;
		List<Vector2f> textureCoordinates = new ArrayList<Vector2f>();
		List<Face> faces = new ArrayList<Face>();
		
		
		for(String line : lines)
		{
			String[] parameters = line.split(" ");
			
			switch(parameters[0]) {
			case "v":
				vertexes.add(new Vertex(Float.parseFloat(parameters[1]),
									    Float.parseFloat(parameters[2]),
						                Float.parseFloat(parameters[3])));
				break;
			case "vt":
				textureCoordinates.add(new Vector2f(Float.parseFloat(parameters[1]),
											        Float.parseFloat(parameters[2])));
				break;
			case "f":
				List<Integer> vertexIndecies = new ArrayList<Integer>();
				List<Integer> textureCoordinateIndecies = new ArrayList<Integer>();
				
				for(int i = 1; i < parameters.length; i++)
				{
					String[] arr = parameters[i].split("/");
					vertexIndecies			 .add(Integer.parseInt(arr[0]));
					textureCoordinateIndecies.add(Integer.parseInt(arr[1]));
				}
				faces.add(new Face(vertexIndecies.toArray(new Integer[vertexIndecies.size()]),
						  	       vertexIndecies.toArray(new Integer[vertexIndecies.size()])));
				break;
			}
		}
		
		
		return new Model(vertexes.toArray(new Vertex[vertexes.size()]),
				  textureCoordinates.toArray(new Vector2f[textureCoordinates.size()]),
				  faces.toArray(new Face[faces.size()]));
		
		
	}
}