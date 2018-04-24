package graphicInterface;

import java.io.File;
import java.io.IOException;

import pojos.*;
import creation.Conector;
import creation.ConnInterface;
import creation.DBCreation;
import creation.QuerysInsert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa.JPAConnector;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	private Stage window;
	public static ConnInterface conector;
	public static ConnInterface jpaConector;
	public static Patient patient;
	public static Doctor doctor;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		QuerysInsert qi = new QuerysInsert();
		
		this.conector = new Conector();
		this.jpaConector = new JPAConnector();
		File url = new File(".//Database//DBproject.db");
		
		if(!url.exists()) {
			this.conector.connect();
			DBCreation.createDB(this.conector);
			this.jpaConector.connect();
		}
		else {
			this.conector.connect();
			this.jpaConector.connect();
		}
		
		this.window = primaryStage;
		this.window.getIcons().add(new Image(".//images//Logo.jpg"));
		Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
		this.window.initStyle(StageStyle.UNDECORATED);
		this.window.setResizable(false);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
}
