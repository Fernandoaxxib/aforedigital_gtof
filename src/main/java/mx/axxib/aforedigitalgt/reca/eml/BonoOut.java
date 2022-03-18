package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Consulta bono pensión
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class BonoOut {
	private Integer estatus;
	private String mensaje;
	
	private Integer edad;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private BigDecimal bonoIni;
	
	private BigDecimal montoPesos;
	private Date fecAcredit;
	private Date fecVenc;
	private BigDecimal valorUDI;
	private BigDecimal ajusteBono;
	
	private BigDecimal montoPesos2;
	private Date fecAjuste;
	private Date fecAjusteVenc;
	private BigDecimal valorAudi;
	private BigDecimal bonoTotal;
	private Integer codCuenta;
}

//P_EDAD OUT NUMBER,
//P_NOMBRE OUT VARCHAR2,
//P_PATERNO OUT VARCHAR2,
//P_MATERNO OUT VARCHAR2,
//P_BONO_INI OUT NUMBER,

//P_MONTO_EN_PESOS OUT NUMBER,
//P_FEC_ACREDITACION OUT DATE,
//P_FEC_VENCIMIENTO  OUT DATE,
//P_VALOR_UDI       OUT NUMBER,
//P_AJUSTE_BONO      OUT NUMBER,

//P_MONT_PESOS    OUT NUMBER,
//P_FEC_AJUSTE       OUT DATE,
//P_FEC_AJUS_VENCIMIENTO OUT DATE,
//P_VALOR_AUDI_AJUSTE   OUT NUMBER,
//P_BONO_TOTAL   OUT NUMBER,
//P_COD_CUENTA   OUT NUMBER,

//P_MENSAJE  OUT VARCHAR2,
//P_ESTATUS  OUT NUMBER); 

