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


public class ListaCancelacionOut {
	private Integer estatus;
	private String mensaje;
	private List<ListaCancelacion> datos;
}
//CLAVE_PROCESO	NUMBER
//DESCRIPCION	VARCHAR2

  