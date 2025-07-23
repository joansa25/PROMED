package Test;

import Config.Conexion;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Conexion cn = new Conexion();
        Connection con = cn.Conexion();

        if (con != null) {
            System.out.println(" Conexión exitosa a la base de datos. ");
        } else {
            System.out.println(" Error al conectar a la base de datos.");
        }
    }
}
