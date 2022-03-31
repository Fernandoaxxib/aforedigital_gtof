package mx.axxib.aforedigitalgt.reca.eml;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class DatosInicialesFocapdom {
	@Getter
	@Setter
	private String cod_empresa;
	@Getter
	@Setter
	private String tipo_incremento;
	@Getter
	@Setter
	private Integer comtel_cve_compania;
	@Getter
	@Setter
	Integer estatus_incre;
	@Getter
	@Setter
	private Integer cve_est_sol;
	@Getter
	@Setter
	private String solicitud;
	@Getter
	@Setter
	private String cve_est_reg;
	@Getter
	@Setter
	private String desc_est_reg;
	@Getter
	@Setter
	private Date fec_captura;
	@Getter
	@Setter
	private Integer cve_orig_sol;
	@Getter
	@Setter
	private Integer cve_tipo_persona;
	@Getter
	@Setter
	private String dummy_tipo_persona;
	@Getter
	@Setter
	private Integer cve_tipo_ident;
	@Getter
	@Setter
	private String dummy_tipo_identificacion;
	@Getter
	@Setter
	private Integer cve_orig_aporta;
	@Getter
	@Setter
	private String dummy_origen_aporta;
	@Getter
	@Setter
	private Integer cve_tipo_periodo;
	@Getter
	@Setter
	private String dummy_periodo;
	@Getter
	@Setter
	private String cve_banco;
	@Getter
	@Setter
	private String dummy_banco;
	@Getter
	@Setter
	private String cve_tipo_cta;
	@Getter
	@Setter
	private String dummy_tipo_cuenta;
	@Getter
	@Setter
	private Integer cve_tipo_aporta;
	@Getter
	@Setter
	private Integer cve_tipo_siefore;
	@Getter
	@Setter
	private String creado_por;
	@Getter
	@Setter
	private Date fec_creacion;
	@Getter
	@Setter
	private String modif_por;
	@Getter
	@Setter
	private Date fec_modif;
	@Getter
	@Setter
	private String orig_monto_otros;
	@Getter
	@Setter
	private String id_referencia;
	@Getter
	@Setter
	private String tipo_mov;
	@Getter
	@Setter
	private String dummy_tip_mov;
	@Getter
	@Setter
	private String dummy_comp_celular;
	@Getter
	@Setter
	private Integer cve_benef_fiscal;
	@Getter
	@Setter
	private String dummy_beneficiario_fiscal;
	@Getter
	@Setter
	private String cve_orig_captura;
	@Getter
	@Setter
	private String fecha_cobro;
}
