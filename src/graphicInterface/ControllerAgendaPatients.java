package graphicInterface;

import java.net.URL;

import java.util.ResourceBundle;

import com.calendarfx.view.CalendarView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;


public class ControllerAgendaPatients implements Initializable{
	
    @FXML
    private BorderPane container;
    
    private void createAgenda() {
    	CalendarView calendar = new CalendarView();
    	this.container.setCenter(calendar);
    }
    
    @FXML
    void onClickDayView(ActionEvent event) {

    }

    @FXML
    void onClickMonthView(ActionEvent event) {
    	
    }

    @FXML
    void onClickWeekView(ActionEvent event) {
    	
    }
    
    @FXML
    void onNextWeekClick(ActionEvent event) {

    }

    @FXML
    void onPreviousWeekClick(ActionEvent event) {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.createAgenda();
	}
}
