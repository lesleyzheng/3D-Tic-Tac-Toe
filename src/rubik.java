package src;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage; 
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler; 
import javafx.util.Duration; 
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import java.util.Scanner;
//interface and class respectively used as one of the data structures

public class rubik extends Application{
	
	//Sets the center of the scene in the x, y, and z directions respectively.
	protected static double Xm = 600;
	protected static double Ym = 410;
	protected static double Zm = 1600;
	
	//Sets the time (seconds) for one cube rotation
	private double spinTime = 0.5;

	//Introduces the class NaughtsAndCrossesInnerCube within the class src.rubik, because two parent classes are required (and Java does not allow extension of multiple classes)
	public static class NaughtsAndCrossesInnerCube extends Faces{	
		
		//Initializes FaceNumber and BoardLocation, which determines the location of a move
		public static int FaceNumber;
		public static int BoardLocation;
		
		//Initiates the cube.
		public NaughtsAndCrossesInnerCube(){
			for (int i=0; i<6; i++){
				FaceInitiation((i%2)*180, i);
			}
		}
		
		//Method that allows a valid Facenumber to be input (for use without the graphics ie debugging)
		protected static void InputFaceNumber(){
			
			boolean CannotUse=true;
			
			//Ensures that the Facenumber entered is an integer
			while (CannotUse==true){
				
				Scanner Input1=new Scanner(System.in);
				System.out.println("Enter the number face in which you would like to place your tile");
				
				if (Input1.hasNextInt()){
					
					FaceNumber=Input1.nextInt();
					
					for (int i=0; i<7; i++){	
						if (FaceNumber==i){			
							CannotUse=false;	
							if (FaceNumber==6){
								FaceNumber=0;
							}	
						}
					}
					//Ensure FaceNumber entered is a value that corresponds to the facenumber
					if (CannotUse==true){
						System.out.println("Try again, the face number you entered is out of range");
					}
	
				}
				else{
					System.out.println("Try again, you did not enter a number");
				}
			}
	
		}

		protected static void InputPlaceNumber(){
			
			boolean CannotUse=true;
			
			//Ensures that the place number entered is an integer (For use without the graphics ie debugging)
			while (CannotUse==true){
				
				Scanner Input1 = new Scanner(System.in);
				System.out.println("Enter the position number in which you would like to place your tile");
				
				if (Input1.hasNextInt()){
					
					BoardLocation=Input1.nextInt();
						
					for (int i=0; i<10; i++){
							if (FaceNumber==i){
								CannotUse=false;
								if (FaceNumber==6){
									FaceNumber=0;
							}
						}
					}
				
					//Ensures that the board space entered is within the valid values
					if (CannotUse==true){
						System.out.println("Try again, the place number you entered is out of range");
					}
				} else{
					System.out.println("Try again, you did not enter a number");
				}
				
				Input1.close();
			}
		}
			
		protected static boolean CheckUnused(int BoardLocation, int FaceNumber){
			//Method used to ensure the same board location is not used twice.
			
			boolean CannotUse=true;
			CannotUse=false;
			MyGenericDS<Character> Temporary=new MyGenericDS<Character>(Used);
			
			for (int i=0; i<Used.length(); i++){
				if ((char)('0'+BoardLocation)==Temporary.peek() && (char)('0'+FaceNumber)==Temporary.peekSecondary()){
					CannotUse=true;
				}
				Temporary.pop();	
			}
			if (CannotUse==true){
				System.out.println("Try again, that move has already been taken");
			}
			return CannotUse;
			
		}
		
	}
	
