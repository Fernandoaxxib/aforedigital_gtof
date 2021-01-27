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
public class ConsultarNombreCuentaIcefasOut {
	
	private String nombre;
	private Integer cuenta;
	private String curp_o_nss;
	private List<CpDatosIcefasOut> cpDatos;
	private String mensaje;
	
	

}
