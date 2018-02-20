package graphicInterface;

import creation.DBCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private Stage window;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		DBCreation.createDB();
		this.window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
		this.window.initStyle(StageStyle.UNDECORATED);
		this.window.setResizable(false);
		this.window.setScene(new Scene(root));
		this.window.show();
	}

}
