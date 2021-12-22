package mx.axxib.aforedigitalgt.com;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Constantes de la aplicación en su mayoría nombres de paquetes y storeds procedures
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
public class Constantes {

	public static final String USUARIO_PENSION = "ADIGITAL";

	// Monitor de procesos FOMONIT
	public static final String MONITOR_PROCESOS_PACKAGE = "PKG_CONSULTA_MUNITOR";
	public static final String MONITOR_PROCESOS_OBTIENE_MONITOR = "PRC_RET_OBTIENE_MONITOR";
	public static final String MONITOR_PROCESOS_OBTIENE_JOBS = "PRC_RET_OBTIENE_JOBS";
	public static final String MONITOR_PROCESOS_EJECUTA_JOB = "PRC_RET_EJECUTA_JOB";

	// GRAFICAS
	public static final String APRO_SOLIC_PRC_TIPO_TRANSAC = "PRC_TIPO_TRANSAC";
	public static final String APRO_SOLIC_PRC_TIPO_SUBTRAN = "PRC_TIPO_SUBTRAN";
	public static final String PARCIALIDADES_PACKAGE="PKG_PARCIALIDADES";
	public static final String OBTENER_DATOS_GRAFICAS= "PRC_GRAFICAS";
	public static final String OBTENER_DETALLE_GRAFICAS= "PRC_GRAFICAS_DETALLE";

	// Aprobación de solicitudes X tipo de retiro FOAPSORE
	public static final String APRO_SOLIC_TIPO_RETIRO_PACKAGE = "PKG_RET_FOAPSORE";
	public static final String APRO_SOLIC_TIPO_RETIRO_APROBAR_SELECCIONADOS = "PRC_APROBAR_SELECCIONADOS";
	public static final String APRO_SOLIC_TIPO_RETIRO_RECUP_SOLIC_PEND = "PRC_REC_SOLIC_PEND";
	public static final String APRO_SOLIC_TIPO_RETIRO_MONITOR_SESION = "PRC_MONITOR_SESION";
	public static final String APRO_SOLIC_TIPO_RETIRO_OBTIENE_ID_SESION = "PRC_APRUEBA_SOL_RET_PAR";

	// Módulo desempleo parcialidades FOPROUNI
	public static final String MOD_DESEMPLEO_PARCF_PACKAGE = "PKG_RET_FOPROUNI";
	public static final String MOD_DESEMPLEO_PARCF_PROCESO_FEC_INI = "PRC_PROCESO_FECHA_INICIAL";
	public static final String MOD_DESEMPLEO_PARCF_DET_SAL = "PRC_PROCESO_BOTON_DETSAL";
	public static final String MOD_DESEMPLEO_PARCF_GUARDAR_DET_SAL = "PRC_BOTON_GUARDA_DETSAL";
	public static final String MOD_DESEMPLEO_PARCF_EJECUTAR_PROCESO = "PRC_PROCESO_BOTON_EJECUTAR";
	public static final String MOD_DESEMPLEO_PARCF_PROCESAR_REPORTES = "PRC_REPORTES_BOTON_PROCESAR";
	public static final String MOD_DESEMPLEO_PARCF_LAYOUT_BTN_LOTES = "PRC_LAYOUT_BOTON_LOTES";
	public static final String MOD_DESEMPLEO_PARCF_LAYOUT_BTN_GENERAR = "PRC_LAYOUT_BOTON_GENERAR";

	// Interfaz del módulo de pagos FOINTFA1
	public static final String MOD_PAGOS_PACKAGE = "PKG_RET_FOINTFA1";
	public static final String MOD_PAGOS_BOTON_REFRESH = "PRC_BOTON_REFRESH";
	public static final String MOD_PAGOS_INTERFACE_FONDOS = "PRC_GENERA_INTERFACE_FONDOS";
	public static final String MOD_PAGOS_INTERFACE_PAGOS = "PRC_GENERA_INTERFACE_PAGOS";

