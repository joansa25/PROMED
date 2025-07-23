/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joans
 */
public class UserDao {
        Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    // Método para validar un usuario por su nombre de usuario y contraseña
public User validar(String user, String password) {
    User us = null;
    String sql = "SELECT * FROM PM_USUARIOS WHERE User_US=? AND Psw_Us=?";
    System.out.println("entrando al metodo validar");
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next()) {
            us = new User();
            us.setUser_US(rs.getString("User_US"));
            us.setPsw_Us(rs.getString("Psw_Us"));
            us.setNomb_Us(rs.getString("Nomb_Us"));  // ← nombre
            us.setApe_Us(rs.getString("Ape_Us"));    // ← apellido
            us.setCod_user(rs.getString("Cod_User")); // ← si lo necesitas también
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return us;
}
}
