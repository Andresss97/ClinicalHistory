package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import com.calendarfx.model.Entry;
import com.calendarfx.view.*;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerAgendaPatients implements Initializable{
	
    @FXML
    private BorderPane container;
    
    private void createAgenda() throws IOException {
    	CalendarView calendar = new CalendarView();
    	calendar.setRequestedTime(LocalTime.now());
    	this.container.setCenter(calendar);
    	GridPane c = FXMLLoader.load(getClass().getResource("CreateAppointmentMenu.fxml"));
    	calendar.setEntryDetailsPopOverContentCallback(param -> c);
    	
		calendar.setEntryFactory(param -> {
			Stage window = (Stage) container.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAppointmentMenu.fxml"));
			GridPane root = null;
			try {
				root = loader.load();
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
	    	
			Entry<Object> entry = new Entry<>("New Entry");
			ZonedDateTime time = param.getZonedDateTime();

			entry.changeStartDate(time.toLocalDate());
			entry.changeStartTime(time.toLocalTime());
			entry.changeEndDate(entry.getStartDate());
			entry.changeEndTime(entry.getStartTime().plusHours(1));

			return entry;
    	});
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.createAgenda();
			
			for(int i = 0; i < Main.patient.getAppointments().size(); i++) {
				Entry<Object> entry = new Entry<>(Main.patient.getAppointments().get(i).getReason());
				entry.setId(String.valueOf(Main.patient.getAppointments().get(i).getID()));
				entry.changeStartDate(Main.patient.getAppointments().get(i).getDate().toLocalDate());
				switch(Main.patient.getAppointments().get(i).getHour()) {
				
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
