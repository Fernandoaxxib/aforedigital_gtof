package mx.axxib.aforedigitalgt.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.DatosGraficasDetalleOut;
import mx.axxib.aforedigitalgt.eml.DatosGraficasTotalesOut;
import mx.axxib.aforedigitalgt.eml.GraficasDetalleOut;
import mx.axxib.aforedigitalgt.eml.GraficasTotalesOut;
import mx.axxib.aforedigitalgt.eml.TipoRetiroOut;
import mx.axxib.aforedigitalgt.serv.GraficasServ;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
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
	private BarChartModel barModel2_1;

	@Getter
	@Setter
	private BarChartModel barModel2_2;

	@Getter
	@Setter
	private BarChartModel barModel1_1;

	@Getter
	@Setter
	private BarChartModel barModel1_2;

	@Getter
	@Setter
	private PieChartModel pieModel;

	@Getter
	@Setter
	private List<TipoRetiroOut> datos;

	@Getter
	@Setter
	private List<TipoRetiroOut> datos2;

	@Getter
	@Setter
	private String display;

	@Getter
	@Setter
	private String display2;
	
	@Getter
	@Setter
	private String display3;
	
	@Getter
	@Setter
	private String display4;

	@Getter
	@Setter
	private List<GraficasTotalesOut> datosTotales;

	@Getter
	@Setter
	private List<GraficasDetalleOut> datosDetalles;
	@Getter
	@Setter
	private List<GraficasDetalleOut> datosDetallesTotales;

	@Getter
	@Setter
	private List<GraficasDetalleOut> datosDetallesParciales;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			cargaTotales();
			cargaDetalles();
			display = "inline";
			display2 = "none";
			display3=  "none";
			display4=  "none";
			createBarModel1_1();
			createBarModel1_2();
			createBarModel2_1();
			createBarModel2_2();
			createPieModel();
			init = false;
		}
	}

	public void exit() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	private void createPieModel() {
		pieModel = new PieChartModel();

		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();

		List<Number> values = new ArrayList<>();
		values.add(datosTotales.get(0).getTOTAL_RETIROS());
		values.add(datosTotales.get(1).getTOTAL_RETIROS());
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add(datosTotales.get(0).getTIPO_RETIRO());
		labels.add(datosTotales.get(1).getTIPO_RETIRO());
		data.setLabels(labels);

		PieChartOptions options = new PieChartOptions();
		Legend l = new Legend();
		l.setPosition("bottom");
		options.setLegend(l);

		pieModel.setOptions(options);
		pieModel.setData(data);
	}

	public void createBarModel1_1() {
		barModel1_1 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();

		List<Number> values = new ArrayList<>();
		values.add(datosTotales.get(0).getRETIROS_CANCELADOS());
		values.add(datosTotales.get(0).getRETIROS_PRECAPTURA());
		values.add(datosTotales.get(0).getRETIROS_CAPTURADA());
		values.add(datosTotales.get(0).getRETIROS_RECHAZADA());
		values.add(datosTotales.get(0).getRETIROS_PREV_LIQUIDA());
		values.add(datosTotales.get(0).getRETIROS_LIQUIDADOS());
		values.add(datosTotales.get(0).getRETIRO_LIQUIDA_MENS());

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
		labels.add("Retiros cancelados");
		labels.add("Retiros precaptura");
		labels.add("Retiros capturada");
		labels.add("Retiros rechazada");
		labels.add("Retiros previos liquidados");
		labels.add("Retiros liquidados");
		labels.add("Retiros liquidados por mes");

		data.setLabels(labels);
		barModel1_1.setData(data);

		// Options
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

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Retiros parciales por estatus");
		title.setFontSize(15);
		options.setTitle(title);

		barModel1_1.setOptions(options);
	}

	public void createBarModel1_2() {
		barModel1_2 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();

		List<Number> values = new ArrayList<>();
		values.add(datosTotales.get(1).getRETIROS_CANCELADOS());
		values.add(datosTotales.get(1).getRETIROS_PRECAPTURA());
		values.add(datosTotales.get(1).getRETIROS_CAPTURADA());
		values.add(datosTotales.get(1).getRETIROS_RECHAZADA());
		values.add(datosTotales.get(1).getRETIROS_PREV_LIQUIDA());
		values.add(datosTotales.get(1).getRETIROS_LIQUIDADOS());
		values.add(datosTotales.get(1).getRETIRO_LIQUIDA_MENS());

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
		labels.add("Retiros cancelados");
		labels.add("Retiros precaptura");
		labels.add("Retiros capturada");
		labels.add("Retiros rechazada");
		labels.add("Retiros previos liquidados");
		labels.add("Retiros liquidados");
		labels.add("Retiros liquidados por mes");

		data.setLabels(labels);
		barModel1_2.setData(data);

		// Options
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

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Retiros totales por estatus");
		title.setFontSize(15);
		options.setTitle(title);

		barModel1_2.setOptions(options);
	}

	public void createBarModel2_1() {
		barModel2_1 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("IVRT");
		barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
		barDataSet.setBorderColor("rgb(255, 99, 132)");
		barDataSet.setBorderWidth(1);
		List<Number> values = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values.add(x.getIVRT());
		});
		barDataSet.setData(values);

		BarChartDataSet barDataSet2 = new BarChartDataSet();
		barDataSet2.setLabel("Negativa de pensión");
		barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
		barDataSet2.setBorderColor("rgba(255, 159, 64)");
		barDataSet2.setBorderWidth(1);
		List<Number> values2 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values2.add(x.getNEG_PEN());
		});
		barDataSet2.setData(values2);

		BarChartDataSet barDataSet3 = new BarChartDataSet();
		barDataSet3.setLabel("SAR por negativa de pensión");
		barDataSet3.setBackgroundColor("rgba(138, 226, 176, 0.2)");
		barDataSet3.setBorderColor("rgb(138, 226, 176)");
		barDataSet3.setBorderWidth(1);
		List<Number> values3 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values3.add(x.getSAR_X_NEG());
		});
		barDataSet3.setData(values3);

		BarChartDataSet barDataSet4 = new BarChartDataSet();
		barDataSet4.setLabel("SAR");
		barDataSet4.setBackgroundColor("rgb(255, 126, 194,0.2)");
		barDataSet4.setBorderColor("rgb(255,126, 194)");
		barDataSet4.setBorderWidth(1);
		List<Number> values4 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values4.add(x.getSAR());
		});
		barDataSet4.setData(values4);

		BarChartDataSet barDataSet5 = new BarChartDataSet();
		barDataSet5.setLabel("Desempleo");
		barDataSet5.setBackgroundColor("rgb(255, 205, 86,0.2)");
		barDataSet5.setBorderColor("rgb(255, 205, 86)");
		barDataSet5.setBorderWidth(1);
		List<Number> values5 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values5.add(x.getDESEMPLEO());
		});
		barDataSet5.setData(values5);

		BarChartDataSet barDataSet6 = new BarChartDataSet();
		barDataSet6.setLabel("Matrimonio");
		barDataSet6.setBackgroundColor("rgb(255, 93, 107,0.2)");
		barDataSet6.setBorderColor("rgb(255, 93, 107)");
		barDataSet6.setBorderWidth(1);
		List<Number> values6 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values6.add(x.getMATRIMONIO());
		});
		barDataSet6.setData(values6);

		BarChartDataSet barDataSet7 = new BarChartDataSet();
		barDataSet7.setLabel("Francción AFORE");
		barDataSet7.setBackgroundColor("rgb(75, 192, 192,0.2)");
		barDataSet7.setBorderColor("rgb(75, 192, 192)");
		barDataSet7.setBorderWidth(1);
		List<Number> values7 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values7.add(x.getFRACC_AFORE());
		});
		barDataSet7.setData(values7);

		BarChartDataSet barDataSet8 = new BarChartDataSet();
		barDataSet8.setLabel("Francción AFORE B");
		barDataSet8.setBackgroundColor("rgb(184, 174, 213,0.2)");
		barDataSet8.setBorderColor("rgb(184, 174, 213)");
		barDataSet8.setBorderWidth(1);
		List<Number> values8 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values8.add(x.getFRACC_AFORE_B());
		});
		barDataSet8.setData(values8);

		BarChartDataSet barDataSet9 = new BarChartDataSet();
		barDataSet9.setLabel("Retiro al 2%");
		barDataSet9.setBackgroundColor("rgb(75, 192, 192,0.2)");
		barDataSet9.setBorderColor("rgb(75, 192, 192)");
		barDataSet9.setBorderWidth(1);
		List<Number> values9 = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			values9.add(x.getRETIRO_2_PORC());
		});
		barDataSet9.setData(values9);

		data.addChartDataSet(barDataSet);
		data.addChartDataSet(barDataSet2);
		data.addChartDataSet(barDataSet3);
		data.addChartDataSet(barDataSet4);
		data.addChartDataSet(barDataSet5);
		data.addChartDataSet(barDataSet6);
		data.addChartDataSet(barDataSet7);
		data.addChartDataSet(barDataSet8);
		data.addChartDataSet(barDataSet9);

		List<String> labels = new ArrayList<>();
		datosDetallesParciales.forEach(x -> {
			labels.add(x.getESTATUS());
		});
		data.setLabels(labels);
		barModel2_1.setData(data);

		// Options
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

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Tipo de retiros parciales");
		title.setFontSize(15);
		options.setTitle(title);

		barModel2_1.setOptions(options);
	}

	public void createBarModel2_2() {
		barModel2_2 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("IVRT");
		barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
		barDataSet.setBorderColor("rgb(255, 99, 132)");
		barDataSet.setBorderWidth(1);
		List<Number> values = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values.add(x.getIVRT());			
		});
		barDataSet.setData(values);

		BarChartDataSet barDataSet2 = new BarChartDataSet();
		barDataSet2.setLabel("Negativa de pensión");
		barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
		barDataSet2.setBorderColor("rgba(255, 159, 64)");
		barDataSet2.setBorderWidth(1);
		List<Number> values2 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values2.add(x.getNEG_PEN());			
		});
		barDataSet2.setData(values2);

		BarChartDataSet barDataSet3 = new BarChartDataSet();
		barDataSet3.setLabel("SAR por negativa de pensión");
		barDataSet3.setBackgroundColor("rgba(138, 226, 176, 0.2)");
		barDataSet3.setBorderColor("rgb(138, 226, 176)");
		barDataSet3.setBorderWidth(1);
		List<Number> values3 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values3.add(x.getSAR_X_NEG());			
		});
		barDataSet3.setData(values3);

		BarChartDataSet barDataSet4 = new BarChartDataSet();
		barDataSet4.setLabel("SAR");
		barDataSet4.setBackgroundColor("rgb(255, 126, 194,0.2)");
		barDataSet4.setBorderColor("rgb(255,126, 194)");
		barDataSet4.setBorderWidth(1);
		List<Number> values4 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values4.add(x.getSAR());			
		});
		barDataSet4.setData(values4);

		BarChartDataSet barDataSet5 = new BarChartDataSet();
		barDataSet5.setLabel("Desempleo");
		barDataSet5.setBackgroundColor("rgb(255, 205, 86,0.2)");
		barDataSet5.setBorderColor("rgb(255, 205, 86)");
		barDataSet5.setBorderWidth(1);
		List<Number> values5 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values5.add(x.getDESEMPLEO());			
		});
		barDataSet5.setData(values5);

		BarChartDataSet barDataSet6 = new BarChartDataSet();
		barDataSet6.setLabel("Matrimonio");
		barDataSet6.setBackgroundColor("rgb(255, 93, 107,0.2)");
		barDataSet6.setBorderColor("rgb(255, 93, 107)");
		barDataSet6.setBorderWidth(1);
		List<Number> values6 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values6.add(x.getMATRIMONIO());			
		});
		barDataSet6.setData(values6);

		BarChartDataSet barDataSet7 = new BarChartDataSet();
		barDataSet7.setLabel("Francción AFORE");
		barDataSet7.setBackgroundColor("rgb(75, 192, 192,0.2)");
		barDataSet7.setBorderColor("rgb(75, 192, 192)");
		barDataSet7.setBorderWidth(1);
		List<Number> values7 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values7.add(x.getFRACC_AFORE());			
		});
		barDataSet7.setData(values7);

		BarChartDataSet barDataSet8 = new BarChartDataSet();
		barDataSet8.setLabel("Francción AFORE B");
		barDataSet8.setBackgroundColor("rgb(184, 174, 213,0.2)");
		barDataSet8.setBorderColor("rgb(184, 174, 213)");
		barDataSet8.setBorderWidth(1);
		List<Number> values8 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values8.add(x.getFRACC_AFORE_B());			
		});
		barDataSet8.setData(values8);

		BarChartDataSet barDataSet9 = new BarChartDataSet();
		barDataSet9.setLabel("Retiro al 2%");
		barDataSet9.setBackgroundColor("rgb(75, 192, 192,0.2)");
		barDataSet9.setBorderColor("rgb(75, 192, 192)");
		barDataSet9.setBorderWidth(1);
		List<Number> values9 = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				values9.add(x.getRETIRO_2_PORC());			
		});
		barDataSet9.setData(values9);

		data.addChartDataSet(barDataSet);
		data.addChartDataSet(barDataSet2);
		data.addChartDataSet(barDataSet3);
		data.addChartDataSet(barDataSet4);
		data.addChartDataSet(barDataSet5);
		data.addChartDataSet(barDataSet6);
		data.addChartDataSet(barDataSet7);
		data.addChartDataSet(barDataSet8);
		data.addChartDataSet(barDataSet9);

		List<String> labels = new ArrayList<>();
		datosDetallesTotales.forEach(x -> {			
				labels.add(x.getESTATUS());			
		});
		data.setLabels(labels);
		barModel2_2.setData(data);

		// Options
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

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Tipo de retiros totales");
		title.setFontSize(15);
		options.setTitle(title);

		barModel2_2.setOptions(options);
	}

	public void itemSelect(ItemSelectEvent event) {		
		if (event.getItemIndex() == 0) {
			datosDetalles = datosDetallesParciales;
			display =  "none";
			display2 = "inline";
			display3=  "inline";
			display4=  "none";
		} else {
			datosDetalles = datosDetallesTotales;
			display =  "none";
			display2 = "inline";
			display3=  "none";
			display4=  "inline";
		}		
	}

	public void itemSelect2(ItemSelectEvent event) {
		datosDetalles = datosDetallesParciales;
		display =  "none";
		display2 = "inline";
		display3=  "inline";
		display4=  "none";	
	}

	public void itemSelect3(ItemSelectEvent event) {
		datosDetalles = datosDetallesTotales;
		display =  "none";
		display2 = "inline";
		display3=  "none";
		display4=  "inline";
	}

	public void regresar() {
		display = "inline";
		display2 = "none";		
		display3=  "none";
		display4=  "none";
	}

	public void cargaTotales() {
		try {
			DatosGraficasTotalesOut result = graficasService.getDatosTotales();
			if (result.getP_ESTATUS() == 1) {
				datosTotales = result.getGraficasTotales();
				datosTotales.forEach(x -> {
					String v = x.getTIPO_RETIRO().replace("_", " ");
					x.setTIPO_RETIRO(v);
				});
			} else {
				if (result.getP_ESTATUS() == 2) {
					GenerarErrorNegocio(result.getP_MENSAJE());
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void cargaDetalles() {
		try {
			DatosGraficasDetalleOut result = graficasService.getDetalleGraficas();
			if (result.getP_ESTATUS() == 1) {			
				datosDetallesTotales = result.getGraficasDetalle().stream()
						.filter(x -> x.getRETIROS().equals("RETIRO_TOTAL")).collect(Collectors.toList());
				datosDetallesTotales.forEach(x -> {
					String v1 = x.getRETIROS().replace("_", " ");
					x.setRETIROS(v1);
					String v2 = x.getESTATUS().replace("_", " ");
					x.setESTATUS(v2);
				});
				datosDetallesParciales = result.getGraficasDetalle().stream()
						.filter(x -> x.getRETIROS().equals("RETIRO_PARCIAL")).collect(Collectors.toList());
				datosDetallesParciales.forEach(x -> {
					String v1 = x.getRETIROS().replace("_", " ");
					x.setRETIROS(v1);
					String v2 = x.getESTATUS().replace("_", " ");
					x.setESTATUS(v2);
				});
				Collections.sort(datosDetallesParciales, (x, y) -> x.getESTATUS().compareToIgnoreCase(y.getESTATUS()));
			} else {
				if (result.getP_ESTATUS() == 2) {
					GenerarErrorNegocio(result.getP_MENSAJE());
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}
}