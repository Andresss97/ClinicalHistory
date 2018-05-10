
package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import creation.QuerysInsert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jpa.CreateJPA;
import jpa.DeleteJPA;
import jpa.UpdateJPA;
import pojos.Allergies;
import pojos.Appointment;
import pojos.ClinicalHistory;
import pojos.ClinicalHistory.ADDICTIONS;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Illness;
import pojos.Patient;
import pojos.Surgeries;
import pojos.Treatment;
import pojos.Treatment.typeTreatment;
import pojos.Vaccine;
import pojos.Vaccine.typeVaccine;

public class ControllerClinicalRecord implements Initializable {

    @FXML
    private BorderPane container;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField gender;

    @FXML
    private TextField mail;

    @FXML
    private TextField mPhone;

    @FXML
    private TextField hPhone;

    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private DatePicker dBirth;

    @FXML
    private ImageView image;

    @FXML
    private JFXComboBox<String> alcohol;

    @FXML
    private JFXComboBox<String> drugs;

    @FXML
    private JFXComboBox<String> others;

    @FXML
    private JFXComboBox<String> bGroup;

    @FXML
    private JFXComboBox<String> mInsCompany;

    @FXML
    private JFXButton modify;

    @FXML
    private JFXButton set;

    @FXML
    private ListView<Appointment> list;

    @FXML
    private TableView<Vaccine> tableVaccines;

    @FXML
    private TableColumn<Vaccine, typeVaccine> vaccineName;

    @FXML
    private TableColumn<Vaccine, Date> vaccineDate;

    @FXML
    private TableColumn<Vaccine, String> vaccineObservations;

    @FXML
    private JFXComboBox<String> vaccineAddName;

    @FXML
    private DatePicker vaccinesAddDate;

    @FXML
    private JFXTextField vaccinessAddObservations;

    @FXML
    private JFXButton vaccinesAdd;

    @FXML
    private TableView<Surgeries> tableSurgeries;

    @FXML
    private TableColumn<Surgeries, String> surgeriesType;

    @FXML
    private TableColumn<Surgeries, Date> surgeriesDate;

    @FXML
    private JFXTextField typeSurgeries;

    @FXML
    private DatePicker dateOfSurgery;

    @FXML
    private JFXButton addSurgery;
    
    @FXML
    private TableView<Illness> tableIllness;
    
    @FXML
    private TableColumn<Illness, String> illnessesType;

    @FXML
    private TableColumn<Illness, Date> dateOfIllness;

    @FXML
    private TableColumn<Illness, String> illnessName;

    @FXML
    private TableColumn<Illness, String> illnessDescription;

    @FXML
    private JFXComboBox<String> typeIllness;

    @FXML
    private DatePicker dateIllness;

    @FXML
    private JFXTextField nameIllness;

    @FXML
    private JFXTextField descripIllness;

    @FXML
    private JFXButton addIllness;
    
    @FXML
    private TableView<Allergies> tableAllergies;

    @FXML
    private TableColumn<Allergies, String> columnTypeAllergies;

    @FXML
    private TableColumn<Allergies, String> columnObservationAllergies;

    @FXML
    private JFXTextField typeAllergies;

    @FXML
    private JFXTextField observationsAllergies;

    @FXML
    private JFXButton addAllergies;

    @FXML
    private JFXButton pdf;

    @FXML
    private JFXButton print;
    
    private Patient patient;
    
    private ObservableList<String> list4;
    
    @FXML
    void onClickAddAllergies(ActionEvent event) {
    	CreateJPA create = new CreateJPA();
    	Allergies allergy = new Allergies();
    	
    	allergy.setType(typeAllergies.getText());
    	allergy.setObservations(observationsAllergies.getText());
    	allergy.setPatient(this.patient);
    	
    	create.createAllergy(allergy);
    	
    	typeAllergies.clear();
    	observationsAllergies.clear();
    	
    	this.tableAllergies.getItems().add(allergy);
    }
    
