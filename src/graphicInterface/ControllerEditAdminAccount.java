package graphicInterface;

import java.io.IOException;
import java.sql.SQLException;

import creation.QuerysUpdate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerEditAdminAccount {

    @FXML
    private GridPane conatiner;

    @FXML
    private TextField user;

    @FXML
    private Button update;

    @FXML
    private TextField password;

    @FXML
    void onClickUpdate(ActionEvent event) throws IOException {
    	QuerysUpdate query = new QuerysUpdate();
    	try {
			query.updateAdmin(user.getText(), password.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = update.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

}