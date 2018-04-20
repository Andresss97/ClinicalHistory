package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import creation.QuerysDelete;
import creation.QuerysSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pojos.Doctor;
import pojos.Patient;
import pojos.Person;

public class ControllerAdminView implements Initializable{

    @FXML
    private BorderPane cBar;

    @FXML
    private MenuBar bar;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem mProfile;

    @FXML
    private MenuItem signOff;

    @FXML
    private BorderPane cContainer;

    @FXML
    private ListView<Person> list;

    @FXML
    private Button modify;

    @FXML
    private Button delete;

    @FXML
    private Button create;

    @FXML
    private ImageView iButton;

    @FXML
    private ComboBox<?> orderBy;
    
    @FXML
    private JFXTextField search;
    
    private Doctor doctor;
    
    private Patient patient;
    
    private ArrayList<Person> accounts;
    
    @FXML
    void onClickCreate(ActionEvent event) throws IOException {
    	BorderPane root = FXMLLoader.load(getClass().getResource("CreateDoctorView.fxml"));
		cContainer.getChildren().clear();
		root.prefHeightProperty().bind(cContainer.heightProperty());
		root.prefWidthProperty().bind(cContainer.widthProperty());
		cContainer.setCenter(root);
    }

    @FXML
    void onClickDelete(ActionEvent event) throws IOException {
    	QuerysDelete qs = new QuerysDelete();
    	int id = 0;
    	
    	if(list.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("You must select an account");
        	alert.setTitle("Warning delete");
        	alert.setHeaderText("Delete information");
        	alert.show();
        	return;
    	}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Are you sure you want to delete this account?");
    	alert.setTitle("Information");
    	alert.setHeaderText("Delete account");
    	Optional<ButtonType> result = alert.showAndWait();

    	if(result.get() == ButtonType.OK) {
    		if(list.getSelectionModel().getSelectedItem() instanceof Doctor) {
    			Doctor doctor = (Doctor) list.getSelectionModel().getSelectedItem();
    			try {
					qs.deleteDoctorAccount(doctor);
					id = doctor.getID() + 1;
					qs.deleteUser(id);
					this.accounts.remove(doctor);
			    	this.refreshList();
				} catch (SQLException e) {

				}
    		}
    		else if(list.getSelectionModel().getSelectedItem() instanceof Patient){
    			Patient patient = (Patient) list.getSelectionModel().getSelectedItem();
    			try {
    				id = patient.getID() + 1;
					qs.deleteUser(id);
					qs.deletePatientAccount(patient);
					this.accounts.remove(patient);
			    	this.refreshList();
				} catch (SQLException e) {

				}
    		}
    	}
    }

    @FXML
    void onClickEditProfile(ActionEvent event) throws IOException {
    	GridPane root = FXMLLoader.load(getClass().getResource("EditAdminAccount.fxml"));
    	cContainer.getChildren().clear();
    	root.prefHeightProperty().bind(cContainer.heightProperty());
    	root.prefWidthProperty().bind(cContainer.widthProperty());
    	cContainer.setCenter(root);
    }

    @FXML
    void onClickHomePage(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickModify(ActionEvent event) {
    	if(list.getSelectionModel().getSelectedItem() instanceof Doctor) {
    		this.doctor = (Doctor) list.getSelectionModel().getSelectedItem();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDoctorsAccount.fxml"));
    		BorderPane root = null;
			try {
				root = loader.load();
				ControllerUpdateDoctors controller = loader.<ControllerUpdateDoctors>getController();
				controller.initComponents(doctor);
				cContainer.getChildren().clear();
				root.prefHeightProperty().bind(cContainer.heightProperty());
				root.prefWidthProperty().bind(cContainer.widthProperty());
				cContainer.setCenter(root);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    	else if(list.getSelectionModel().getSelectedItem() instanceof Patient) {
    		patient = (Patient) list.getSelectionModel().getSelectedItem();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePatientAdmin.fxml"));
    		BorderPane root = null;
    		try {
				root = loader.load();
				cContainer.getChildren().clear();
				ControllerUpdatePatientAdmin controller = loader.<ControllerUpdatePatientAdmin>getController();
				controller.initComponents(patient);
				root.prefHeightProperty().bind(cContainer.heightProperty());
				root.prefWidthProperty().bind(cContainer.widthProperty());
				cContainer.setCenter(root);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("You must select an account");
        	alert.setTitle("Warning Update");
        	alert.setHeaderText("Update information");
        	alert.show();
        	return;
    	}
     }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @FXML
    void onClickOrderBy(ActionEvent event) {
    	if(orderBy.getSelectionModel().getSelectedItem().equals("Alphabetically")) {
    		ObservableList s = FXCollections.observableArrayList(accounts);
        	SortedList so = new SortedList(s);
        	
        	so.setComparator(new Comparator<Person>() {
    			@Override
    			public int compare(Person arg0, Person arg1) {
    				return arg0.getName().compareToIgnoreCase(arg1.getName());
    			}
        	});
        	
        	list.setItems(so);
    	}
    	else if(orderBy.getSelectionModel().getSelectedItem().equals("User type")){
    		ObservableList s = FXCollections.observableArrayList(accounts);
        	SortedList so = new SortedList(s);
        	
        	so.setComparator(new Comparator<Person>() {
    			@Override
    			public int compare(Person arg0, Person arg1) {
    				if(arg0 instanceof Patient) {
    					return 1;
    				}
    				else if(arg1 instanceof Patient) {
    					return 1;
    				}
    				else if(arg0 instanceof Doctor) {
    					return -1;
    				}
    				else if(arg1 instanceof Doctor) {
    					return -1;
    				}
    				else {
    					return 0;
    				}
    			}
        	});
        	
        	list.setItems(so);
    	}
    }

    @FXML
    void onClickSignOff(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
    	Scene scene = cBar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }
    
    
	@FXML
    void onClickedSearch(ActionEvent event) {
		
    }
    
    private void refreshList() {
    	this.list.getItems().clear();
    	this.list.getItems().addAll(accounts);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "User type");
		QuerysSelect qs = new QuerysSelect();
		orderBy.setItems(list);
		
		this.accounts = new ArrayList<>();
		
		try {
			this.accounts.addAll(qs.selectDoctorsAccount());
			this.accounts.addAll(qs.selectPatientsAccounts());
			this.list.getItems().addAll(accounts);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

