package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import lombok.Data;

@Data
public class ProcesResult {

	private String p_Ruta;
	private String p_Archivo;
	private String oc_Avance;
	private String p_Message;
	private Integer on_Estatus;	
	private List<RegOP84Out> registros;

}
