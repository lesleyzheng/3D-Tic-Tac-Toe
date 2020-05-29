package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/*
 * Referenced: http://www.learningaboutelectronics.com/Articles/How-to-create-a-pop-up-window-in-JavaFX.php
 * to understand how popups work and used parts of their example code
 */

//Class for creating a popup window once a game has terminated
public class createPopup {   
	
	protected static void display(){
		
		//Sets up a new stage to contain all the contents within the popup window
		Stage popupwindow = new Stage();
      
		//Blocks access to other stages when this popup window is opened
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		
		//Sets the title of the popup window
		popupwindow.setTitle("Congratulations!");
		
		//Creates the text shown in the popup window based on the results
		Label label1 = new Label();
		if(Faces.GameOver==1) {
			label1.setText("Player 1 has won!");
		} else if(Faces.GameOver==2) {
			label1.setText("Player 2 has won!");
		} else {
			label1.setText("It is a draw!");
		}
      
		//Creates the button for closing the popup window
		Button button1 = new Button("Close");
		button1.setOnAction((new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				popupwindow.close();
			}
		}));

		//Creates a vertical box layout to stack all nodes within the scene vertically
		VBox layout= new VBox(10);
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
      
		//Creates the scene for the popup window
		Scene scene = new Scene(layout, 500, 150);
		popupwindow.setScene(scene);   
		popupwindow.showAndWait();
	}
	
}