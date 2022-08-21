package co.com.invima.maestro.service.srvAdministracion.web.api.rest.v1;


import co.com.invima.maestro.modeloTransversal.dto.generic.GenericResponseDTO;
import co.com.invima.maestro.service.srvAdministracion.service.IAdministracionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Administracion")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class AdministracionController implements IAdministracionController {

    private final IAdministracionService service;

    @Autowired
    public AdministracionController(IAdministracionService service) {
        this.service = service;
    }

    @Override
    @PostMapping("/listarTablas")
    @ApiOperation(value = "lista las tablas", notes = "lista las tablas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> listarTablas(@ApiParam(value =
            "el parametro consultar debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"nombreTabla\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.listarTablas(genericRequestDTO);
    }



    @Override
    @PostMapping("/InformacionTabla")
    @ApiOperation(value = "informacion de la tablas", notes = "informacion de la tablas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> InformacionTabla(@ApiParam(value =
            "el parametro consultar debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"idTabla\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.InformacionTabla(genericRequestDTO);
    }

    @Override
    @PostMapping("/ListarCamposTabla")
    @ApiOperation(value = "lista los  campos de las  tablas", notes = "lista los  campos de las  tablas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> ListarCamposTabla(@ApiParam(value =
            "el parametro consultar debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"tabla\":,\n" +
                    "\"esquema\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.ListarCamposTabla(genericRequestDTO);
    }



    @Override
    @PutMapping("/actualizarDatosTabla")
    @ApiOperation(value = "Actualizar Datos De Las  Tabla", notes = "Actualizar Datos De Las  Tabla")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. se actualiza la Empresa Parcial correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarDatosTabla(@ApiParam(type = "Json", value =
            "el json con los dato con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"idTabla\":,\n" +
                    "\"id\":,\n" +
                    "\"campos\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarDatosTabla(genericRequestDTO);
    }



    @Override
    @PostMapping("/guardarDatosTabla/")
    @ApiOperation(value = "guarda los Datos de las Tablas", notes = "guarda los Datos de las Tablas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> guardarDatosTabla(@ApiParam(type = "Integer", value =
            "el json con los dato con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"idTabla\":,\n" +
                    "\"campos\":,\n" +
                    "\"datos\":,\n" +
                    "}\n" +
                    "}", example = "1",required = true) String genericRequestDTO) {
        return service.guardarDatosTabla(genericRequestDTO);
    }

    @Override
    @PutMapping("/EliminadorLogico")
    @ApiOperation(value = "EliminadorLogico", notes = "EliminadorLogico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = co.com.invima.canonico.modeloCanonico.dto.generic.GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> EliminadorLogico(
            @ApiParam(type = "Json", value =    "el json con los dato con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"Parametros\":{\n" +
                    "\"idTabla\":,\n" +
                    "\"id\":,\n" +
                    "}\n" +
                    "}",
                    example = "1", required = true)
            String genericRequestDTO) {
        return service.EliminadorLogico(genericRequestDTO);
    }

    @Override
    @PutMapping("/actualizarInformacionUsuario")
    @ApiOperation(value = "actualiza  Informacion Usuario", notes = "actualiza  Informacion Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarInformacionUsuario(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"registro\":{\n" +
                    "\"idPersona\":,\n" +
                    "\"estadoUsuario\":,\n" +
                    "\"primerNombre\":,\n" +
                    "\"segundoNombre\":,\n" +
                    "\"primerApellido\":,\n" +
                    "\"segundoApellido\":,\n" +
                    "\"idGrupoMisional\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarInformacionUsuario(genericRequestDTO);
    }


    @Override
    @PostMapping("/guardarAsignacionRol/")
    @ApiOperation(value = "Guardar la asignacion del rol ", notes = "guarda  la asignacion del rol")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> guardarAsignacionRol(@ApiParam(type = "Integer", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"registro\":{\n" +
                    "\"idUsuario\":,\n" +
                    "\"idRolSistema\":,\n" +
                    "\"idAplicativo\":,\n" +
                    "\"idInfoAsignacionUsuario\":,\n" +
                    "\"modulo\":,\n" +
                    "\"tipoVinculacion\":,\n" +
                    "\"cargo\":,\n" +
                    "\"codigo\":,\n" +
                    "\"grado\":,\n" +
                    "}\n" +
                    "}", example = "1",required = true) String genericRequestDTO) {
        return service.guardarAsignacionRol(genericRequestDTO);
    }



    @Override
    @PutMapping("/AsignacionEliminadoLogico")
    @ApiOperation(value = "Eliminador logico", notes = "Eliminador logico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> AsignacionEliminadoLogico(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"registro\":{\n" +
                    "\"idInfoAsignacionUsuario\":,\n" +
                    "\"activo\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.AsignacionEliminadoLogico(genericRequestDTO);
    }




    @Override
    @GetMapping("/consultarAplicativoRolSistema/{idAplicativo}")
    @ApiOperation(value = "busca el rol dependiendo los Aplicativos", notes = "busca el rol dependiendo los Aplicativos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})

    public ResponseEntity<GenericResponseDTO> consultarAplicativoRolSistema(@PathVariable Integer idAplicativo) {
        return service.consultarAplicativoRolSistema(idAplicativo);
    }




    @Override
    @PostMapping("/consultarEmpresasPorAdministrador")
    @ApiOperation(value = "Consulta Empresas Por Administrador", notes = "Consulta Empresas Por Administrador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarEmpresasPorAdministrador(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "   \"usuario\":\"jhsanchez\",\n" +
                    "   \"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "   \"correo\":\"naguilar@soaint.com\"\n" +
                    "}\n" +
                    "}", required = true) String json) {
        return service.consultarEmpresasPorAdministrador(json);
    }

    @Override
    @PostMapping("/AdministrarPersonaPorUsuario")
    @ApiOperation(value = "Consulta el la persona por el usuario", notes = "Consulta la persona por el usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> AdministrarPersonaPorUsuario(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "   \"usuario\":\"jhsanchez\",\n" +
                    "   \"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "   \"correo\":\"naguilar@soaint.com\"\n" +
                    "}\n" +
                    "}", required = true) String json) {
        return service.AdministrarPersonaPorUsuario(json);
    }



    @Override
    @GetMapping("/AdministrarTablaPorPadre/{idPadre}")
    @ApiOperation(value = "busca el rol dependiendo los Aplicativos", notes = "busca el rol dependiendo los Aplicativos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})

    public ResponseEntity<GenericResponseDTO> AdministrarTablaPorPadre(@PathVariable Integer idPadre) {
        return service.AdministrarTablaPorPadre(idPadre);
    }




    @Override
    @PostMapping("/guardarAdminTarifa/")
    @ApiOperation(value = "Guardar la tarifa ", notes = "Guardar la tarifa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> guardarAdminTarifa(@ApiParam(type = "Integer", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "\"filtro\":,\n" +
                    "\"idTarifa\":,\n" +
                    "\"activo\":,\n" +
                    "\"tarifaTramite\":{\n" +
                    "   \"idTarifa\":\"\"\n"+
                    "   \"codigoTipoProducto\":\"\"\n"+
                    "   \"codigoGrupoProducto\":\"\"\n"+
                    "   \"codigoSubGrupo\":\"\"\n"+
                    "   \"codigoCategoria\":\"\"\n"+
                    "   \"codigoCategoriaAlimento\":\"\"\n"+
                    "   \"codigoTipoTramite\":\"\"\n"+
                    "   \"codigoSubTipoTramite\":\"\"\n"+
                    "}\n" +
                    "}", example = "1",required = true) String genericRequestDTO) {
        return service.guardarAdminTarifa(genericRequestDTO);
    }

    @Override
    @PostMapping("/consultarAdminReglaOAC")
    @ApiOperation(value = "Consulta ReglaOAC", notes = "Consulta  ReglaOAC")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarAdminReglaOAC(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "   \"usuario\":\"jhsanchez\",\n" +
                    "   \"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "   \"codigoTipoProducto\":\"\"\n" +
                    "   \"codigoGrupoProducto\":\"\"\n" +
                    "   \"codigoTramite\":\"\"\n" +
                    "   \"codigoSubtipoTramite\":\"\"\n" +
                    "}\n" +
                    "}", required = true) String json) {
        return service.consultarAdminReglaOAC(json);
    }


    @Override
    @PutMapping("/actualizarAdministarReglaOAC")
    @ApiOperation(value = "actualiza  ReglaOAC", notes = "actualiza  ReglaOAC")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarAdministarReglaOAC(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"regla\":{\n" +
                    "\"requireRolLegal\":,\n" +
                    "\"requiereRolTecnico\":,\n" +
                    "\"activo\":,\n" +
                    "\"codigoTipoProducto\":,\n" +
                    "\"codigoGrupoProducto\":,\n" +
                    "\"codigoTramite\":,\n" +
                    "\"codigoSubTipoTramite\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarAdministarReglaOAC(genericRequestDTO);
    }




    @Override
    @PutMapping("/actualizarAdministrarAvanceTramite")
    @ApiOperation(value = "actualiza  el avance del tramite", notes = "el avance del tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarAdministrarAvanceTramite(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "\"desde\":,\n" +
                    "\"hasta\":,\n" +
                    "\"idAvanceTramite\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarAdministrarAvanceTramite(genericRequestDTO);
    }
    
    @Override
    @GetMapping("/documentosTramite/detalleTipologia/{codigoDocumento}")
    @ApiOperation(value = "busca el rol dependiendo los Aplicativos", notes = "busca el rol dependiendo los Aplicativos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})

    public ResponseEntity<GenericResponseDTO> detalleTipologia(@PathVariable String codigoDocumento) {
        return service.detalleTipologia(codigoDocumento);
    }
    
    @Override
    @PostMapping("/documentosTramite/consultar")
    @ApiOperation(value = "Consulta los documentos asociados al tramite", notes = "Consulta los documentos asociados al tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarDocumentosTramite(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\r\n"
                    + "	\"objAuditoria\": {\r\n"
                    + "		\"usuario\": \"naguilar@soaint.com\",\r\n"
                    + "		\"ip\": \" 181.49.173.42\"\r\n"
                    + "	},\r\n"
                    + "	\"objOperacion\": {\r\n"
                    + "	  \"idTipoProducto\": 3,\r\n"
                    + "	  \"codigoSubGrupoProducto\": \"SubGr0\",\r\n"
                    + "      \"codigoGrupoProducto\": \"Grupo13\",\r\n"
                    + "	  \"codigoTipoTramite\": \"TT71\",\r\n"
                    + "	  \"codigoModalidad\": \"MOD11\",\r\n"
                    + "	  \"codigoSubTipoTramite\": \"SST165\",\r\n"
                    + "	  \"codigoTipoModificacion\": \"0\",\r\n"
                    + "	  \"codigoTipoDocumental\": \"436\"\r\n"
                    + "\r\n"
                    + "	}\r\n"
                    + "}", required = true) String json) {
        return service.consultarDocumentosTramite(json);
    }
    
	@Override
    @PostMapping("/documentosTramite/guardar")
    @ApiOperation(value = "Consulta los documentos asociados al tramite", notes = "Consulta los documentos asociados al tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> guardarDocumentosTramite(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\r\n"
                    + "  \"objAuditoria\": {\r\n"
                    + "    \"usuario\": \"naguilar@soaint.com\",\r\n"
                    + "    \"ip\": \" 181.49.173.42\"\r\n"
                    + "  },\r\n"
                    + "  \"objOperacion\": [\r\n"
                    + "    {\r\n"
                    + "      \"obligatoriedad\": \"SI\",\r\n"
                    + "      \"actualizar\": 1,\r\n"
                    + "      \"activo\": 1,\r\n"
                    + "	  \"informacionAdicional\": \"XXXXXXX\"\r\n"
                    + "	  \"idTipoProducto\": 1\r\n"
                    + "      \"codigoSubGrupoProducto\": \"SubGr0\",\r\n"
                    + "      \"codigoGrupoProducto\": \"Grupo6\",\r\n"
                    + "      \"codigoTipoTramite\": \"TT69\",\r\n"
                    + "      \"codigoModalidad\": \"MOD9\",\r\n"
                    + "      \"codigoSubTipoTramite\": \"SST165\",\r\n"
                    + "      \"codigoTipoModificacion\": \"0\",\r\n"
                    + "      \"codigoTipoDocumental\": \"463\"\r\n"
                    + "    }\r\n"
                    + "  ]\r\n"
                    + "}}", required = true) String json) {
        return service.guardarDocumentosTramite(json);
    }



    @Override
    @PostMapping("/consultarAdminTerminosLegales")
    @ApiOperation(value = "Consulta Terminos Legales ", notes = "Consulta  Terminos Legales")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarAdminTerminosLegales(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "   \"usuario\":\"jhsanchez\",\n" +
                    "   \"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "   \"idTipoProducto\":\"\"\n" +
                    "   \"codigoSubGrupo\":\"\"\n" +
                    "   \"codigoGrupoProducto\":\"\"\n" +
                    "   \"codigoTipoTramite\":\"\"\n" +
                    "   \"codigoSubTipoTramite\":\"\"\n" +
                    "}\n" +
                    "}", required = true) String json) {
        return service.consultarAdminTerminosLegales(json);
    }


    @Override
    @PutMapping("/actualizarAdministarTerminosLegales")
    @ApiOperation(value = "actualiza  Terminos Legales", notes = "actualiza  Terminos Legales")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarAdministarTerminosLegales(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"termino\":{\n" +
                    "\"idEtapa\":,\n" +
                    "\"cantidad\":,\n" +
                    "\"activo\":,\n" +
                    "\"codUnidadTiempo\":,\n" +
                    "\"codTipoDia\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarAdministarTerminosLegales(genericRequestDTO);
    }


    @Override
    @PostMapping("/consultarAdminVigenciaTramite")
    @ApiOperation(value = "Consulta Vigencia Tramite", notes = "Consulta  Vigencia Tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarAdminVigenciaTramite(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "   \"usuario\":\"jhsanchez\",\n" +
                    "   \"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"objOperacion\":{\n" +
                    "   \"idTipoProducto\":\"\"\n" +
                    "   \"codigoSubGrupo\":\"\"\n" +
                    "   \"codigoGrupoProducto\":\"\"\n" +
                    "   \"codigoTipoTramite\":\"\"\n" +
                    "   \"codigoSubTipoTramite\":\"\"\n" +
                    "}\n" +
                    "}", required = true) String json) {
        return service.consultarAdminVigenciaTramite(json);
    }




    @Override
    @PutMapping("/actualizarAdministarVigenciaTramite")
    @ApiOperation(value = "actualiza  vigencia tramite", notes = "actualiza  vigencia tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> actualizarAdministarVigenciaTramite(@ApiParam(type = "Json", value =
            "el parametro direcciòn  debe ser un json con la siguiente estructura:" +
                    "{\n" +
                    "\"objAuditoria\":{\n" +
                    "\"usuario\":\"jhsanchez\",\n" +
                    "\"ip\":\"192.123.123.1\"\n" +
                    "},\n" +
                    "\"vigencias\":{\n" +
                    "\"idVigenciaTramites\":,\n" +
                    "\"observacion\":,\n" +
                    "\"activo\":,\n" +
                    "\"estado\":,\n" +
                    "\"vigencia\":,\n" +
                    "}\n" +
                    "}", required = true) String genericRequestDTO) {
        return service.actualizarAdministarVigenciaTramite(genericRequestDTO);
    }


    @Override
    @GetMapping("/consultarAdminEtapa")
    @ApiOperation(value = "Consulta las Etapas", notes = "Consulta Etapas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarAdminEtapa() {
        return service.consultarAdminEtapa();
    }
    
    @Override
    @PostMapping("/asignacionPosterior/consultar")
    @ApiOperation(value = "Consulta la asignacion posterior al tramite", notes = "Consulta la asignacion posterior al tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> consultarAsignacionPosterior(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n"
                    + "	\"objAuditoria\": {\n"
                    + "		\"usuario\": \"naguilar@soaint.com\",\n"
                    + "		\"ip\": \" 181.49.173.42\"\n"
                    + "	},\n"
                    + "	\"objOperacion\": {\n"
                    + "		\"tipoProducto\": 3,\n"
                    + "		\"codigoSubGrupoProducto\": \"SubGr0\",\n"
                    + "		\"codigoGrupoProducto\": \"COSME\",\n"
                    + "		\"codigoTipoTramite\": \"TT62\",\n"
                    + "		\"codigoModalidad\": \"SST165\",\n"
                    + "		\"codigoSubTipoTramite\": \"SST165\"\n"
                    + "\n"
                    + "	}\n"
                    + "}", required = true) String json) {
        return service.consultarAsignacionPosterior(json);
    }
    
    @Override
    @PostMapping("/asignacionPosterior/guardar")
    @ApiOperation(value = "Guarda la asignacion posterior al tramite", notes = "Guarda la asignacion posterior al tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> guardarAsignacionPosterior(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n"
                    + "	\"objAuditoria\": {\n"
                    + "		\"usuario\": \"naguilar@soaint.com\",\n"
                    + "		\"ip\": \" 181.49.173.42\"\n"
                    + "	},\n"
                    + "\"objOperacion\": [\n"
                    + "    {\n"
                    + "      \"idAsignacionPermitidaPorRol\": 1,\n"
                    + "      \"codigoTipoProducto\": 1,\n"
                    + "      \"codigoGrupoProducto\": \"Grupo22\",\n"
                    + "      \"codigoTipoTramite\": \"TT151\",\n"
                    + "      \"idGrupoMisional\": 19,\n"
                    + "      \"descripcionGrupoMisional\": \"xxxxxxxx\",\n"
                    + "      \"idRol\": 11,\n"
                    + "      \"descripcionRol\": \"xxxxxxxxx\",\n"
                    + "      \"activo\": true.\n"
                    + "	  \"actualizar\": true\n"
                    + "    }\n"
                    + "  ]\n"
                    + "}", required = true) String json) {
        return service.guardarAsignacionPosterior(json);
    }
    
    @Override
    @PostMapping("/dependenciaAsignacionPosterior/consultar")
    @ApiOperation(value = "Guarda la asignacion posterior al tramite", notes = "Guarda la asignacion posterior al tramite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = GenericResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<GenericResponseDTO> administrarDependenciaAsignacionPosterior(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\r\n"
                    + "	\"objAuditoria\": {\r\n"
                    + "		\"usuario\": \"naguilar@soaint.com\",\r\n"
                    + "		\"ip\": \" 181.49.173.42\"\r\n"
                    + "	},\r\n"
                    + "	\"objOperacion\": {\r\n"
                    + "		\"idTipoProducto\": 3,\r\n"
                    + "		\"tipoDependencia\": \"Grupos\"\r\n"
                    + "\r\n"
                    + "	}\r\n"
                    + "}", required = true) String json) {
        return service.administrarDependenciaAsignacionPosterior(json);
    }
}
