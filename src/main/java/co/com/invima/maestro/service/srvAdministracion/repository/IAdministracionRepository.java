package co.com.invima.maestro.service.srvAdministracion.repository;

import co.com.invima.maestro.modeloTransversal.entities.calendario.CalendarioDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IAdministracionRepository extends JpaRepository<CalendarioDAO, Integer>, JpaSpecificationExecutor<CalendarioDAO> {

    @Procedure("dbo.USP_GuardarDatosTabla_U")
    String actualizarDatosTabla(String json_in);

    @Procedure("USP_GuardarDatosTabla_I")
    String guardarDatosTabla(String json);


    @Procedure("dbo.USP_EliminarDatoTabla_U")
    String EliminadorLogico(String json_in);



    @Procedure("dbo.USP_InformacionUsuario_U")
    String actualizarInformacionUsuario(String json_in);



    @Procedure("dbo.USP_AdministraRol_I")
    String guardarAsignacionRol(String json);


    @Procedure("dbo.USP_AsignacionEliminadoLogico_U")
    String AsignacionEliminadoLogico(String json_in);



    @Procedure("dbo.USP_AdministrarPersonaPorUsuario_S")
    String AdministrarPersonaPorUsuario(String json);



    @Procedure("dbo.USP_AdministrarTarifa_I")
    String guardarAdminTarifa(String json);


    @Procedure("dbo.USP_AdministarReglaOAC_U")
    String actualizarAdministarReglaOAC(String json_in);


    @Procedure("dbo.USP_AdministrarAvanceTramite_U")
    String actualizarAdministrarAvanceTramite(String json_in);
    
    @Procedure("USP_AdministrarDetalleTipologia_S")
    String detalleTipologia(String codigoDocumento);
    
    @Procedure("USP_AdministrarDocumentoAsociadoTramite_S")
    String consultarDocumentosTramite(String json_in);
    
    @Procedure("USP_AdministrarDocumentoAsociadoTramite_I")
    String guardarDocumentosTramite(String json_in);


    @Procedure("dbo.USP_AdministarTerminosLegales_U")
    String actualizarAdministarTerminosLegales(String json_in);


    @Procedure("dbo.USP_AdministarVigenciaTramite_U")
    String actualizarAdministarVigenciaTramite(String json_in);
    
    @Procedure("USP_AdministrarAsignacionPosterior_S")
    String consultarAsignacionPosterior(String json_in);
    
    @Procedure("USP_AdministrarAsignacionPosterior_I")
    String guardarAsignacionPosterior(String json_in);
    
    @Procedure("USP_AdministrarDependenciaAsignacionPosterior_S")
    String administrarDependenciaAsignacionPosterior(String json_in);


}
