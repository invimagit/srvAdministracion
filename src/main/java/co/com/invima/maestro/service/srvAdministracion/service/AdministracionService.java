package co.com.invima.maestro.service.srvAdministracion.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import ch.qos.logback.core.pattern.parser.Parser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.invima.maestro.modeloTransversal.dto.adminPersona.AdminPersonaDTO;
import co.com.invima.maestro.modeloTransversal.dto.generic.GenericResponseDTO;
import co.com.invima.maestro.service.srvAdministracion.commons.Utils;
import co.com.invima.maestro.service.srvAdministracion.commons.converter.AdministracionConverter;
import co.com.invima.maestro.service.srvAdministracion.repository.IAdministracionRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AdministracionService implements IAdministracionService {


    private final IAdministracionRepository iAdministracionRepository;

    private static JSONParser jsonParser = new JSONParser();
    private String driver = "spring.datasource.driver-class-name";
    private String usernamedb = "spring.datasource.username";
    private String password = "spring.datasource.password";
    private String url = "spring.datasource.url";
    private String urlMaestra = "spring.datasource.url.maestra";
    private String schema = "spring.jpa.properties.hibernate.default_schema";
    private String jsonOut = "json_OUT";




    @Autowired
    private Environment env;

    @Autowired
    public AdministracionService(AdministracionConverter administracionConverter, ModelMapper modelMapper, IAdministracionRepository iAdministracionRepository) {

        this.iAdministracionRepository = iAdministracionRepository;
    }

    /**
     * @author Esteban Lara
     */


    @Override
    public ResponseEntity<GenericResponseDTO> listarTablas(String genericRequestDTO) {

        String procedimiento = "dbo.USP_ListarTablas(?,?)";
        try {
            String respuestaTramite;
            Utils.cargarDriver(env.getProperty(driver));
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb),
                    env.getProperty(password),
                    env.getProperty(url),
                    env.getProperty(schema));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);

                cStmt.registerOutParameter(jsonOut, Types.LONGVARCHAR);


                cStmt.execute();

                respuestaTramite = cStmt.getString(jsonOut);
            }
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se Listan Las Tablas";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("retorno");
                JSONArray mensaje = (JSONArray) respuestGenerica3.get("Tablas");
                if (mensaje == null) {
                    respuesta = "No se encontraron registros asociados a los filtros de busqueda";
                    return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(new JSONArray())
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }
                return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);


            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Error  Listando Las Tablas" + descripcion)
                        .objectResponse(new JSONArray()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error Listando Las Tablas:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> InformacionTabla(String genericRequestDTO) {

        String procedimiento = "dbo.USP_InformacionTabla_S(?,?)";
        try {
            String respuestaTramite;
            Utils.cargarDriver(env.getProperty(driver));
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb),
                    env.getProperty(password),
                    env.getProperty(url),
                    env.getProperty(schema));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);

                cStmt.registerOutParameter(jsonOut, Types.LONGVARCHAR);


                cStmt.execute();

                respuestaTramite = cStmt.getString(jsonOut);
            }
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se Listan Las Tablas";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("retorno");
                JSONArray mensaje = (JSONArray) respuestGenerica3.get("datos");
                if (mensaje == null) {
                    respuesta = "No se encontraron registros asociados a los filtros de busqueda";
                    return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(new JSONArray())
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }
                return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);


            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Error  Consultando informacion  de  Las Tablas" + descripcion)
                        .objectResponse(new JSONArray()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error  Consultando informacion  de  Las Tablas:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> ListarCamposTabla(String genericRequestDTO) {

        String procedimiento = "dbo.USP_ListarCamposTabla(?,?)";
        try {
            String respuestaTramite;
            Utils.cargarDriver(env.getProperty(driver));
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb),
                    env.getProperty(password),
                    env.getProperty(url),
                    env.getProperty(schema));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);

                cStmt.registerOutParameter(jsonOut, Types.LONGVARCHAR);


                cStmt.execute();

                respuestaTramite = cStmt.getString(jsonOut);
            }
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se Listan Las Tablas";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("retorno");
                JSONArray mensaje = (JSONArray) respuestGenerica3.get("Campos");
                if (mensaje == null) {
                    respuesta = "No se encontraron registros asociados a los filtros de busqueda";
                    return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(new JSONArray())
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }
                return new ResponseEntity<>(GenericResponseDTO.builder().message(respuesta).objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);


            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Error  Listando Las Tablas" + descripcion)
                        .objectResponse(new JSONArray()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error Listando Las Tablas:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }




    @Override
    public ResponseEntity<GenericResponseDTO> actualizarDatosTabla(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarDatosTabla(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se Listan Las Tablas";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("mensajeConfirmacion");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se Actualiza los datos")
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error actualizando:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> guardarDatosTabla(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.guardarDatosTabla(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se Listan Las Tablas";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("mensajeConfirmacion");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se guarda los datos")
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error guardando los datos:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO>EliminadorLogico(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.EliminadorLogico(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(respuestaTramite);

            //JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            JSONObject respuestGenerica = (JSONObject) json.get(0);
            JSONObject respuestGenerica2 = (JSONObject) respuestGenerica.get("mensaje");
            String codigo = (String) respuestGenerica2.get("codigoConfirmacion");

            if (codigo.equals("200")) {
                String respuesta = "Se eliminan los datos";
                JSONObject respuestGenerica3 = (JSONObject) respuestGenerica.get("mensajeConfirmacion");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se eliminan los datos")
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al eliminar el vinculo:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> actualizarInformacionUsuario(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarInformacionUsuario(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza la Informacion del Usuario").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar la Informacion del Usuario\":  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<GenericResponseDTO> guardarAsignacionRol(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.guardarAsignacionRol(genericRequestDTO);
            org.json.simple.parser.JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se guarda la asignacion del rol").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error actualizando la asignacion del rol :  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> AsignacionEliminadoLogico(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.AsignacionEliminadoLogico(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza la Informacion del la asignacion").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar la Informacion del Usuario\":  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> consultarAplicativoRolSistema(Integer idAplicativo) {
        String procedimiento = "USP_AplicativoRolSistema_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada2 = "idAplicativo";
                cStmt.setInt(datoEntrada2, idAplicativo);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }

            JSONObject json = (JSONObject) parser.parse(personas);
            String mensaje = json.get("mensaje").toString();
            if (mensaje.length() > 2) {
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .message("se consultan todos los registros")
                        .objectResponse(json.get("mensaje"))
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .message("no se encontraron registros")
                        .objectResponse(json.get("mensaje"))
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .message("Error consultando la persona:  " + e.getMessage())
                    .objectResponse(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> consultarEmpresasPorAdministrador(String genericRequestDTO) {
        String procedimiento = "USP_ConsultarEmpresasAdministrador_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta la Persona: ").objectResponse(json)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando la Persona:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }




    @Override
    public ResponseEntity<GenericResponseDTO> AdministrarPersonaPorUsuario(String json) {

        try {
            String respuesta;
            JSONParser parser = new JSONParser();

            Hashtable env = new Hashtable();
            env.put(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(DirContext.SECURITY_AUTHENTICATION, "simple");
            env.put(DirContext.PROVIDER_URL, "ldap://192.168.10.12:389");
            //env.put(DirContext.PROVIDER_URL, "Ldap://172.16.10.12:389");
            env.put(DirContext.SECURITY_PRINCIPAL, "CN=accesoldap,CN=Users,DC=invima,DC=loc");
            env.put(DirContext.SECURITY_CREDENTIALS, "}!E^tHDN4qZ)");
            // env.put(DirContext.PROVIDER_URL, "ldap://192.168.1.12:389");
            // env.put(DirContext.SECURITY_PRINCIPAL, "cn=administrator,cn=users,dc=testad,dc=com");
            // env.put(DirContext.SECURITY_CREDENTIALS, "Mangata90.,.");

            DirContext dirContext = null;

            DirContext ctx;
            try {
                dirContext = new InitialDirContext(env);
                AdminPersonaDTO  adminPersonaDTO = new AdminPersonaDTO();
                JSONObject respuestaLDAP = null;
                JSONObject obj = new JSONObject();
                List<AdminPersonaDTO> dtoList = new ArrayList<>();
                String codigo  = null;
                JSONArray mensaje= null;
                JSONObject peticion = (JSONObject) parser.parse(json);
                JSONArray jsonOut = (JSONArray) peticion.get("persona");
                JSONObject objOperacion = (JSONObject) peticion.get("objOperacion");
                String usuarioRed = (String) objOperacion.get("usuarioRed");
                String searchBase = "OU=ProySoaint,OU=Sede Principal,OU=Bogota,DC=invima,DC=loc";
                String searchFilter = "(mail=" + usuarioRed + ")";

                SearchControls searchControls = new SearchControls();
                searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                NamingEnumeration objs = dirContext.search(searchBase, searchFilter, searchControls);

                if (objs.hasMoreElements()) {
                    List<Map<String, Object>> persona = new ArrayList<>();
                    while (objs.hasMoreElements())  {
                        respuestaLDAP = new JSONObject();
                        SearchResult match = (SearchResult) objs.nextElement();
                        Attributes attrs = match.getAttributes();

                        Attribute firstN = attrs.get("givenName");
                        String firstName = (String) firstN.get();
                        System.out.println(firstN);
                        Attribute lastN = attrs.get("sn");
                        String lastName = (String) lastN.get();

                        Attribute email = attrs.get("userPrincipalName");
                        System.out.println(email);
                        String correo = (String) email.get();


                        adminPersonaDTO.setPrimerNombre(firstName);
                        adminPersonaDTO.setPrimerApellido(lastName);
                        adminPersonaDTO.setCorreoElectronico(correo);
                        adminPersonaDTO.setSource("LDAP");

                        System.out.println(adminPersonaDTO);
                        dtoList.add(adminPersonaDTO);
                        System.out.println(dtoList);
                       // obj.put("persona", dtoList);
                        /*
                        respuestaLDAP.put("primerNombre", firstName);
                        respuestaLDAP.put("apelllido", lastName);
                        respuestaLDAP.put("correo", correo);
                         */

                    }

                /*    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta usuario exitosamente: ")
                            .objectResponse(respuestaLDAP)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);*/
                } //else {
                    respuesta = iAdministracionRepository.AdministrarPersonaPorUsuario(json);
                    JSONObject jsonResponse = (JSONObject) parser.parse(respuesta);

                    JSONObject respuestaRespuesta = (JSONObject) jsonResponse.get("respuesta");
                    codigo = (String) respuestaRespuesta.get("codigo");
                    mensaje = (JSONArray) jsonResponse.get("persona");
                    System.out.println(respuestaLDAP);
                   /*
                    if (codigo.equals("00") && mensaje != null) {
                        return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta usuario exitosamente ")
                                .objectResponse(mensaje)
                                .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

                    } else {
                        return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta usuario y no se encuentran coincidencias: ")
                                .objectResponse(mensaje)
                                .statusCode(HttpStatus.OK.value()).build(), HttpStatus.BAD_REQUEST);
                    }*/
              //  }
                //se realiza validaciones
                if (respuestaLDAP ==null){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("No existe en el Directorio activo: ")
                            .objectResponse(dtoList)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }
                else if (codigo.equals("00") && mensaje != null) {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta usuario exitosamente ")
                            .objectResponse(mensaje)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);}

                else{
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta usuario exitosamente ")
                            .objectResponse(dtoList)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);}
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error buscando información:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);

        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> AdministrarTablaPorPadre(Integer idPadre) {
        String procedimiento = "USP_AdministrarTablaPorPadre_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada2 = "idTablaPadre_IN";
                cStmt.setInt(datoEntrada2, idPadre);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta la Persona: ").objectResponse(json)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando la Persona:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> guardarAdminTarifa(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.guardarAdminTarifa(genericRequestDTO);
            org.json.simple.parser.JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                String existe = (String) respuestGenerica.get("mensaje");
                if (!existe.equals("OK")){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("existe").objectResponse(existe)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se guarda tarifa ")
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error actualizando la Administracion de la  tarifa :  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> consultarAdminReglaOAC(String genericRequestDTO) {
        String procedimiento = "USP_ConsultarReglaOAC_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            JSONArray mensaje = (JSONArray) json.get("mensaje");

            if (codigo.equals("00")) {
                if (mensaje==null){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("No existe en la base de datos").objectResponse(mensaje)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta la Regla: ").objectResponse(json)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);                }
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando la Regla:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> actualizarAdministarReglaOAC(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarAdministarReglaOAC(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza la Regla OAC").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar la Regla OAC\":  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<GenericResponseDTO> detalleTipologia(String codigoDocumento) {
        try {

            String repository = iAdministracionRepository.detalleTipologia(codigoDocumento);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(repository);
            JSONArray mensaje = (JSONArray) json.get("mensaje");
            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            String descripcion = (String) respuestGenerica.get("mensaje");
            JSONObject obj = new JSONObject();
            if (codigo.equals("00")) {
            	obj = (JSONObject) mensaje.get(0);
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Operacion Exitosa").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {                
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error, no se pudo encontrar:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> actualizarAdministrarAvanceTramite(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarAdministrarAvanceTramite(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza el avance del Tramite").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar el avance del Tramite:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<GenericResponseDTO> consultarDocumentosTramite(String genericRequestDTO) {
        try {

            String repository = iAdministracionRepository.consultarDocumentosTramite(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(repository);
            JSONArray mensaje = (JSONArray) json.get("mensaje");
            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            String descripcion = (String) respuestGenerica.get("mensaje");
            JSONObject obj = new JSONObject();
            if (codigo.equals("00")) {
            	obj = (JSONObject) mensaje.get(0);
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Operacion Exitosa").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {                
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error en la consulta:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

	@Override
    public ResponseEntity<GenericResponseDTO> guardarDocumentosTramite(String genericRequestDTO) {
        try {
            String repository = iAdministracionRepository.guardarDocumentosTramite(genericRequestDTO);
            JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(repository);

			JSONObject respuestGenerica = (JSONObject) json.get("respuesta");

			String codigo = (String) respuestGenerica.get("codigo");
			String descripcion = (String) respuestGenerica.get("mensaje");
			JSONObject mensaje = (JSONObject) json.get("mensaje");

			if (codigo.equals("00") && mensaje != null) {
				return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion).objectResponse(mensaje)
						.statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
			} else if (codigo.equals("00") && mensaje == null) {
				return new ResponseEntity<>(GenericResponseDTO.builder().message("No se encontraron coincidencias")
						.objectResponse(mensaje).statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion).objectResponse(mensaje)
						.statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					GenericResponseDTO.builder().message("Error, no se encontro coincidencias: " + e.getMessage())
							.objectResponse(new JSONObject()).statusCode(HttpStatus.BAD_REQUEST.value()).build(),
					HttpStatus.BAD_REQUEST);
		}
	}


    @Override
    public ResponseEntity<GenericResponseDTO> consultarAdminTerminosLegales(String genericRequestDTO) {
        String procedimiento = "USP_ConsultarAdminTerminosLegales_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            JSONArray mensaje = (JSONArray) json.get("mensaje");

            if (codigo.equals("00")) {
                if (mensaje==null){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("No existe en la base de datos").objectResponse(mensaje)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta los terminos legales: ").objectResponse(json)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);                }
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando los terminos legales:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> actualizarAdministarTerminosLegales(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarAdministarTerminosLegales(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza los terminos legales").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar los terminos legales:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResponseDTO> consultarAdminVigenciaTramite(String genericRequestDTO) {
        String procedimiento = "USP_ConsultarAdminVigenciaTramite_S(?,?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                String datoEntrada = "@json_IN";
                cStmt.setString(datoEntrada, genericRequestDTO);
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            JSONArray mensaje = (JSONArray) json.get("mensaje");

            if (codigo.equals("00")) {
                if (mensaje==null){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("No existe en la base de datos").objectResponse(mensaje)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta la Vigencia: ").objectResponse(json)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);                }
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando la Vigencia:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<GenericResponseDTO> actualizarAdministarVigenciaTramite(String genericRequestDTO) {
        try {

            String respuestaTramite = iAdministracionRepository.actualizarAdministarVigenciaTramite(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respuestaTramite);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");

            if (codigo.equals("00")) {
                JSONObject mensaje = (JSONObject) json.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Se actualiza la vigencia").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error al actualizar la vigencia:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> consultarAdminEtapa() {

        String procedimiento = "USP_ConsultarAdminEtapa_S(?)";
        try {
            JSONParser parser = new JSONParser();


            Utils.cargarDriver(env.getProperty(driver));
            String personas;
            try (Connection conn = Utils.realizarConexion(env.getProperty(usernamedb), env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url")
                    , env.getProperty("spring.jpa.properties.hibernate.default_schema"));
                 CallableStatement cStmt = conn.prepareCall("{call " + procedimiento + "}")) {
                cStmt.registerOutParameter("json_OUT", Types.LONGVARCHAR);
                cStmt.execute();
                personas = cStmt.getString("json_OUT");
            }


            JSONObject json = (JSONObject) parser.parse(personas);

            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            JSONArray mensaje = (JSONArray) json.get("mensaje");

            if (codigo.equals("00")) {
                if (mensaje==null){
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("No existe en la base de datos").objectResponse(mensaje)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(GenericResponseDTO.builder().message("Se consulta las Etapas: ").objectResponse(json)
                            .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);                }
            }else {
                String descripcion = (String) respuestGenerica.get("mensaje");
                return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion)
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error consultando las Etapas:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<GenericResponseDTO> consultarAsignacionPosterior(String genericRequestDTO) {
        try {

            String repository = iAdministracionRepository.consultarAsignacionPosterior(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(repository);
            JSONArray mensaje = (JSONArray) json.get("mensaje");
            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            String descripcion = (String) respuestGenerica.get("mensaje");
            JSONObject obj = new JSONObject();
            if (codigo.equals("00") && mensaje != null) {
            	obj = (JSONObject) mensaje.get(0);
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Operacion Exitosa").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {                
                return new ResponseEntity<>(GenericResponseDTO.builder().message("No se encontraron coincidencias")
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error en la consulta:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<GenericResponseDTO> guardarAsignacionPosterior(String genericRequestDTO) {
        try {
            String repository = iAdministracionRepository.guardarAsignacionPosterior(genericRequestDTO);
            JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(repository);

			JSONObject respuestGenerica = (JSONObject) json.get("respuesta");

			String codigo = (String) respuestGenerica.get("codigo");
			String descripcion = (String) respuestGenerica.get("mensaje");
			JSONObject mensaje = (JSONObject) json.get("mensaje");

			if (codigo.equals("00") && mensaje != null) {
				return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion).objectResponse(mensaje)
						.statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
			} else if (codigo.equals("00") && mensaje == null) {
				return new ResponseEntity<>(GenericResponseDTO.builder().message("No se encontraron coincidencias")
						.objectResponse(mensaje).statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(GenericResponseDTO.builder().message(descripcion).objectResponse(mensaje)
						.statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					GenericResponseDTO.builder().message("Error, no se encontro coincidencias: " + e.getMessage())
							.objectResponse(new JSONObject()).statusCode(HttpStatus.BAD_REQUEST.value()).build(),
					HttpStatus.BAD_REQUEST);
		}
	}
    
    @Override
    public ResponseEntity<GenericResponseDTO> administrarDependenciaAsignacionPosterior(String genericRequestDTO) {
        try {

            String repository = iAdministracionRepository.administrarDependenciaAsignacionPosterior(genericRequestDTO);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(repository);
            JSONArray mensaje = (JSONArray) json.get("mensaje");
            JSONObject respuestGenerica = (JSONObject) json.get("respuesta");
            String codigo = (String) respuestGenerica.get("codigo");
            String descripcion = (String) respuestGenerica.get("mensaje");
            JSONObject obj = new JSONObject();
            if (codigo.equals("00") && mensaje != null) {
            	obj = (JSONObject) mensaje.get(0);
                return new ResponseEntity<>(GenericResponseDTO.builder().message("Operacion Exitosa").objectResponse(mensaje)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            } else {                
                return new ResponseEntity<>(GenericResponseDTO.builder().message("No se encontraron coincidencias")
                        .objectResponse(new JSONObject()).statusCode(HttpStatus.CONFLICT.value()).build(),
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().message("Error en la consulta:  " + e.getMessage())
                    .objectResponse(false).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}