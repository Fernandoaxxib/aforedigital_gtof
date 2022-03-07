package mx.axxib.aforedigitalgt.reca.eml;

import java.util.List;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Compra Titulos
//** Interventor Principal: JSAS
//** Fecha Creación: 01/Mar/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class CompraTitMontoOut {
	private Integer estatus;
	private String mensaje;
	private List<CompraTitMonto> datos;
}

