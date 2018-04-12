package graphicInterface;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.labs.icalendaragenda.scene.control.agenda.ICalendarAgenda;
import jfxtras.labs.icalendarfx.VCalendar;

public class ControllerAgendaPatients implements Initializable{
	
    @FXML
    private BorderPane container;

    @FXML
    private JFXButton pWeek;

    @FXML
    private JFXButton nWeek;

    @FXML
    private JFXButton day;

    @FXML
    private JFXButton week;

    @FXML
    private JFXButton month;
    
    private ICalendarAgenda agenda;
    
    private VCalendar vcalendar;
    
    private void createAgenda() {
    	this.vcalendar = new VCalendar();
    	this.agenda = new ICalendarAgenda(vcalendar);
    	container.setCenter(this.agenda);
    	
    	this.agenda.actionCallbackProperty();
    }
    
    @FXML
    void onClickDayView(ActionEvent event) {
    	this.agenda.setSkin(new AgendaDaySkin(agenda));
    }

    @FXML
    void onClickMonthView(ActionEvent event) {
    	
    }

    @FXML
    void onClickWeekView(ActionEvent event) {
    	this.agenda.setSkin(new AgendaWeekSkin(agenda));
    }
    
    @FXML
    void onNextWeekClick(ActionEvent event) {
    	LocalDateTime ldt = agenda.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
    	agenda.setDisplayedLocalDateTime(ldt);
    }

    @FXML
    void onPreviousWeekClick(ActionEvent event) {
    	LocalDateTime ldt = agenda.getDisplayedLocalDateTime().minus(Period.ofWeeks(1));
    	agenda.setDisplayedLocalDateTime(ldt);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.createAgenda();
	}
}
