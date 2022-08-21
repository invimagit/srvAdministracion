package co.com.invima.maestro.service.srvAdministracion.service;

import co.com.invima.maestro.modeloTransversal.dto.generic.GenericRequestDTO;
import co.com.invima.maestro.modeloTransversal.dto.generic.GenericResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;


public interface IAdministracionService {

    ResponseEntity<GenericResponseDTO> listarTablas(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> InformacionTabla(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> ListarCamposTabla(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> actualizarDatosTabla(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> guardarDatosTabla(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> EliminadorLogico(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> actualizarInformacionUsuario(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> guardarAsignacionRol(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> AsignacionEliminadoLogico(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAplicativoRolSistema(Integer idAplicativo);

    ResponseEntity<GenericResponseDTO> consultarEmpresasPorAdministrador(String pJson);

    ResponseEntity<GenericResponseDTO> AdministrarPersonaPorUsuario(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> AdministrarTablaPorPadre(Integer idPadre);

    ResponseEntity<GenericResponseDTO> guardarAdminTarifa(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAdminReglaOAC(String pJson);

    ResponseEntity<GenericResponseDTO> actualizarAdministarReglaOAC(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> actualizarAdministrarAvanceTramite(String genericRequestDTO);


	ResponseEntity<GenericResponseDTO> consultarDocumentosTramite(String genericRequestDTO);

	ResponseEntity<GenericResponseDTO> guardarDocumentosTramite(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAdminTerminosLegales(String pJson);

    ResponseEntity<GenericResponseDTO> actualizarAdministarTerminosLegales(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAdminVigenciaTramite(String pJson);


    ResponseEntity<GenericResponseDTO> actualizarAdministarVigenciaTramite(String genericRequestDTO);

	ResponseEntity<GenericResponseDTO> detalleTipologia(String genericRequestDTO);

    ResponseEntity<GenericResponseDTO> consultarAdminEtapa();
    
    ResponseEntity<GenericResponseDTO> consultarAsignacionPosterior(String genericRequestDTO);

	ResponseEntity<GenericResponseDTO> guardarAsignacionPosterior(String genericRequestDTO);

	ResponseEntity<GenericResponseDTO> administrarDependenciaAsignacionPosterior(String genericRequestDTO);


}
