package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "LlenaInfo", 
				classes = { @ConstructorResult(targetClass = LlenaInfo.class, 
					columns = {
						@ColumnResult(name = "NUM_SOLICITUD", type = Integer.class),
						@ColumnResult(name = "COD_CUENTA", type = String.class),
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "PESOS", type = Integer.class),
						@ColumnResult(name = "ACCIONES", type = Integer.class),
						@ColumnResult(name = "FOLIO_PROCESAR", type = String.class),
						@ColumnResult(name = "FEC_APROBACION", type = Date.class),
						@ColumnResult(name = "P_BENEFICIARIO", type = String.class)						
					})
				})
})

public class LlenaInfo {
	private Integer noSolicitud;
	private String codCuenta;
	private String nss;
	private Integer pesos;
	private Integer acciones;
	private String folioProcesar;
	private Date fechaAprobacion;
	private String beneficiario;
}
//NUM_SOLICITUD	NUMBER
//COD_CUENTA	VARCHAR2
//NSS	VARCHAR2
//PESOS	NUMBER
//ACCIONES	NUMBER
//FOLIO_PROCESAR	VARCHAR2
//FEC_APROBACION	DATE
//BENEFICIARIO	VARCHAR2

  