	// Modo Desempleo Parcialidad FOCEPARC
	public static final String MOD_DESEMPLEO_PARC_PACKAGE = "PKG_RET_FUSION_FODEPARCE";
	public static final String MOD_DESEMPLEO_PARC_DATOS_SOLICITUD = "PRC_DATOS_SOLICITUD";
	public static final String MOD_DESEMPLEO_PARC_CARGA_PARCIALIDADES = "PRC_CARGA_PARCIALIDADES";
	public static final String MOD_DESEMPLEO_PARC_RETPAR_DETA = "PRC_RETPAR_DETA";
	public static final String MOD_DESEMPLEO_PARC_MARCAS = "PRC_MARCAS";
	public static final String MOD_DESEMPLEO_PARC_LISTA_CANCELACION = "PRC_LISTA_CANCELACION";
	public static final String MOD_DESEMPLEO_PARC_CANCELAR_SOLICITUD = "PRC_CANCELAR_SOLICITUD";
	public static final String MOD_DESEMPLEO_PARC_CANCELACION_MASIVA = "PRC_FOCNCPRC_P_CANCEL_SOLIC";

	// Venta de titulos por sector de inversión FOVENTIT
	public static final String VENTA_TITULOS_PACKAGE = "PKG_RET_FOVENTIT";
	public static final String VENTA_TITULOS_OBTIENE_MONTO_TOTAL = "PRC_RET_OBTIENE_MONTO_TOTAL";
	public static final String VENTA_TITULOS_OBTENER_TIPO_RETIRO = "PRC_RET_OBTENER_TIPO_RETIRO";
	public static final String VENTA_TITULOS_OBTIENE_MONTO_RETIRO = "PRC_RET_OBTIENE_MONTO_RETIRO";
	public static final String VENTA_TITULOS_OBTIENE_MONTO_CORTE = "PRC_RET_OBTIENE_MONTO_CORTE";
	public static final String VENTA_TITULOS_OBTENER_LOTE_TRASPA = "PRC_RET_OBTENER_LOTE_TRASPA";
	public static final String VENTA_TITULOS_OBTIENE_MONTO_TRASPASOS = "PRC_OBTIENE_MONTO_TRASPASOS";
	public static final String VENTA_TITULOS_OBTENER_RG_DEV_EXCES = "PRC_RET_OBTENER_RG_DEV_EXCES";
	public static final String VENTA_TITULOS_OBTIENE_MONTO_DEV = "PRC_RET_OBTIENE_MONTO_DEV";
	public static final String VENTA_TITULOS_VENTA_TITULOS_MONITOR = "PRC_RET_VENTA_TITULOS_MONITOR";
	public static final String VENTA_TITULOS_VENTA_TITULOS_MONITOR_CT = "PRC_VENTA_TITULOS_MONITOR_CT";

	// Notificación de pagos FONOTI16
	public static final String NOTIFICACION_PAGOS_PACKAGE = "PKG_RET_FUSION_FONOTI16";
	public static final String NOTIFICACION_PAGOS_LLENA_INFO = "PRC_LLENA_INFO";
	public static final String NOTIFICACION_PAGOS_ENVIA_FECHA = "PRC_ENVIA_FECHA";
	public static final String NOTIFICACION_PAGOS_EXPORTAR = "PRC_EXPORTAR";

	// Reporte de liquidación FOLIQDIS
	public static final String REPORTE_LIQUIDACION_PACKAGE = "PKG_RET_FOLIQDIS";
	public static final String REPORTE_LIQUIDACION_OBTIENE_PARAMETROS = "PRC_OBTIENE_PARAMETROS";
	public static final String REPORTE_LIQUIDACION_OBTIENE_SIEFORE = "PRC_OBTIENE_SIEFORE";
	public static final String REPORTE_LIQUIDACION_EJECUTA_REPORTE = "PRC_EJECUTA_REPORTE";

	// Carga Masiva de Right Fax FOCAMRIG
	public static final String CARGA_RIGHT_PACKAGE = "PKG_RET_FOCAMRIG";
	public static final String CARGA_RIGHT_OBTIENE_CRUCE_PREVIO = "PRC_CREUCE_PREVIO";
	public static final String CARGA_RIGHT_OBTIENE_CARGA = "PRC_CARGA";

	// VER REPORTE PARCIAL FOVALDSM
	public static final String SALARIO_MINIMO_PACKAGE = "PKG_RET_FOVALDSM";
	public static final String SALARIO_MINIMO_STORED = "PRC_SALARIO_MIN";
	public static final String SALARIO_MINIMO_INSERT_STORED = "PRC_SALARIO_MIN_INSERT";
	public static final String SALARIO_MINIMO_UPDATE_STORED = "PRC_SALARIO_MINIMO_UPD";
	public static final String SALARIO_MINIMO_DELETE_STORED = "PRC_DELETE";
	
