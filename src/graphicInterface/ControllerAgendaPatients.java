package graphicInterface;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.calendarfx.view.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;


public class ControllerAgendaPatients implements Initializable{
	
    @FXML
    private BorderPane container;
    
    private void createAgenda() {
    	CalendarView calendar = new CalendarView();
    	calendar.setRequestedTime(LocalTime.now());
    	this.container.setCenter(calendar);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.createAgenda();
	}
}
