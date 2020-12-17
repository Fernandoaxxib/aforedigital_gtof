package mx.axxib.aforedigitalgt.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.TipoTransacOut;
import mx.axxib.aforedigitalgt.serv.GraficasService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;


@Scope(value = "session")
@Component(value = "graficasCtrll")
@ELBeanName(value = "graficasCtrll")
public class GraficasCtrll extends ControllerBase {

	@Autowired
	private GraficasService graficasService;

	private LineChartModel lineModel;

	@Getter
	@Setter
	private String fechaInicioCombo;

	// private Map<String,String> fechasInicio;

	@Getter
	@Setter
	private LinkedHashMap<String, String> fechasInicio;

	@Getter
	@Setter
	private String fechaFinCombo;

	@Getter
	@Setter
	private Map<String, String> fechasFin;
	
	@Getter
	private Integer totParcial;
	@Getter
	private Integer totTotal;

	@PostConstruct
	public void init() {
		fechasInicio = new LinkedHashMap<>();
		fechasInicio.put("ENE-2020", "1");
		fechasInicio.put("FEB-2020", "2");
		fechasInicio.put("MAR-2020", "3");
		fechasInicio.put("ABR-2020", "4");
		fechasInicio.put("MAY-2020", "5");
		fechasInicio.put("JUN-2020", "6");
		fechasInicio.put("JUL-2020", "7");
		fechasInicio.put("AGO-2020", "8");
		fechasInicio.put("SEP-2020", "9");
		fechasInicio.put("OCT-2020", "10");
		fechasInicio.put("NOV-2020", "11");
		fechasInicio.put("DIC-2020", "12");

		fechasFin = fechasInicio;

		createLineModel();

	}

	
	public void reset() {
		fechaFinCombo = "0";
	}

	public void onCountryChange() {
		// **************************
		addMessage("Fecha inicio: " + fechaInicioCombo + " Fecha fin: " + fechaFinCombo);

		String fechaI="2020".concat("0"+fechaInicioCombo).concat("01");
		String fechaF="2020".concat("0"+fechaFinCombo).concat("01");
		try {
			List<TipoTransacOut> tipoTransacciones= graficasService.getTipoTransacciones(fechaI, fechaF);
			totParcial=tipoTransacciones.get(0).getTotParcial();
			totTotal=tipoTransacciones.get(0).getTotTotal();
		} catch (AforeException e) {
			
			e.printStackTrace();
		}
		// ***************************
		lineModel = new LineChartModel();

		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Object> values = new ArrayList<>();
		values.add(10);
		values.add(20);
		values.add(30);
		values.add(25);
		values.add(20);
		values.add(15);
		values.add(5);
		dataSet.setData(values);
		dataSet.setFill(false);
		// dataSet.setLabel("My First Dataset");
		dataSet.setBorderColor("rgb(75, 192, 192)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("January");
		labels.add("February");
		labels.add("March");
		labels.add("April");
		labels.add("May");
		labels.add("June");
		labels.add("July");
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		/* title.setText("Line Chart"); */
		options.setTitle(title);

		lineModel.setOptions(options);
		lineModel.setData(data);
	}

	public void exit() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public void createLineModel() {
		lineModel = new LineChartModel();

		ChartData data = new ChartData();

		LineChartDataSet dataSet2 = new LineChartDataSet();
		List<Object> values2 = new ArrayList<>();
		values2.add(65);
		values2.add(59);
		values2.add(80);
		values2.add(81);
		values2.add(56);
		values2.add(55);
		values2.add(40);
		dataSet2.setData(values2);
		dataSet2.setFill(false);
		// dataSet2.setLabel("My Second Dataset");
		dataSet2.setBorderColor("rgb(75, 75, 75)");
		dataSet2.setLineTension(0.2);

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Object> values = new ArrayList<>();
		values.add(10);
		values.add(20);
		values.add(30);
		values.add(25);
		values.add(20);
		values.add(15);
		values.add(5);
		dataSet.setData(values);
		dataSet.setFill(false);
		// dataSet.setLabel("My First Dataset");
		dataSet.setBorderColor("rgb(75, 192, 192)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);
		data.addChartDataSet(dataSet2);

		List<String> labels = new ArrayList<>();
		labels.add("January");
		labels.add("February");
		labels.add("March");
		labels.add("April");
		labels.add("May");
		labels.add("June");
		labels.add("July");
		data.setLabels(labels);

		List<String> labels2 = new ArrayList<>();
		labels2.add("January");
		labels2.add("February");
		labels2.add("March");
		labels2.add("April");
		labels2.add("May");
		labels2.add("June");
		labels2.add("July");
		data.setLabels(labels2);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		// title.setText("Line Chart");
		options.setTitle(title);

		lineModel.setOptions(options);
		lineModel.setData(data);
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

}
