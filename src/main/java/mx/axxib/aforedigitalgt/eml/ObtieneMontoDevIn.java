package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class ObtieneMontoDevIn {
	private String loteRev;
	private Date fecha;
}

//P_LOTE_REV IN VARCHAR2,
//P_FECHA IN DATE
