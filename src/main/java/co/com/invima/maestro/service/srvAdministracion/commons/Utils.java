package co.com.invima.maestro.service.srvAdministracion.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Component
@Slf4j
public class Utils {

    public static void cargarDriver(String claseSQL) {
        try {
            Class.forName(claseSQL);
        } catch (ClassNotFoundException e1) {
            log.error("Error 1: " + e1);
            e1.printStackTrace();
        }
    }

    public static Connection realizarConexion(String user, String password, String url, String schema) {
        Properties connectionProps = new Properties();
        Connection conn;
        try {
            connectionProps.put("user", user);
            connectionProps.put("password", password);
            conn = DriverManager.getConnection(url, connectionProps);
            conn.setSchema(schema);
            return conn;
        } catch (Exception e) {
            log.error("Error 2: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
