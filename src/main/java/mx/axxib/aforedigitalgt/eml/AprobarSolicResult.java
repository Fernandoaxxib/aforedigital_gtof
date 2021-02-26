package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AprobarSolicResult {

	private String ocIndAccion;        
	private String ocGlobalError;    
	private String ocGlobalExito;    
	private String ocGlobalSistProc;
	private String ocGlobalAbrevProc;
	private String ocNombreAplicacion;
	private String ocMensaje;
	private List<ProcesMonitorOut> listaProceso;
	private Integer on_estatus;
}