	// VER ORDEN DE PAGO FOREPALE
	public static final String ORDEN_PAGO_PACKAGE = "PKG_RET_FUSION_FOREPALE";
	public static final String ORDEN_PAGO_OBTIENE_FECHA_STORED = "PRC_OBTIENE_FECHAS";
	public static final String ORDEN_PAGO_REPORTE_ANTERIOR_STORED = "PRC_GENERA_REPORTE_ANT";
	public static final String ORDEN_PAGO_CREA_REPORTE_STORED = "PRC_REPORTE";
	public static final String ORDEN_PAGO_GENERA_REPORTE_STORED = "PRC_GENERA_REPORTE";
	public static final String ORDEN_PAGO_REPORTE_BACK_STORED = "PRC_GENERA_REPORTE_BACK";

	// Retiros parciales IMSS OP84 FOCARG84
	public static final String RET_PARIMSS_OP84_PACKAGE = "PKG_RET_FOCARG84";
	public static final String RET_PARIMSS_OP84_CARGA_ARCH_OP84 = "PRC_CARGA_ARCHIVO_OPE084";
	public static final String RET_PARIMSS_OP84_BTN_LOTES_OP84 = "PRC_BOTON_LOTES_OP84";
	public static final String RET_PARIMSS_OP84_CONSULTA_OP84 = "PRC_CONSULTA_OP084";
	public static final String RET_PARIMSS_OP84_GENEREA_REPOP84 = "PRC_GENERA_REPORTE_OPE084";
	public static final String RET_PARIMSS_OP84_GENERA_ARCH_OP85 = "PRC_GENERA_ARCHIVO_OPE085";
	public static final String RET_PARIMSS_OP84_CARGA_ARCH_OP86 = "PRC_CARGA_ARCHIVO_OPE086";
	public static final String RET_PARIMSS_OP84_BTN_LOTES_OP85 = "PRC_BOTON_LOTES_OP85";
	public static final String RET_PARIMSS_OP84_GENEREA_REPOP86 = "PRC_GENERA_REPORTE_OPE086";

	// Proceso certificación de inactividad FOPROINA
	public static final String PRO_CERT_INACTIVIDAD_PACKAGE = "PKG_RET_FOPROINA";
	public static final String PRO_CERT_INACTIVIDAD_PROCESO_BTN_EJECUTAR = "PRC_PROCESO_BOTON_EJECUTAR";
	public static final String PRO_CERT_INACTIVIDAD_REPORTES_BTN_PROCESAR = "PRC_REPORTES_BOTON_PROCESAR";
	public static final String PRO_CERT_INACTIVIDAD_BTN_LOTES = "PRC_BOTON_LOTES";
	public static final String PRO_CERT_INACTIVIDAD_LAYOUTPROC_BTN_GENERAR = "PRC_LAYOUTPROC_BOTON_GENERAR";
	public static final String PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_EJECUTAR = "PRC_APPMOVIL_BOTON_EJECUTAR";
	public static final String PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_GENARCH = "PRC_APPMOVIL_BOTON_GENARCH";
	// DESMARCA CONSULTAS ICEFAS FOMARCTA
	public static final String CONSULTAR_TRASPASOS_PACKAGE = "PKG_RET_FOMARCTA";
	public static final String CONSULTAR_TRASPASOS_CURP_STORED = "PRC_CONSULTA_CURP";
	public static final String CONSULTAR_TRASPASOS_NSS_STORED = "PRC_CONSULTA_NSS";
	public static final String CONSULTAR_TRASPASOS_STORED = "PRC_CONSULTA_POR_DATO";

