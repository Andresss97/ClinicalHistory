package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.*;
import com.jfoenix.controls.JFXButton.ButtonType;

import creation.QuerysSelect;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojos.Appointment;


public class ControllerAgendaPatients implements Initializable{
	
    @FXML
    private BorderPane container;
    
    private CalendarView calendar;
    
    public static Entry<Object> entry;
    
    private void createAgenda() throws IOException {
    	calendar = new CalendarView();
    	Calendar calendario = new Calendar(Main.patient.getName() +" Calendar");
    	calendario.setStyle(Style.STYLE2);
    	CalendarSource calendarios = new CalendarSource(Main.patient.getName() +" Calendar");
    	calendarios.getCalendars().add(calendario);
    	calendar.getCalendarSources().setAll(calendarios);
    	calendar.setRequestedTime(LocalTime.now());
    	
    	this.container.setCenter(calendar);

		calendar.setEntryFactory(param -> {	    		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAppointmentMenu.fxml"));
			GridPane c = null;
			try {
				c = loader.load();
				ControllerCreateAppointmentMenu controller = loader.<ControllerCreateAppointmentMenu>getController();
				controller.initAgenda(true);
				
		    	Stage window = (Stage) container.getScene().getWindow();
			   	Stage modal = new Stage();
		    	modal.setTitle("Babylon Studio");
		    	modal.setScene(new Scene(c));
		    	modal.initOwner(window);
		    	modal.setResizable(false);
		    	modal.initModality(Modality.APPLICATION_MODAL);
		    	modal.showAndWait();
		    	if(entry != null) {
		    		this.calendar.getCalendarSources().get(0).getCalendars().get(0).addEntry(entry);
		    	}
			}
			catch(IOException ex) {
				
			}
			return entry;
    	});
		
		this.calendar.setEntryDetailsCallback(param -> {
			InputEvent evt = param.getInputEvent();
	        if (evt instanceof MouseEvent) {
	        	MouseEvent mouseEvent = (MouseEvent) evt;
	            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
	            	QuerysSelect qs = new QuerysSelect();
	            	Entry<Object> entry = (Entry<Object>) param.getEntry();
	            	int id = Integer.parseInt(entry.getId());
	            	try {
						Appointment app = qs.selectAppointmentById(id);	            	
						BorderPane c = null;
						FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAppointment.fxml"));
						c = loader.load();
						ControllerUpdateAppointment controller = loader.<ControllerUpdateAppointment>getController();
						controller.initComponents2(app);
						
				    	Stage window = (Stage) container.getScene().getWindow();
					   	Stage modal = new Stage();
				    	modal.setTitle("Babylon Studio");
				    	modal.setScene(new Scene(c));
				    	modal.initOwner(window);
				    	modal.setResizable(false);
				    	modal.initModality(Modality.APPLICATION_MODAL);
				    	modal.showAndWait();
				    	
				    	if(this.entry == null) {
				    		return false;
				    	}
				    	
				    	this.calendar.getCalendarSources().get(0).getCalendars().get(0).removeEntry(entry);
				    	this.calendar.getCalendarSources().get(0).getCalendars().get(0).addEntry(this.entry);
				    	
				    	return true;
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }

			return false;
		});
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.createAgenda();
			
			for(int i = 0; i < Main.patient.getAppointments().size(); i++) {
				Entry<Object> entry = new Entry<>();
				entry.setTitle(Main.patient.getAppointments().get(i).getReason());
				LocalTime time = LocalTime.parse(Main.patient.getAppointments().get(i).getHour());
				entry.setId(String.valueOf(Main.patient.getAppointments().get(i).getID()));
				entry.changeStartDate(Main.patient.getAppointments().get(i).getDate().toLocalDate());
				entry.changeEndDate(Main.patient.getAppointments().get(i).getDate().toLocalDate());
				entry.changeStartTime(time);
				entry.changeEndTime(entry.getStartTime().plusMinutes(30));
				this.calendar.getCalendarSources().get(0).getCalendars().get(0).addEntry(entry);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
