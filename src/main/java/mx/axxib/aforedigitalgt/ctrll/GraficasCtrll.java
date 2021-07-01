package mx.axxib.aforedigitalgt.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.TipoTransacOut;
import mx.axxib.aforedigitalgt.serv.GraficasServ;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.*;
        


@Scope(value = "session")
@Component(value = "graficasCtrll")
@ELBeanName(value = "graficasCtrll")
public class GraficasCtrll extends ControllerBase {

	@Autowired
	private GraficasServ graficasService;

	@Getter
	@Setter
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
	
	@Getter
	@Setter
	private BarChartModel barModel;
	
	@Getter
	@Setter
	private BarChartModel barModel2;
	
	@Getter
	@Setter
	private BarChartModel stackedBarModel;
	
	@Getter
	@Setter
	private PieChartModel pieModel;
	
	@Getter
	@Setter
	 private DonutChartModel donutModel;
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			createBarModel();
			createBarModel2();
			createPieModel() ;
			createDonutModel();
			init=false;
		}
	}	

	/*
	 * @PostConstruct public void init2() { fechasInicio = new LinkedHashMap<>();
	 * fechasInicio.put("ENE-2020", "1"); fechasInicio.put("FEB-2020", "2");
	 * fechasInicio.put("MAR-2020", "3"); fechasInicio.put("ABR-2020", "4");
	 * fechasInicio.put("MAY-2020", "5"); fechasInicio.put("JUN-2020", "6");
	 * fechasInicio.put("JUL-2020", "7"); fechasInicio.put("AGO-2020", "8");
	 * fechasInicio.put("SEP-2020", "9"); fechasInicio.put("OCT-2020", "10");
	 * fechasInicio.put("NOV-2020", "11"); fechasInicio.put("DIC-2020", "12");
	 * 
	 * fechasFin = fechasInicio;
	 * 
	 * createLineModel();
	 * 
	 * createBarModel();
	 * 
	 * }
	 */
	
	/*
	 * public void init() { fechasInicio = new LinkedHashMap<>();
	 * fechasInicio.put("ENE-2020", "1"); fechasInicio.put("FEB-2020", "2");
	 * fechasInicio.put("MAR-2020", "3"); fechasInicio.put("ABR-2020", "4");
	 * fechasInicio.put("MAY-2020", "5"); fechasInicio.put("JUN-2020", "6");
	 * fechasInicio.put("JUL-2020", "7"); fechasInicio.put("AGO-2020", "8");
	 * fechasInicio.put("SEP-2020", "9"); fechasInicio.put("OCT-2020", "10");
	 * fechasInicio.put("NOV-2020", "11"); fechasInicio.put("DIC-2020", "12");
	 * 
	 * fechasFin = fechasInicio;
	 * 
	 * createLineModel();
	 * 
	 * }
	 */

	
	public void reset() {
		fechaFinCombo = "0";
	}

	public void onCountryChange() {
		// **************************
		String fechaI="";
		String fechaF="";
		//addMessage("Fecha inicio: " + fechaInicioCombo + " Fecha fin: " + fechaFinCombo);

		if(Integer.parseInt(fechaInicioCombo)<10) {
		   fechaI="01".concat("0"+fechaInicioCombo).concat("20");
		}else {
			 fechaI="01".concat(fechaInicioCombo).concat("20");
		}
		
		if(Integer.parseInt(fechaFinCombo)<10) {
			fechaF="01".concat("0"+fechaFinCombo).concat("20");
		}else {
			fechaF="01".concat(fechaFinCombo).concat("20");
		}
		
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

	public void createBarModel() {
		barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Retiros Cancelados");    
        barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        barDataSet.setBorderColor("rgb(255, 99, 132)");
        barDataSet.setBorderWidth(1);
        List<Number> values = new ArrayList<>();
        values.add(3); 
        values.add(0);
        values.add(0);
        values.add(0);
        values.add(0);
        barDataSet.setData(values);        
      
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Retiros Capturada");                                
        barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
        barDataSet2.setBorderColor("rgba(255, 159, 64)");
        barDataSet2.setBorderWidth(1);
        List<Number> values2 = new ArrayList<>();        
        values2.add(0);  
        values2.add(2);
        values2.add(0);
        values2.add(0);
        values2.add(0);
        barDataSet2.setData(values2);   
                
        
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Retiros Prev Liquida");                                
        barDataSet3.setBackgroundColor("rgba(255, 205, 86, 0.2)");
        barDataSet3.setBorderColor("rgb(255, 205, 86)");
        barDataSet3.setBorderWidth(1);
        List<Number> values3 = new ArrayList<>();        
        values3.add(0);  
        values3.add(0);
        values3.add(58);
        values3.add(0);
        values3.add(0);
        barDataSet3.setData(values3);  
                
        
        BarChartDataSet barDataSet4 = new BarChartDataSet();
        barDataSet4.setLabel("Retiros Liquidados");                                
        barDataSet4.setBackgroundColor("rgb(75, 192, 192,0.2)");
        barDataSet4.setBorderColor("rgb(75, 192, 192)");
        barDataSet4.setBorderWidth(1);
        List<Number> values4 = new ArrayList<>();        
        values4.add(0);  
        values4.add(0);
        values4.add(0);
        values4.add(32);
        values4.add(0);
        barDataSet4.setData(values4);         
        
        BarChartDataSet barDataSet5 = new BarChartDataSet();
        barDataSet5.setLabel("Retiros Liquida Mens");                                
        barDataSet5.setBackgroundColor("rgb(75, 192, 192,0.2)");
        barDataSet5.setBorderColor("rgb(75, 192, 192)");
        barDataSet5.setBorderWidth(1);
        List<Number> values5 = new ArrayList<>();        
        values5.add(0);  
        values5.add(0);
        values5.add(0);
        values5.add(0);
        values5.add(25);
        barDataSet5.setData(values5);
        
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        data.addChartDataSet(barDataSet3);
        data.addChartDataSet(barDataSet4);
        data.addChartDataSet(barDataSet5);
        
        List<String> labels = new ArrayList<>();
        labels.add("");
        labels.add(""); 
        labels.add("");         
        labels.add("");
        labels.add("");
        data.setLabels(labels);
        barModel.setData(data);


        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
       
        options.setScales(cScales);

        

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(12);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
        
        Tooltip t= new Tooltip();
        t.setEnabled(true);
        t.setBackgroundColor("RED");
        options.setTooltip(t);

       
        // disable animation
		/*
		 * Animation animation = new Animation(); animation.setDuration(0);
		 * options.setAnimation(animation);
		 */

        barModel.setOptions(options);
    }
	private void createPieModel() {
		pieModel = new PieChartModel();
		
        ChartData data = new ChartData();
        
        
        PieChartDataSet dataSet = new PieChartDataSet();
        
        List<Number> values = new ArrayList<>();
        values.add(3670);
        values.add(2232);        
        dataSet.setData(values);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");        
        dataSet.setBackgroundColor(bgColors);
        
        
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Parcial");
        labels.add("Total");        
        data.setLabels(labels);
        
        PieChartOptions options = new PieChartOptions();
        Legend l= new Legend();
        l.setPosition("bottom");
        options.setLegend(l);
        
        pieModel.setOptions(options);
        pieModel.setData(data);
    }
	
	/*
	 public void createBarModel() {
		barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Parciales");
        

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);
        

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
       
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 99, 132)");
        
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Enero");
        labels.add("Febrero");
        labels.add("Marzo");
        labels.add("Abril");
        labels.add("Mayo");
        labels.add("Junio");
        labels.add("Julio");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
		
		  Animation animation = new Animation(); animation.setDuration(0);
		  options.setAnimation(animation);
		 

        barModel.setOptions(options);
    }
	 * */
	public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        //barDataSet.setLabel("My First Dataset");

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();                                      
        labels.add("Retiros Cancelados");
        labels.add("Retiros Capturada");
        labels.add("Retiros Prev Liquida");
        labels.add("Retiros Liquidados");
        labels.add("Retiros Liquida Mens");
        
        data.setLabels(labels);
        barModel2.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(12);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
		/*
		 * Animation animation = new Animation(); animation.setDuration(0);
		 * options.setAnimation(animation);
		 */

        barModel2.setOptions(options);
    }

	 public void createDonutModel() {
	        donutModel = new DonutChartModel();
	        ChartData data = new ChartData();

	        DonutChartDataSet dataSet = new DonutChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(3670);
	        values.add(2232);
	        dataSet.setData(values);

	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(255, 99, 132)");
	        bgColors.add("rgb(54, 162, 235)");
	        
	        dataSet.setBackgroundColor(bgColors);

	        data.addChartDataSet(dataSet);
	        List<String> labels = new ArrayList<>();
	        labels.add("Parcial");
	        labels.add("Total");
	        
	        data.setLabels(labels);

	        donutModel.setData(data);
	    }

}