	// Rechazos de solicitudes FORETREC
	public static final String RECHAZOS_SOLICITUDES_PACKAGE = "PKG_RET_FORETREC";
	public static final String RECHAZOS_SOLICITUDES_CONSULTA_RECHAZOS = "prc_rechazos";
	public static final String RECHAZOS_SOLICITUDES_CONSULTA_FOLIO = "PRC_CONSULTA_FOLIO";
	public static final String RECHAZOS_SOLICITUDES_CONSULTA_RESOLUCION = "PRC_CONSULTA_RESOLUCION";
	public static final String RECHAZOS_SOLICITUDES_CONSULTA_NSS = "PRC_CONSULTA_NSS";
	public static final String RECHAZOS_SOLICITUDES_CATALOGO = "PRC_CAT_RECHASOS";
	public static final String RECHAZOS_SOLICITUDES_GENERA_REPORTE = "prc_genera_reporte";
	public static final String RECHAZOS_SOLICITUDES_INGRESAR_RECHAZOS = "PRC_INGRESAR_RECHAZOS";

	// VER DESMARCA MASIVA FODSMCTA
	public static final String DETALLE_DESMARCA_CARGA_MASIVA_PACKAGE = "PKG_RET_FODSMCTA";
	public static final String DETALLE_DESMARCA_CARGA_MASIVA_CREAR_ARCHIVO_STORED = "PRC_CARGA_BOTON_CARGA_ARCHIVO";
	public static final String DETALLE_DESMARCA_CARGA_MASIVA_REVERSA_ARCHIVO_STORED = "PRC_CARGA_BOTON_REVERSA_CARGA";
	public static final String DETALLE_DESMARCA_CUENTAS_MASIVAS_STORED = "PRC_BOTON_DESM_MASIVA_CTAS";
	public static final String DETALLE_DESMARCA_MASIVA_OBTENER_PROCESO_STORED = "PRC_OBTENER_PROCESO";
	public static final String DETALLE_DESMARCA_MASIVA_INDIVIDUAL_CITA_STORED = "PRC_BOTON_DESMARCA_INDIV_CTA";
	public static final String DETALLE_DESMARCA_CONSULTAR_MARCAS_PROCESO_STORED = "PRC_OBTENER_PROCESO";
	public static final String DETALLE_DESMARCA_CONSULTAR_MARCAS_CLAVE_STORED = "PRC_BOTON_CONSULTAR_MARCAS";
	public static final String DETALLE_DESMARCA_CONSULTAR_MARCAS_NSS_STORED = "PRC_BOTON_CONS_MARCAS_NSS";

	// Valores UMA FOVALUMA
	public static final String VALORES_UMA_PACKAGE = "PKG_RET_FOVALUMA";
	public static final String VALORES_UMA_VALORES = "PRC_CAT_UMA";
	public static final String VALORES_UMA_GENERAR_REPORTE = "PRC_GENERA_REPORTE";
	public static final String VALORES_UMA_INSERTAR_UMA = "PRC_SALARIO_UMA_INSERT";
	public static final String VALORES_UMA_ACTUALIZAR_UMA = "PRC_SALARIO_UMA_UPD";
	public static final String VALORES_UMA_ELIMINAR_UMA = "PRC_DELETE_UMA";

	// Diagnóstico de la cuenta individual FOREPMAR
	public static final String DIAGNOSTICO_CUENTA_PACKAGE = "PKG_RET_FOREPMAR";
	public static final String DIAGNOSTICO_CUENTA_OBTIENE_CODCUENTA = "PRC_OBTIENE_COD_CUENTA";
	public static final String DIAGNOSTICO_CUENTA_OBTIENE_TIPOPROCESO = "PRC_OBTIENE_TIPO_PROCESO";
	public static final String DIAGNOSTICO_CUENTA_GENERA_ARCHIVO = "PRC_GENERA_ARCHIVO";
	public static final String DIAGNOSTICO_CUENTA_DESBLOQUEA_CUENTAS = "PRC_DESBLOQUEA_CTAS";
	public static final String DIAGNOSTICO_CUENTA_BLOQUEA_CUENTAS = "PRC_BLOQUEA_CUENTAS";

	// Reinversiones a básicas parcialidades FOREMODE
	public static final String REINVERSION_BASICAS_PARCIALIDADES_PACKAGE = "PKG_RET_FOREMODE";
	public static final String REINVERSION_BASICAS_PARCIALIDADES_BTN_EJECUTAR = "PRC_PROCESO_BOTON_EJECUTAR";

