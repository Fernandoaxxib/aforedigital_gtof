package mx.axxib.aforedigitalgt.eml;

import java.util.List;

import lombok.Data;

@Data
public class PermisoResult {
	private String mensaje;
	private List<Permiso> datos;
} 