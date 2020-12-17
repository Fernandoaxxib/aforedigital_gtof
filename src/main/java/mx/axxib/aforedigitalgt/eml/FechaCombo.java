package mx.axxib.aforedigitalgt.eml;

public class FechaCombo {

	
	private int id;
	private String mes;	
	private String fecha;
	
	public FechaCombo() {}

	public FechaCombo(int id, String mes, String fecha) {
		super();
		this.id = id;
		this.mes = mes;
		this.fecha = fecha;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getMes() {
		return mes;
	}



	public void setMes(String mes) {
		this.mes = mes;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


    @Override
	public String toString() {
		return fecha;
	}
	
	
	
}
