
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
import pojos.Appointment;
import pojos.ClinicalHistory;
import pojos.ClinicalHistory.ADDICTIONS;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Illness;
import pojos.Illness.typeDisease;
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
    private TableColumn<Surgeries, ?> surgeriesType;

    @FXML
    private TableColumn<Surgeries, ?> surgeriesDate;

    @FXML
    private TableColumn<Treatment, ?> surgeriesTreatment;

    @FXML
    private TableColumn<Treatment, ?> surgeriesTypeTreatment;

    @FXML
    private TableColumn<Treatment, ?> surgeriesDescription;

    @FXML
    private TableColumn<Treatment, ?> surgeriesStartDate;

    @FXML
    private TableColumn<Treatment, ?> surgeriesEndDate;

    @FXML
    private TableColumn<Treatment, ?> surgeriesTestResults;

    @FXML
    private JFXTextField typeSurgeries;

    @FXML
    private DatePicker dateOfSurgery;

    @FXML
    private JFXComboBox<?> tSurgeryTreatment;

    @FXML
    private JFXTextField descSurgeriesTreatment;

    @FXML
    private DatePicker startDateSurgery;

    @FXML
    private DatePicker endDateSurgery;

    @FXML
    private JFXTextField testResultsSurgery;

    @FXML
    private JFXButton addSurgery;
    
    @FXML
    private TableView<Illness> tableIllness;
    
    @FXML
    private TableColumn<Illness, typeDisease> illnessesType;

    @FXML
    private TableColumn<Illness, Date> dateOfIllness;

    @FXML
    private TableColumn<Illness, String> illnessName;

    @FXML
    private TableColumn<Illness, String> illnessDescription;

    @FXML
    private TableColumn<Illness, Treatment> illnessTreatment;

    @FXML
    private TableColumn<Treatment, typeTreatment> illnessTypeTreatment;

    @FXML
    private TableColumn<Treatment, String> illnesssDescription;

    @FXML
    private TableColumn<Treatment, Date> illnesssStartDate;

    @FXML
    private TableColumn<Treatment, Date> illnessEndDate;

    @FXML
    private TableColumn<Treatment, String> illnessTestResults;

    @FXML
    private JFXComboBox<String> typeIllness;

    @FXML
    private DatePicker dateIllness;

    @FXML
    private JFXTextField nameIllness;

    @FXML
    private JFXTextField descripIllness;

    @FXML
    private JFXComboBox<String> typeTreatmentIllness;

    @FXML
    private JFXTextField descriptionTreatmentIllness;

    @FXML
    private DatePicker startDateTreatmentIllness;

    @FXML
    private DatePicker endDateTreatmentIllness;

    @FXML
    private JFXTextField testResultsTreatmentIllness;

    @FXML
    private JFXButton addIllness;

    @FXML
    private JFXButton pdf;

    @FXML
    private JFXButton print;
    
    private Patient patient;
    
    private ObservableList<String> list4;
    
    @FXML
    void onClickAddIllness(ActionEvent event) {
    	Illness illness = new Illness();
    	Treatment treatment = new Treatment();
    	CreateJPA create = new CreateJPA();
    	
    	illness.setTypeDisease(typeDisease.valueOf(this.typeIllness.getSelectionModel().getSelectedItem()));
    	illness.setDate(Date.valueOf(this.dateIllness.getValue()));
    	illness.setDescription(this.descripIllness.getText());
    	illness.setName(this.nameIllness.getText());
    	illness.setPatient(this.patient);
    	
    	treatment.setTreatment(typeTreatment.valueOf(this.typeTreatmentIllness.getSelectionModel().getSelectedItem()));
    	treatment.setStartDate(Date.valueOf(this.startDateTreatmentIllness.getValue()));
    	treatment.setEndDate(Date.valueOf(this.endDateTreatmentIllness.getValue()));
    	treatment.setDescrpition(this.descriptionTreatmentIllness.getText());
    	treatment.setIllness(illness);
    	treatment.setResults(this.testResultsTreatmentIllness.getText());
    	treatment.setPatient(this.patient);
    	
    	illness.setTreatment(treatment);
    	
    	create.createIllnes(illness);
    	create.createTreatment(treatment);
    	
    	this.typeIllness.getSelectionModel().clearSelection();
    	this.dateIllness.setValue(null);
    	this.descripIllness.clear();
    	this.nameIllness.clear();
    	this.typeTreatmentIllness.getSelectionModel().clearSelection();
    	this.startDateTreatmentIllness.setValue(null);
    	this.endDateTreatmentIllness.setValue(null);
    	this.testResultsTreatmentIllness.clear();
    	
    	this.tableIllness.getItems().add(illness);
    }

    @FXML
    void onClickAddSurgery(ActionEvent event) {
    	Surgeries surgery = new Surgeries();
    	Treatment treatment = new Treatment();
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
    void onClickPrint(ActionEvent event) {

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
    	System.out.println(patient.getID());
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
		
		illnessesType.setCellValueFactory(new PropertyValueFactory<Illness, typeDisease>("type"));
		dateOfIllness.setCellValueFactory(new PropertyValueFactory<Illness, Date>("date"));
		illnessName.setCellValueFactory(new PropertyValueFactory<Illness, String>("name"));
		illnessDescription.setCellValueFactory(new PropertyValueFactory<Illness, String>("description"));
		illnessTreatment.setCellValueFactory(new PropertyValueFactory<Illness, Treatment>("treatment"));
		illnessTypeTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, typeTreatment>("treatment"));
		illnesssDescription.setCellValueFactory(new PropertyValueFactory<Treatment, String>("description"));
		illnesssStartDate.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("startDate"));
		illnessEndDate.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("endDate"));
		illnessTestResults.setCellValueFactory(new PropertyValueFactory<Treatment, String>("tResults"));
		
		this.refreshIllness();
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
						alert.setContentText("You must select");
						alert.setTitle("Warning");
						alert.show();
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
    	this.tableVaccines.getItems().addAll(this.patient.getVaccines());
    }
    
    private void refreshIllness() {
    	this.tableIllness.getItems().clear();
    	this.tableIllness.getItems().addAll(this.patient.getIllnesses());
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
		
		ObservableList list6 = FXCollections.observableArrayList("MEDICATION", "REHAB", "OTHER");
		this.tSurgeryTreatment.setItems(list6);
		this.typeTreatmentIllness.setItems(list6);
	}
}