	// Reporte de parciales CONSAR FORPRTPR
	public static final String REPORTE_PARCIALES_CONSAR_PACKAGE = "PKG_RET_FORPRTPR";
	public static final String REPORTE_PARCIALES_CONSAR_BTN_GENERA_REPORTE = "PRC_BOTON_GENERA_REPORTE";
	// VER DESMARCA CONSULTAS MARCAS FOSLDNEG
	public static final String SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE = "PKG_RET_FOSLDNEG";
	public static final String SALDOS_CARGA_IMSS_STORED = "PRC_CARGA_SALDOS_IMSS";
	public static final String SALDOS_CONSULTAR_IMSS_STORED="PRC_REP_SALDOS_IMSS";
	public static final String SALDOS_CARGA_ISSTE_STORED = "PRC_CARGA_SALDOS_ISSS";
	public static final String SALDOS_CONSULTAR_ISSTE_STORED="PRC_REP_SALDOS_ISSS";
	public static final String SALDOS_VOL_NEGATIVO_STORED = "PRC_REP_SALDOS_VOL";
	
	// VER DETALLE CHEQUE FOHISMYD
	public static final String DETALLE_CHEQUE_PACKAGE = "PKG_RET_FOHISMYD";
	public static final String CONSULTA_VER_DETALLE_CHEQUE_STORED = "PRC_OBTIENE_CUENTA";
	public static final String SOLICITUD_VER_DETALLE_CHEQUE_STORED = "PRC_SOL_MAT_DESEMPLE";
	public static final String PAGOS_VER_DETALLE_CHEQUE_STORED = "PRC_PAG_EFECTUADOS";
	

	// Consulta recaudación FOREREVU
	public static final String CONSULTA_RECAUDACION_PACKAGE = "PKG_RET_FOREREVU";
	public static final String CONSULTA_RECAUDACION_CONSULTA_NSS = "CONSULTA_X_NSS";
	public static final String CONSULTA_RECAUDACION_GENERA_REPORTE = "PRC_GENERA_REPORTE";
	
	// VER DETALLE CHEQUE FOPAGOS
	public static final String CONSULTA_FOPAGOS_PACKAGE="PKG_RET_FOPAGOS";
	public static final String CONSULTA_FOPAGOS_NSS_STORED="PRC_LLENA_DATOS";
	
	// Consulta de movimientos actuales FOMOVACT
	public static final String MOVIMIENTOS_ACTUALES_PACKAGE = "PKG_RET_FOMOVACT";
	public static final String MOVIMIENTOS_ACTUALES_OBTIENE_CODCUENTA = "PRC_OBT_COD_CUENTA";
	public static final String MOVIMIENTOS_ACTUALES_LLENA_MOVIMIENTOS = "PRC_LLENA_MOVIMIENTOS";
	public static final String MOVIMIENTOS_ACTUALES_LLENA_DETALLE = "PRC_LLENA_DETALLE";
	
	// Consulta de movimientos históritocs FOMOVHIS
	public static final String MOVIMIENTOS_HISTORICOS_PACKAGE = "PKG_RET_FOMOVHIS";
	public static final String MOVIMIENTOS_HISTORICOS_OBTIENE_CODCUENTA = "PRC_OBTIENE_DATOS_X_NSS";
	public static final String MOVIMIENTOS_HISTORICOS_LLENA_MOVIMIENTOS = "PRC_MOVIMIENTO_HISTORICO";
	public static final String MOVIMIENTOS_HISTORICOS_LLENA_DETALLE = "PRC_DES_PRODUCT";
	
	//VER CONSULTA RESOLUCION DATA MART FOCODTMR
	public static final String CONSULTA_RESOLUCION_DATA_MART_PACKAGE ="PKG_RET_FOCODTMR";
	public static final String CONSULTA_RESOLUCION_DATA_NSS_STORED="PRC_OBTIENE_NOMBRE";
	public static final String CONSULTA_RESOLUCION_DATA_MART_STORED="PRC_DATOS_DATAMART";
	
	// Reporte Solicitud Retiro FOREPSRE
	public static final String REPORTE_SOLICITUD_RETIRO_PACKAGE = "PKG_RET_FOREPSRE";
	public static final String REPORTE_SOLICITUD_RETIRO_TIPO_TRANSACCION = "PRC_SELEC_TIP_TRANSAC_RET";
	public static final String REPORTE_SOLICITUD_RETIRO_SUBTIPO_TRANSACCION = "PRC_SELEC_SUB_TRANSAC_RET";
	public static final String REPORTE_SOLICITUD_RETIRO_REPORTE = "PRC_REP_SOL_CHEQ_RETIRO_P";
	
