package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPunteoOut {	
	private String ic_Nss;
    private String ic_Aceptado;
    private String ic_Restantes;
    private String ic_Casos;
    private String ic_nss_afil;
    private String ic_rfc_afil;
    private String ic_nombre_afil;
    private String ic_cod_estado_afil;
    private String ic_tip_solicitud_afil;	
	private String oc_Mensaje;
	private Integer on_Estatus;
	private List<DatosPunteoOut> datosPunteo;	
}