	//Built-in method within Application class that creates the graphics of the game
	public void start(Stage stage){
		
		//Create the cubes and their respective rotation objects
		createCubes C = new createCubes();
		createCubes.lrt(); //left-right rotation objects
		createCubes.urt(); //up-down rotation objects
		
		//Decorations
		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setOffsetX(4);
		innerShadow.setOffsetY(4);
		innerShadow.setColor(Color.BLACK);
		
		Circle circle = new Circle(Xm/5+25, Ym+10, 100, Color.WHITE);
		
		Text title = new Text(200, Ym*2 - 10, "3D TIC-TAC-TOE");
		title.setEffect(innerShadow);
		title.setFill(Color.WHITE);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 100));
		
		//Create Control Buttons
		Button Lbutton = new Button("Left");
		Lbutton.setLayoutX(Xm/5-50);
		Lbutton.setLayoutY(Ym);
						
		Button Rbutton = new Button("Right");
		Rbutton.setLayoutX(Xm/5+50);
		Rbutton.setLayoutY(Ym);
						
		Button Ubutton = new Button("Up");
		Ubutton.setLayoutX(Xm/5+5);
		Ubutton.setLayoutY(Ym-50);
						
		Button Dbutton = new Button("Down");
		Dbutton.setLayoutX(Xm/5);
		Dbutton.setLayoutY(Ym+50);
		
		//Sets up actions corresponding to each control button
		/*
		 * Referenced: 1) https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm; 
		 * 2) http://www.java2s.com/Tutorials/Java/JavaFX/1010__JavaFX_Timeline_Animation.htm;
		 * but made significant edits
		 */
		Lbutton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 Timeline timeline = new Timeline();
	        	 for(int i = 0; i<26; i++) {
	        		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(spinTime), new KeyValue(createCubes.cubelrt.get(i).angleProperty(),createCubes.cubelrt.get(i).getAngle()+90)));
	        	 }	
	        	 timeline.play();
	         } 
		}));
		
		Rbutton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 Timeline timeline = new Timeline();
	        	 for(int i = 0; i<26; i++) {
	        		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(spinTime), new KeyValue(createCubes.cubelrt.get(i).angleProperty(),createCubes.cubelrt.get(i).getAngle()-90)));
	        	 }	
	        	 timeline.play();
	         } 
		}));
		
		Ubutton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 Timeline timeline = new Timeline();
	        	 for(int i = 0; i<26; i++) {
	        		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(spinTime), new KeyValue(createCubes.cubeurt.get(i).angleProperty(),createCubes.cubeurt.get(i).getAngle()-90)));
	        	 }	
	        	 timeline.play();
	         } 
		}));
		
		Dbutton.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
	        	 Timeline timeline = new Timeline();
	        	 for(int i = 0; i<26; i++) {
	        		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(spinTime), new KeyValue(createCubes.cubeurt.get(i).angleProperty(),createCubes.cubeurt.get(i).getAngle()+90)));
	        	 }	
	        	 timeline.play();
	         } 
		}));
		
		//Creates code for user interaction - so each click corresponds to a user move; assumes Player 1 is X.
		for(int i = 0; i<26; i++) {
			
			final int i0=i;
			//Needed for some reason: https://stackoverflow.com/questions/43849443/why-am-i-getting-local-variables-referenced-from-an-inner-class-must-be-final
			Box temp = createCubes.cubes.get(i0);
			
			createCubes.cubes.get(i0).setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
						if (i0<9){
							NaughtsAndCrossesInnerCube.FaceNumber=1;
							NaughtsAndCrossesInnerCube.BoardLocation=i0+1;
						}
						else if (i0<12 && i0>8){
							NaughtsAndCrossesInnerCube.FaceNumber=3;
							NaughtsAndCrossesInnerCube.BoardLocation=(35-(3*i0));
						}
						else if (i0==12){
							NaughtsAndCrossesInnerCube.FaceNumber=2;
							NaughtsAndCrossesInnerCube.BoardLocation=5;
						}
						else if (i0==13){
							NaughtsAndCrossesInnerCube.FaceNumber=5;
							NaughtsAndCrossesInnerCube.BoardLocation=5;
						}
						else if (i0<17 && i0>13){
							NaughtsAndCrossesInnerCube.FaceNumber=0;
							NaughtsAndCrossesInnerCube.BoardLocation=20-i0;
						}
						else if (i0>16){
							NaughtsAndCrossesInnerCube.FaceNumber=4;
							if (i0<20){
								NaughtsAndCrossesInnerCube.BoardLocation=(3*i0-50);
							}
							else if(i0<23){
								NaughtsAndCrossesInnerCube.BoardLocation=(3*i0-58);
							}
							else{
								NaughtsAndCrossesInnerCube.BoardLocation=(3*i0-66);
							}
						}
					//Checks which cube has been clicked and changes the corresponding face numbers in the face class
						boolean Used=NaughtsAndCrossesInnerCube.CheckUnused(NaughtsAndCrossesInnerCube.BoardLocation, NaughtsAndCrossesInnerCube.FaceNumber);
					//Checks whether the cube clicked has already been altered
						if (Used==true){
							
						}
						else{
							if(NaughtsAndCrossesInnerCube.TurnCount%2 == 0) {
								createCubes.Cross(temp);
								NaughtsAndCrossesInnerCube.Move='X';
							}
							else {
								createCubes.Circle(temp);
								NaughtsAndCrossesInnerCube.Move='O';
							}
						//Checks to see which players turn it is
							NaughtsAndCrossesInnerCube.UpdateBoard(NaughtsAndCrossesInnerCube.BoardLocation, NaughtsAndCrossesInnerCube.FaceNumber);
						//Updates the board according to the new play
							NaughtsAndCrossesInnerCube.TurnCount=NaughtsAndCrossesInnerCube.TurnCount+1;
						//Increases the turncount to reflect the player has moved and it is now the second players turn
							NaughtsAndCrossesInnerCube.CheckGameStatus();
						//Checks whether the game is over or not
						}
						
				}//handle method
			}//EventHandler	
		); //setOnMouseClicked
		}//for loop
		
		/*
		 * Referenced: https://www.tutorialspoint.com//javafx/javafx_3d_shapes.htm
		 * for the set up of the camera
		 * 
		 * Referenced: https://www.tutorialspoint.com/javafx/javafx_application.htm
		 * for the set up of the group, scene and stage structure
		 */
		
		//Camera
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(0);
		camera.setTranslateY(0);
		camera.setTranslateZ(0);
				
		//Group: Organizes all the nodes (graphical objects) that appear under this one parent node
		Group root = new Group(circle, title, Lbutton, Rbutton, Ubutton, Dbutton);
		for(int i = 0; i<26; i++) {
			root.getChildren().add(createCubes.cubes.get(i));
		}
				
		//Scene: Collects all the nodes and sets up the scene of game
		Scene scene = new Scene(root, Xm*2, Ym*2, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.DIMGRAY);
		scene.setCamera(camera);
		
		//Stage: Contains all objects within the class (including all scenes), and displays it
		stage.setScene(scene);
		stage.setTitle("3D Tic-Tac-Toe"); //sets the title of the stage
		stage.show();
		
	}
	
	public static void main(String args[]) {
		
		NaughtsAndCrossesInnerCube Cube = new NaughtsAndCrossesInnerCube();
	//Creates a new instance of the cube
		NaughtsAndCrossesInnerCube.PrintBoard();
	//Prints the new cube as a string in the cmd line
		
		//This method internally calls to the start() method and hence starts the graphical components of the game
		launch(args); 
		
	}
	
}
