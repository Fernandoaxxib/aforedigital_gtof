package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass

public class DatosSolicitudIn {
	private String nss;
}

//P_NSS IN 	IN	VARCHAR2