	// Reinversiones a Siefores Básicas Módulo de Desempleo
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE="PKG_RET_FOREMODE";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_VALOR_CUENTAS="FNC_Valor_Cuentas";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_PROCESO_EJECUTAR="PRC_PROCESO_BOTON_EJECUTAR";
	
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_BTN_GENERA_REP_COMPRA="PRC_COMPRAS_BOTON_GEN_REP";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_BTN_COMPRA="PRC_COMPRAS_BOTON_COMPRA";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_TOTAL_COMPRA="PRC_LLENA_TOTAL_COMPRA";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_LOTES="PRC_LISTA_LOTES";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_DETALLE_COMPRA="PRC_DETALLE_COMPRA";
	
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_BTN_GENERA_REP_VENTA="PRC_VENTAS_BOTON_GEN_REP";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_BTN_VENTA="PRC_VENTAS_BOTON_VENTA";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_TOTAL_VENTA="PRC_LLENA_TOTAL_VENTA";
	public static final String REINVER_BASICAS_MODULO_DESEMPLEO_DETALLE_VENTA="PRC_DETALLE_VENTA";
	
	//SALDOS ACTUALES DEL CLIENTE FOCONCLI, FOSALFEC
	public static final String SALDOS_ACTUALES_CLIENTE_PACKAGE="PKG_RET_FOCONCLI";
	public static final String SALDOS_ACTUALES_CLIENTE_LLENA_SALDOS_BLOQUE="PRC_LLENA_SALDOS_BLOQUE";
	public static final String SALDOS_ACTUALES_CLIENTE_OBTIENEDATOS_NOMBRE="PRC_OBTIENE_DATOS_X_NOMBRE";
	public static final String SALDOS_ACTUALES_CLIENTE_OBTIENEDATOS_NSS="PRC_OBTIENE_DATOS_X_NSS";
	public static final String SALDOS_ACTUALES_CLIENTE_CARGA_SALDOS="PRC_CARGA_SALDOS";
	public static final String SALDOS_ACTUALES_CLIENTE_CARGA_NUEVO_NIVEL="PRC_CARGA_NUEVO_NIVEL";
	
	//DATOS DEL USUARIO
	public static final String DATOS_USUARIO_PACKAGE="PKG_ADMIN_SEC";
	public static final String DATOS_USUARIO_VALIDA_ACCESO="PRC_VALID_ACCESO";
	public static final String DATOS_USUARIO_OBTIENE_DATOS="PRC_LIST_USUARIOS";	
	
	//GENERACIÓN DE MOVIMIENTOS SEMANAS DE COTIZACION IMSS
	public static final String GENERACION_MOVIMIENTOS_COTIZACION_IMSS_PACKAGE="PKG_RECA_FORSCMOV";
	public static final String GENERACION_MOVIMIENTOS_COTIZACION_IMSS_GENERA_MOVIMIENTOS="PRC_GENERA_MOVIMIENTOS";
	public static final String GENERACION_MOVIMIENTOS_COTIZACION_IMSS_GET_DETALLE="PRC_LLENA_DETALLE";  
	
	//MODULO DE ACLARACIONES ESPECIALES
	public static final String ACLARACIONES_ESPECIALES_PACKAGE="PKG_RECA_FOACLARA";
	public static final String ACLARACIONES_ESPECIALES_BTN_CARGAR="PRC_PROC_BOT_CARGAR";
	public static final String ACLARACIONES_ESPECIALES_BTN_PUNTEO_AUT="PRC_PROC_BOT_PUNTEO_AUT";
	public static final String ACLARACIONES_ESPECIALES_CARGA_INFO_PUNTEO="PRC_CARGA_INFO_PUNTEO";
	public static final String ACLARACIONES_ESPECIALES_LLENA_DATOS_PUNTEO="PRC_LLENA_DATOS_PUNTEO";
	public static final String ACLARACIONES_ESPECIALES_PUNTEO_BTN_ACTUALIZAR="PRC_PUNTEO_BOT_ACTUALIZAR";
	public static final String ACLARACIONES_ESPECIALES_CARGA_INFO_HIST="PRC_CARGA_INFO_HIST";
	
