package graphicInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private Stage window;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		this.window.setTitle("Babylon Studio");
		this.window.setResizable(false);
		this.window.setScene(new Scene(root));
		this.window.show();
	}

}
