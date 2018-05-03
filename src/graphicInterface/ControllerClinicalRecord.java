package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import pojos.Appointment;
import pojos.Patient;
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
    private JFXComboBox<?> alcohol;

    @FXML
    private JFXComboBox<?> drugs;

    @FXML
    private JFXComboBox<?> others;

    @FXML
    private JFXComboBox<?> bGroup;

    @FXML
    private JFXComboBox<?> mInsCompany;

    @FXML
    private JFXButton modify;

    @FXML
    private JFXButton set;

    @FXML
    private ListView<Appointment> list;

    @FXML
    private TableView<Vaccine> tableVaccines;

    @FXML
    private TableColumn<?, ?> vaccineName;

    @FXML
    private TableColumn<?, ?> vaccineDate;

    @FXML
    private TableColumn<?, ?> vaccineObservations;

    @FXML
    private JFXComboBox<String> vaccineAddName;

    @FXML
    private DatePicker vaccinesAddDate;

    @FXML
    private JFXTextField vaccinessAddObservations;

    @FXML
    private JFXButton vaccinesAdd;

    @FXML
    private TableView<?> tableSurgeries;

    @FXML
    private TableColumn<?, ?> surgeriesType;

    @FXML
    private TableColumn<?, ?> surgeriesDate;

    @FXML
    private TableColumn<?, ?> surgeriesTreatment;

    @FXML
    private TableColumn<?, ?> surgeriesTypeTreatment;

    @FXML
    private TableColumn<?, ?> surgeriesDescription;

    @FXML
    private TableColumn<?, ?> surgeriesStartDate;

    @FXML
    private TableColumn<?, ?> surgeriesEndDate;

    @FXML
    private TableColumn<?, ?> surgeriesTestResults;

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
    private TableColumn<?, ?> illnessesType;

    @FXML
    private TableColumn<?, ?> dateOfIllness;

    @FXML
    private TableColumn<?, ?> illnessName;

    @FXML
    private TableColumn<?, ?> illnessDescription;

    @FXML
    private TableColumn<?, ?> illnessTreatment;

    @FXML
    private TableColumn<?, ?> illnessTypeTreatment;

    @FXML
    private TableColumn<?, ?> illnesssDescription;

    @FXML
    private TableColumn<?, ?> illnesssStartDate;

    @FXML
    private TableColumn<?, ?> illnessEndDate;

    @FXML
    private TableColumn<?, ?> illnessTestResults;

    @FXML
    private JFXComboBox<?> typeIllness;

    @FXML
    private DatePicker dateIllness;

    @FXML
    private JFXTextField nameIllness;

    @FXML
    private JFXTextField descripIllness;

    @FXML
    private JFXComboBox<?> typeTreatmentIllness;

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
    
    @FXML
    void onClickAddIllness(ActionEvent event) {

    }

    @FXML
    void onClickAddSurgery(ActionEvent event) {

    }

    @FXML
    void onClickAddVaccine(ActionEvent event) {
    	Vaccine vaccine = new Vaccine();
    	vaccine.setNameVaccine(typeVaccine.valueOf(vaccineAddName.getSelectionModel().getSelectedItem()));
    	LocalDate ld = vaccinesAddDate.getValue();
    	vaccine.setDate(Date.valueOf(ld));
    	vaccine.setDescription(vaccinessAddObservations.getText());
    	
    	
    }

    @FXML
    void onClickModify(ActionEvent event) {

    }

    @FXML
    void onClickPDF(ActionEvent event) {

    }

    @FXML
    void onClickPrint(ActionEvent event) {

    }

    @FXML
    void onClickSet(ActionEvent event) {

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
		
		for(int i = 0; i < patient.getVaccines().size(); i++) {
			tableVaccines.getItems().add(patient.getVaccines().get(i));
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("YES", "NONE");
		alcohol.setItems(list);
		drugs.setItems(list);
		others.setItems(list);
		
		ObservableList list2 = FXCollections.observableArrayList("AP, BP, ABP, AN, BN, ABN, ZP, ZN");
		bGroup.setItems(list2);
		
		ObservableList list3 = FXCollections.observableArrayList("SANITAS, ADESLAS, MAPFRE, AEGON, PLUS ULTRA SEGUROS");
		mInsCompany.setItems(list3);
		
		ObservableList list4 = FXCollections.observableArrayList("CHOLERA,DIPHTHERIA,INFLUENZA_A,INFLUENZA_B,HEPATITIS_A,"
				+ "HEPATITIS_B,PAPILLOMAVIRUS, HERPES,MEASLES,MENINGOCOCCAL,PNEUMOCOCCAL,"
				+ "RABIES,ROTAVIRUS,RUBELLA,SMALLPOX,TETANUS,TUBERCULOSIS,TYPHOID, VARICELLA,YELLOWFEVER");
		vaccineAddName.setItems(list4);
	}
}

