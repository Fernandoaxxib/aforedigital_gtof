package mx.axxib.aforedigitalgt.eml;


import java.util.Date;
import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ConsultaResolucionesNombreOut {
	private Long p_CUENTA;
	private String p_NSS_TRABAJADOR;
    private String p_NOMBRE;
    private String p_CURP;
    private String p_NOMBRE_DATAMART;
    private String p_NOMBRE_AFORE;
    private String p_PATERNO_AFORE;
    private String p_MATERNO_AFORE;
    private String p_derecho;
    private String p_desc_derecho;
    private List <ConsultaResolucionDataMartOut> cursor;
    private String p_MENSAJE;
    private String p_ESTATUS;
}
