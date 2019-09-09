package ticTacToe;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThreeTTT extends Application {
	
	public void start(Stage primaryStage) {
	
		Group root = new Group();
		
		Scene scene = new Scene(root, 300, 300);
		
		final PhongMaterial greyMaterial = new PhongMaterial();
	    greyMaterial.setDiffuseColor(Color.DARKGREY);
	    greyMaterial.setSpecularColor(Color.GREY);

	    Box red = new Box(400, 400, 400);
	    red.setMaterial(greyMaterial);
		
		root.getChildren().add(red);
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
    public static void main(String[] args) {
        launch();
    }
}
