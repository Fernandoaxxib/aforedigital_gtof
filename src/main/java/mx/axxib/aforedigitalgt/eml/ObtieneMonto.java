package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class ObtieneMonto {
	private BigDecimal totVender;
	private BigDecimal valCuota;
	private BigDecimal totCuotas;
	private BigDecimal retiroAforeTIT;
	private BigDecimal retiroAforeMND;
	private BigDecimal totGeneralCuotas;
	private String siefore;
	private String indCuotaRend;
}
	