    @FXML
    void onClickLeftIllness(MouseEvent event) {
		if (event.getButton().equals(MouseButton.SECONDARY)) {
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			MenuItem modify = new MenuItem("Modify");
			MenuItem addTreatment = new MenuItem("Add Treatment");
			MenuItem modifyTreatment = new MenuItem("Modify Treatment");
			menu.getItems().addAll(delete, modify, addTreatment, modifyTreatment);

			this.tableIllness.setContextMenu(menu);

			delete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableIllness.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must selectan illness");
						alert.setTitle("Warning");
						alert.show();
						return;
					}

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to delete this illness?");
					alert.setTitle("Information");
					alert.setHeaderText("Delete illness");
					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.OK) {
						DeleteJPA delete = new DeleteJPA();
						Illness illness = tableIllness.getSelectionModel().getSelectedItem();
						if(illness.getTreatment() != null) {
							delete.deleteTreatment(illness.getTreatment());
						}
						delete.deleteIllness(illness);
						patient.getIllnesses().remove(illness);
						tableIllness.getItems().remove(illness);

						tableIllness.autosize();
					}
				}

			});
			
			modify.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableIllness.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Modify information");
						alert.setContentText("You must select an illness");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateIllness.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerUpdateIllness controller = loader.<ControllerUpdateIllness>getController();
						controller.initComponent(tableIllness.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshIllness();
				}
			});
			
			addTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableIllness.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Add treatment information");
						alert.setContentText("You must select an illness");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					if (tableIllness.getSelectionModel().getSelectedItem().getTreatment() != null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Treatment information");
						alert.setContentText("There is an existing treatment for this illness");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initIllness(tableIllness.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshIllness();
				}
			});
			
			modifyTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableIllness.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Update information");
						alert.setContentText("You must select an illness");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initIllness(tableIllness.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshIllness();
				}
			});
		}
    }

    @FXML
    void onClickLeftSurgeries(MouseEvent event) {
		if (event.getButton().equals(MouseButton.SECONDARY)) {
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			MenuItem modify = new MenuItem("Modify");
			MenuItem addTreatment = new MenuItem("Add Treatment");
			MenuItem modifyTreatment = new MenuItem("Modify Treatment");
			menu.getItems().addAll(delete, modify, addTreatment, modifyTreatment);

			this.tableSurgeries.setContextMenu(menu);

			delete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableSurgeries.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select a surgery");
						alert.setTitle("Warning");
						alert.show();
						return;
					}

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to delete this surgery?");
					alert.setTitle("Information");
					alert.setHeaderText("Delete surgery");
					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.OK) {
						DeleteJPA delete = new DeleteJPA();
						Surgeries surgery = tableSurgeries.getSelectionModel().getSelectedItem();
						if(surgery.getTreatment() != null) {
							delete.deleteTreatment(surgery.getTreatment());
						}
						delete.deleteSurgery(surgery);
						tableSurgeries.getItems().remove(surgery);
						patient.getSurgeries().remove(surgery);
						tableSurgeries.autosize();
					}
				}
			});
			
			modify.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableSurgeries.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Modify information");
						alert.setContentText("You must select a surgery");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateSurgery.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerUpdateSurgery controller = loader.<ControllerUpdateSurgery>getController();
						controller.initComponent(tableSurgeries.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshSurgeries();
				}
			});
			
			addTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableSurgeries.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Add treatment information");
						alert.setContentText("You must select a surgery");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					if (tableSurgeries.getSelectionModel().getSelectedItem().getTreatment() != null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Treatment information");
						alert.setContentText("There is an existing treatment for this surgery");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initSurgery(tableSurgeries.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshSurgeries();
				}
			});
			
			modifyTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableSurgeries.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select a surgery");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initSurgery(tableSurgeries.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshSurgeries();
				}
			});
		}
    }
    
    @FXML
    void onLeftClickAllergies(MouseEvent event) {
		if (event.getButton().equals(MouseButton.SECONDARY)) {
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			MenuItem modify = new MenuItem("Modify");
			MenuItem addTreatment = new MenuItem("Add Treatment");
			MenuItem modifyTreatment = new MenuItem("Modify Treatment");
			menu.getItems().addAll(delete, modify, addTreatment, modifyTreatment);

			this.tableAllergies.setContextMenu(menu);

			delete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableAllergies.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select an allergy");
						alert.setTitle("Warning");
						alert.show();
						return;
					}

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to delete this allergy?");
					alert.setTitle("Information");
					alert.setHeaderText("Delete allergy");
					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == ButtonType.OK) {
						DeleteJPA delete = new DeleteJPA();
						Allergies allergy = tableAllergies.getSelectionModel().getSelectedItem();
						delete.deleteAllergy(allergy);
						tableAllergies.getItems().remove(allergy);
						if(allergy.getTreatment() != null) {
							delete.deleteTreatment(allergy.getTreatment());
						}
						patient.getAllergies().remove(allergy);
						tableAllergies.autosize();
					}
				}

			});
			
			modify.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					if (tableAllergies.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select an allergy");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAllergies.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerUpdateAllergy controller = loader.<ControllerUpdateAllergy>getController();
						controller.initComponents(tableAllergies.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshAllergies();
				}
			});
			
			addTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableAllergies.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select an allergy");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					
					if (tableAllergies.getSelectionModel().getSelectedItem().getTreatment() != null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Treatment information");
						alert.setContentText("Ther is an existing treatment for this allergy");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initAllergy(tableAllergies.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshAllergies();
				}
			});
			
			modifyTreatment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (tableAllergies.getSelectionModel().getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must select an allergy");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTreatment.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerAddTreatment controller = loader.<ControllerAddTreatment>getController();
						controller.initAllergy(tableAllergies.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshAllergies();
				}
			});
		}
    }
    
    @FXML
    void onClickAddIllness(ActionEvent event) {
    	Illness illness = new Illness();
    	CreateJPA create = new CreateJPA();
    	
    	illness.setValue(this.typeIllness.getSelectionModel().getSelectedItem());
    	illness.setDate(Date.valueOf(this.dateIllness.getValue()));
    	illness.setDescription(this.descripIllness.getText());
    	illness.setName(this.nameIllness.getText());
    	illness.setPatient(this.patient);
    	
    	create.createIllnes(illness);
    	
    	this.typeIllness.getSelectionModel().clearSelection();
    	this.dateIllness.setValue(null);
    	this.descripIllness.clear();
    	this.nameIllness.clear();
    	
    	this.tableIllness.getItems().add(illness);
    }

    @FXML
    void onClickAddSurgery(ActionEvent event) {
    	Surgeries surgery = new Surgeries();
    	CreateJPA create = new CreateJPA();
    	
    	surgery.setType(typeSurgeries.getText());
    	surgery.setDate(Date.valueOf(dateOfSurgery.getValue()));
    	surgery.setPatient(this.patient);
    	create.createSurgery(surgery);
    	
    	this.typeSurgeries.clear();
    	this.dateOfSurgery.setValue(null);
    	
    	this.patient.getSurgeries().add(surgery);
    	this.tableSurgeries.getItems().add(surgery);
    }

    @FXML
    void onClickAddVaccine(ActionEvent event) {
    	Vaccine vaccine = new Vaccine();
    	CreateJPA create = new CreateJPA();
    	
    	vaccine.setNameVaccine(typeVaccine.valueOf(vaccineAddName.getSelectionModel().getSelectedItem()));
    	LocalDate ld = vaccinesAddDate.getValue();
    	vaccine.setDate(Date.valueOf(ld));
    	vaccine.setDescription(vaccinessAddObservations.getText());
    	vaccine.setPatient(this.patient);
    	create.createVaccine(vaccine);
    	
    	vaccineAddName.getSelectionModel().clearSelection();
    	vaccinesAddDate.setValue(null);
    	vaccinessAddObservations.clear();
    	
    	this.tableVaccines.getItems().add(vaccine);
    }

    @FXML
    void onClickModify(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	
    	this.patient.getcHistory().setAddictionAlcohol(ADDICTIONS.valueOf(this.alcohol.getSelectionModel().getSelectedItem()));
    	this.patient.getcHistory().setAddictionsDrugs(ADDICTIONS.valueOf(this.drugs.getSelectionModel().getSelectedItem()));
    	this.patient.getcHistory().setAddictionsOthers(ADDICTIONS.valueOf(this.others.getSelectionModel().getSelectedItem()));
    	this.patient.getcHistory().setBloodgroup(BLOODGROUP.valueOf(this.bGroup.getSelectionModel().getSelectedItem()));
    	this.patient.getcHistory().setMedicalInsurance(this.mInsCompany.getSelectionModel().getSelectedItem());
    	this.patient.getcHistory().setPatient(this.patient);
    	
    	update.updateClinicalRecord(patient);
    }

    @FXML
    void onClickPDF(ActionEvent event) {

    }

    @FXML
    void onClickSet(ActionEvent event) {
    	ClinicalHistory cl = new ClinicalHistory();
    	CreateJPA create = new CreateJPA();
    	
    	cl.setAddictionAlcohol(ADDICTIONS.valueOf(this.alcohol.getSelectionModel().getSelectedItem()));
    	cl.setAddictionsDrugs(ADDICTIONS.valueOf(this.drugs.getSelectionModel().getSelectedItem()));
    	cl.setAddictionsOthers(ADDICTIONS.valueOf(this.others.getSelectionModel().getSelectedItem()));
    	cl.setBloodgroup(BLOODGROUP.valueOf(this.bGroup.getSelectionModel().getSelectedItem()));
    	cl.setMedicalInsurance(this.mInsCompany.getSelectionModel().getSelectedItem());
    	cl.setPatient(this.patient);

    	create.createClinicalRecord(cl);
    	
    	this.patient.setcHistory(cl);
    	
    	this.modify.setVisible(true);
    	this.set.setVisible(false);
    }
    
    public void initComponents(Patient patient) {
    	this.patient = patient;
    	name.setText(patient.getName());
    	surname.setText(patient.getSurname());
    	dBirth.setValue(patient.getDob().toLocalDate());
    	gender.setText(patient.getGender());
    	mail.setText(patient.getEmail());
    	mPhone.setText(String.valueOf(patient.getMobilePhone()));
    	hPhone.setText(String.valueOf(patient.getHousePhone()));
    	weight.setText(String.valueOf(patient.getWeight()));
    	height.setText(String.valueOf(patient.getHeight()));
		InputStream in = new ByteArrayInputStream(patient.getPhoto());
		BufferedImage im = null;
		try {
			im = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Image img = SwingFXUtils.toFXImage(im, null);
		this.image.setImage(img);
		
		
		ObservableList list = FXCollections.observableArrayList(patient.getAppointments());
		this.list.getItems().addAll(list);
		
		vaccineName.setCellValueFactory(new PropertyValueFactory<Vaccine, typeVaccine>("nameVaccine"));
		vaccineDate.setCellValueFactory(new PropertyValueFactory<Vaccine, Date>("date"));
		vaccineObservations.setCellValueFactory(new PropertyValueFactory<Vaccine, String>("description"));
		
		this.refreshList();
		
		if(this.patient.getcHistory() == null) {
			set.setVisible(true);
			modify.setVisible(false);
		}
		else if(this.patient.getcHistory() != null) {
			set.setVisible(false);
			modify.setVisible(true);
			
			alcohol.getSelectionModel().select(this.patient.getcHistory().getAddictionAlcohol().toString());
			drugs.getSelectionModel().select(this.patient.getcHistory().getAddictionsDrugs().toString());
			others.getSelectionModel().select(this.patient.getcHistory().getAddictionsOthers().toString());
			bGroup.getSelectionModel().select(this.patient.getcHistory().getBloodgroup().toString());
			mInsCompany.getSelectionModel().select(this.patient.getcHistory().getMedicalInsurance());
		}
		
		illnessesType.setCellValueFactory(new PropertyValueFactory<Illness, String>("value"));
		dateOfIllness.setCellValueFactory(new PropertyValueFactory<Illness, Date>("date"));
		illnessName.setCellValueFactory(new PropertyValueFactory<Illness, String>("name"));
		illnessDescription.setCellValueFactory(new PropertyValueFactory<Illness, String>("description"));
		
		this.refreshIllness();
		
		surgeriesType.setCellValueFactory(new PropertyValueFactory<Surgeries, String>("type"));
		surgeriesDate.setCellValueFactory(new PropertyValueFactory<Surgeries, Date>("date"));
		this.refreshSurgeries();
		
		columnTypeAllergies.setCellValueFactory(new PropertyValueFactory<Allergies, String>("type"));
		columnObservationAllergies.setCellValueFactory(new PropertyValueFactory<Allergies, String>("observations"));
		this.refreshAllergies();
    }
    
    @FXML
    void onRightClick(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.SECONDARY)) {
    		ContextMenu menu = new ContextMenu();
    		MenuItem delete = new MenuItem("Delete");
    		MenuItem modify = new MenuItem("Modify");
    		menu.getItems().addAll(delete, modify);
    		
    		this.tableVaccines.setContextMenu(menu);
    		
    		delete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(tableVaccines.getSelectionModel().getSelectedItem() ==  null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Delete information");
						alert.setContentText("You must selecta vaccine");
						alert.setTitle("Warning");
						alert.show();
						return;
					}
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
			    	alert.setContentText("Are you sure you want to delete this vaccine?");
			    	alert.setTitle("Information");
			    	alert.setHeaderText("Delete vaccine");
			    	Optional<ButtonType> result = alert.showAndWait();
			    	
			    	if(result.get() == ButtonType.OK) {
			    		DeleteJPA delete = new DeleteJPA();
			    		Vaccine vaccine = tableVaccines.getSelectionModel().getSelectedItem();
			    		delete.deleteVaccine(vaccine);
			    		tableVaccines.setContextMenu(null);
			    		tableVaccines.getItems().remove(vaccine);
					
			    		tableVaccines.autosize();
			    	}
				}
    		});
    		
    		modify.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateVaccine.fxml"));
					AnchorPane root = null;
					try {
						root = loader.load();
						ControllerUpdateVaccine controller = loader.<ControllerUpdateVaccine>getController();
						controller.initComponent(tableVaccines.getSelectionModel().getSelectedItem());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   	Stage modal = new Stage();
			    	modal.setTitle("Babylon Studio");
			    	modal.setScene(new Scene(root));
			    	modal.initOwner(window);
			    	modal.setResizable(false);
			    	modal.initModality(Modality.APPLICATION_MODAL);
			    	modal.showAndWait();
			    	
			    	refreshList();
				}
    		});
    	}
    }
    
    private void refreshList() {
    	this.tableVaccines.getItems().clear();
    	ObservableList<Vaccine> list2 = FXCollections.observableArrayList(this.patient.getVaccines());
    	this.tableVaccines.setItems(list2);
    }
    
    private void refreshIllness() {
    	this.tableIllness.getItems().clear();
    	ObservableList<Illness> list2 = FXCollections.observableArrayList(this.patient.getIllnesses());
    	this.tableIllness.setItems(list2);
    }
    
    private void refreshSurgeries() {
    	this.tableSurgeries.getItems().clear();
    	ObservableList<Surgeries> list2 = FXCollections.observableArrayList(this.patient.getSurgeries());
    	this.tableSurgeries.setItems(list2);
    }
    
    private void refreshAllergies() {
    	this.tableAllergies.getItems().clear();
    	ObservableList<Allergies> list2 = FXCollections.observableArrayList(this.patient.getAllergies());
    	this.tableAllergies.setItems(list2);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("YES", "NONE");
		alcohol.setItems(list);
		drugs.setItems(list);
		others.setItems(list);
		
		ObservableList list2 = FXCollections.observableArrayList("AP", "BP", "ABP", "AN", "BN", "ABN", "ZP", "ZN");
		bGroup.setItems(list2);
		
		ObservableList list3 = FXCollections.observableArrayList("SANITAS", "ADESLAS", "MAPFRE", "AEGON", "PLUS ULTRA SEGUROS");
		mInsCompany.setItems(list3);
		
		list4 = FXCollections.observableArrayList("CHOLERA", "DIPHTHERIA", "INFLUENZA_A", "INFLUENZA_B", "HEPATITIS_A", 
				"HEPATITIS_B", "PAPILLOMAVIRUS", "HERPES", "MEASLES", "MENINGOCOCCAL", "PNEUMOCOCCAL", 
				"RABIES","ROTAVIRUS", "RUBELLA", "SMALLPOX", "TETANUS", "TUBERCULOSIS", "TYPHOID", "VARICELLA", "YELLOWFEVER");
		vaccineAddName.setItems(list4);
		
		ObservableList list5 = FXCollections.observableArrayList("HEREDITARY", "PERSONAL");
		typeIllness.setItems(list5);
	}
}

