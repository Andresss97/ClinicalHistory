package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ControllerSplashScreen implements Initializable{
	@FXML
	private AnchorPane panel;
	
	private class SplashScreen extends Thread{
		
		@Override
		public void run() {
			try {
				Thread.sleep(4000);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Parent window = null;
						try {
							window = FXMLLoader.load(getClass().getResource("MainView.fxml"));
							Scene scene = new Scene(window);
							Stage stage = new Stage();
							stage.setTitle("Babylon Studio");
							stage.setResizable(true);
							stage.setScene(scene);
							stage.getIcons().add(new Image(".//images//Logo.jpg"));
							stage.setMaximized(true);
							stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
								
								@Override
								public void handle(WindowEvent e) {
									Main.conector.killConnection();
									Main.jpaConector.killConnection();
									Platform.exit();
									System.exit(0);
								}
							});
							
							stage.show();
							
							
							panel.getScene().getWindow().hide();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
						
					}
				});
			}
			catch(Exception ex) {
				
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new SplashScreen().start();
	}
	
}
