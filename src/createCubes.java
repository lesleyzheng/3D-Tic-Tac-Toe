package src;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

//This class creates the 3D cube objects for the 26 cubes, and their corresponding rotation objects
public class createCubes {
	
	//The following three lists contain the coordinates of the mid-points for all 26 cubes; 
	//their positions are dependent on the center of the screen and hence refers to sXm, xYm and sZm
	private static double[] sXm = new double[] {- 323, 0, + 323, - 323, 0, + 323, - 323, 0, + 323,
			- 323, 0, + 323, - 323, + 323, - 323, 0, + 323,
			- 323, 0, + 323, - 323, 0, + 323, - 323, 0, + 323
	};
	private static double [] sYm = new double[] {0 + 323, 0 + 323, 0 + 323, 0, 0, 0, 0 - 323, 0 - 323, 0 - 323,
			0 + 323, 0 + 323, 0 + 323, 0, 0, 0 - 323, 0 - 323, 0 - 323,
			0 + 323, 0 + 323, 0 + 323, 0, 0, 0, 0 - 323, 0 - 323, 0 - 323
	};
	private static double[] sZm = new double[] {0 - 323, 0 - 323, 0 - 323, 0 - 323, 0 - 323, 0 - 323, 0 - 323, 0 - 323, 0 - 323,
			0, 0, 0, 0, 0, 0, 0, 0,
			0 + 323, 0 + 323, 0 + 323, 0 + 323, 0 + 323, 0 + 323, 0 + 323, 0 + 323, 0 + 323
	};
	
	//ArrayList that collects the 26 3D cubes
	static ArrayList<Box> cubes = new ArrayList<Box>();
	
	//ArrayLists that collects the rotation objects corresponding to each cube for both 
	//the left-right direction (hence revolves around the y-axis),
	//and the up-down direction (hence revolves around the x-axis)
	static ArrayList<Rotate> cubelrt = new ArrayList<Rotate>();
	static ArrayList<Rotate> cubeurt = new ArrayList<Rotate>();
	
	//Constructor: creates the cubes
	public createCubes() {
		
		for(int i = 0; i<26; i++) {
			
			//Sets up the size (width, height and depth) of each cube
			cubes.add(new Box(300,300,300)); 
			
			//Sets up the material (color) of each cube)
			cubes.get(i).setMaterial(new PhongMaterial(Color.WHITE)); 
			
			//Translates the cubes to their respective positions
			Translate translate = new Translate(rubik.Xm + sXm[i],rubik.Ym + sYm[i],rubik.Zm + sZm[i]);
			cubes.get(i).getTransforms().add(translate);
		}
	}
	
	/*
	 * Referenced: https://www.tutorialspoint.com/javafx/javafx_transformations.htm
	 * when creating the rotation objects
	 */
	
	//Creates the left-right rotation objects for each cube
	static protected void lrt() {
		for(int i = 0; i<26; i++) {
			
			//Redefines the pivot point to the center of the src.rubik's cube,
			//rather than the center of the cube itself
			cubelrt.add(new Rotate(0, -1*sXm[i], -1*sYm[i], -1*sZm[i],Rotate.Y_AXIS));
			cubes.get(i).getTransforms().add(cubelrt.get(i));
		}
	}
	
	//Creates the up-down rotation objects for each cube
	static protected void urt() {
		
		for(int i = 0; i<26; i++) {
			//Redefines the pivot point to the center of the src.rubik's cube,
			//rather than the center of the cube itself
			cubeurt.add(new Rotate(0, -1*sXm[i], -1*sYm[i], -1*sZm[i],Rotate.X_AXIS));
			cubes.get(i).getTransforms().add(cubeurt.get(i));
		}
	}
	
	//Method that changes the face of a cube to a cross; evoked when player 1 places his/her move
		static protected void Cross(Box box) {
			PhongMaterial cross = new PhongMaterial();
			cross.setDiffuseMap(new Image("https://images.landofnod.com/is/image/LandOfNod/Letter_Giant_Enough_X_236462_LL/$web_setitem$/130831065659/not-giant-enough-letter-x.jpg"));
			box.setMaterial(cross);
		}

	//Method that changes the face of a cube to a circle; evoked when player 2 places his/her move
	static protected void Circle(Box box) {
		PhongMaterial circle = new PhongMaterial();
		circle.setDiffuseMap(new Image("http://clipart-library.com/img1/689328.gif"));
		box.setMaterial(circle);
	}
}