	//ACTUALIZA SALDOS Y BONO DE PENSIÓN
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE="PKG_RECA_FOACTISS";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_RECA_GET_LOTE="PRC_RECA_ISS_LOTE";	
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_RECA_GET_SIEFORE="PRC_RECA_ISS_SIEFORE";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_RECA_BTN_EJECUTAR="PRC_RECA_BTN_EJECUTAR";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_BONO_GET_LOTE="PRC_BONO_LOTE";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_BONO_GET_SIEFORE="PRC_BONO_SIEFORE";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_BONO_BTN_EJECUTAR="PRC_BONO_BOTON_EJECUTAR";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_BTN_EJECUTAR="PRC_SEPA_BOTON_EJECUTAR";
	public static final String ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_GET_LOTE="PRC_SEPA_LOTE";
	
	//NOMINA EMPLEADOS GRUPO FINANCIERO BANORTE
	public static final String NOMINA_EMPLEADOS_BANORTE_PACKAGE="PKG_RECA_FODISCUO";
	public static final String NOMINA_EMPLEADOS_BANORTE_GET_LOTE_DISPERSION="PRC_LOTE_DISPERSION";
	public static final String NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION="PRC_BOTON_APLICAR_DISPERSION";
	public static final String NOMINA_EMPLEADOS_BANORTE_GET_LISTA_EMPRESAS="PRC_LISTA_EMPRESAS";
	public static final String NOMINA_EMPLEADOS_BANORTE_GET_LOTES_EMPRESA="PRC_LOTES_EMPRESA";
	public static final String NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION_VOL_EMP="PRC_BOTON_DISPERSION_VOL_EMP";
	
	//LIQUIDAR MOVIMIENTOS
	public static final String LIQUIDAR_MOVIMIENTOS_PACKAGE="PKG_RECA_FORSCLIQ";
	public static final String LIQUIDAR_MOVIMIENTOS_LOTES="PRC_RG_LOTES";
	public static final String LIQUIDAR_MOVIMIENTOS_BUSCAR="PRC_BUSCAR";
	public static final String LIQUIDAR_MOVIMIENTOS_FECHA="PRC_FECHA";
	public static final String LIQUIDAR_MOVIMIENTOS_LIQUIDAR="PRC_RG_LIQUIDAR";
	
	//CENTRO PATRONAL
	public static final String CENTRO_PATRONAL_PACKAGE="FODISIND";
	public static final String CENTRO_PATRONAL_VALIDA_CUENTA="PRC_VALIDA_CUENTA";
	public static final String CENTRO_PATRONAL_LOTE="PRC_LOTE_DISPERSION";
	public static final String CENTRO_PATRONAL_APLICAR="PRC_BOTON_APLICAR";
	
	//LIQUIDAR RENDIMIENTOS
	public static final String LIQUIDAR_RENDIMIENTOS_PACKAGE="PKG_RECA_FOLIQRCV";
	public static final String LIQUIDAR_RENDIMIENTOS_LOTE="PRC_LOTE";
	public static final String LIQUIDAR_RENDIMIENTOS_CONSULTA="PRC_BOTON_CONSULTA";
	public static final String LIQUIDAR_RENDIMIENTOS_LIQUIDAR="PRC_BOTON_LIQUIDA_LOTE";
	
	//RECAUDACION IMSS 
	public static final String RECAUDACION_IMSS_PACKAGE="PKG_RECA_FOMODREC";
	public static final String RECAUDACION_IMSS_PROCESO_LOTE="PRC_LOTE";
	public static final String RECAUDACION_IMSS_PROCESO_EJECUTAR="PRC_PROCESO_BOTON_EJECUTAR";
	public static final String RECAUDACION_IMSS_REPORTE_EJECUTAR="PRC_REPORTES_BOTON_EJECUTAR"; 
	public static final String RECAUDACION_IMSS_RECHAZO_PATRONAL="PRC_RECA_PATRONAL";
	public static final String RECAUDACION_IMSS_RECHAZO_RECHAZAR="PRC_RECHRECA_BOTON_PROC_SELEC";
	




	

}
