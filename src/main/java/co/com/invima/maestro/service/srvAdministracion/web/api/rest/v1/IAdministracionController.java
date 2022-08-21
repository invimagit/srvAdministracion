package co.com.invima.maestro.service.srvAdministracion.web.api.rest.v1;

import co.com.invima.maestro.modeloTransversal.dto.generic.GenericResponseDTO;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAdministracionController {

    ResponseEntity<GenericResponseDTO> listarTablas(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> InformacionTabla(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> ListarCamposTabla(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> actualizarDatosTabla(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> guardarDatosTabla(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> EliminadorLogico( @RequestBody String genericRequestDTO);;

    ResponseEntity<GenericResponseDTO> actualizarInformacionUsuario(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> guardarAsignacionRol(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> AsignacionEliminadoLogico(@RequestBody String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAplicativoRolSistema(Integer idAplicativo);

    ResponseEntity<GenericResponseDTO> consultarEmpresasPorAdministrador(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> AdministrarPersonaPorUsuario(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> AdministrarTablaPorPadre(Integer idPadre);


    ResponseEntity<GenericResponseDTO> guardarAdminTarifa(@RequestBody String genericRequestDTO);


    ResponseEntity<GenericResponseDTO> consultarAdminReglaOAC(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> actualizarAdministarReglaOAC(@RequestBody String genericRequestDTO);


    ResponseEntity<GenericResponseDTO> actualizarAdministrarAvanceTramite(@RequestBody String genericRequestDTO);


	ResponseEntity<GenericResponseDTO> consultarDocumentosTramite(@RequestBody String json);

	ResponseEntity<GenericResponseDTO> guardarDocumentosTramite(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> consultarAdminTerminosLegales(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> actualizarAdministarTerminosLegales(@RequestBody String genericRequestDTO);


    ResponseEntity<GenericResponseDTO> consultarAdminVigenciaTramite(@RequestBody String json);


    ResponseEntity<GenericResponseDTO> actualizarAdministarVigenciaTramite(@RequestBody String genericRequestDTO);

	ResponseEntity<GenericResponseDTO> detalleTipologia(String codigoDocumento);

    ResponseEntity<GenericResponseDTO> consultarAdminEtapa();
    
    ResponseEntity<GenericResponseDTO> consultarAsignacionPosterior(@RequestBody String json);

	ResponseEntity<GenericResponseDTO> guardarAsignacionPosterior(@RequestBody String json);

	ResponseEntity<GenericResponseDTO> administrarDependenciaAsignacionPosterior(@RequestBody String json);



}
