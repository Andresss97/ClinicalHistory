package graphicInterface;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import creation.QuerysSelect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import statistics.StatsAppointments;

public class ControllerStatsView implements Initializable{

	@FXML
	private BarChart<?, ?> graph;
	
    @FXML
    private CategoryAxis hour;

    @FXML
    private NumberAxis people;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuerysSelect qs = new QuerysSelect();
		StatsAppointments stats = new StatsAppointments();
		
		try {
			ArrayList<String> list = qs.appStats();
			stats.generateStats(list);
			this.fillData(stats);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void fillData(StatsAppointments stats) {
		XYChart.Series serie = new XYChart.Series();
		serie.setName("Affluency");
		serie.getData().add(new XYChart.Data("9:00", stats.getNine()));
		serie.getData().add(new XYChart.Data("9:30", stats.getNine30()));
		serie.getData().add(new XYChart.Data("10:00", stats.getTen()));
		serie.getData().add(new XYChart.Data("10:30", stats.getTen30()));
		serie.getData().add(new XYChart.Data("11:00", stats.getEleven()));
		serie.getData().add(new XYChart.Data("11:30", stats.getEleven30()));
		serie.getData().add(new XYChart.Data("12:00", stats.getTwelve()));
		serie.getData().add(new XYChart.Data("12:30", stats.getTwelve30()));
		serie.getData().add(new XYChart.Data("13:00", stats.getOne()));
		serie.getData().add(new XYChart.Data("13:30", stats.getOne30()));
		serie.getData().add(new XYChart.Data("14:00", stats.getTwo()));
		serie.getData().add(new XYChart.Data("14:30", stats.getTwo30()));
		serie.getData().add(new XYChart.Data("15:00", stats.getThree()));
		serie.getData().add(new XYChart.Data("15:30", stats.getThree30()));
		serie.getData().add(new XYChart.Data("16:00", stats.getFour()));
		serie.getData().add(new XYChart.Data("16:30", stats.getFour30()));
		serie.getData().add(new XYChart.Data("17:00", stats.getFive()));
		serie.getData().add(new XYChart.Data("17:30", stats.getFive30()));
		serie.getData().add(new XYChart.Data("18:00", stats.getSix()));
		
		graph.getData().addAll(serie);
	}
}