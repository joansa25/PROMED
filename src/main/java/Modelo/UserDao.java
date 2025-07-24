/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

public List<User> listar() {
        String sql = "SELECT * FROM PM_USUARIOS";
        List<User> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User us = new User();
                us.setID(rs.getInt("ID"));
                us.setCod_user(rs.getString("COD_USER"));
                us.setUser_US(rs.getString("USER_US"));
                us.setRol_Us(rs.getString("ROL_US"));
                us.setCod_empsa(rs.getString("COD_EMPSA"));
                us.setPsw_Us(rs.getString("PSW_US"));
                us.setNomb_Us(rs.getString("NOMB_US"));
                us.setApe_Us(rs.getString("APE_US"));
                us.setCorr_Us(rs.getString("CORR_US"));
                us.setEsdado(rs.getString("Estado"));
                lista.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

   public int agregar(User us) {
    String sql = "INSERT INTO PM_USUARIOS(COD_USER, USER_US, ROL_US, PSW_US, NOMB_US, APE_US, CORR_US, COD_EMPSA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, us.getCod_user());
        ps.setString(2, us.getUser_US());
        ps.setString(3, us.getRol_Us());
        ps.setString(4, us.getPsw_Us());
        ps.setString(5, us.getNomb_Us());
        ps.setString(6, us.getApe_Us());
        ps.setString(7, us.getCorr_Us());
        ps.setString(8, us.getCod_empsa()); // NUEVO
        r = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return r;
}

    
    
    public User listarPorId(String codUser) {
        User us = new User();
        String sql = "SELECT * FROM PM_USUARIOS WHERE COD_USER=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setID(rs.getInt("ID"));
                us.setCod_user(rs.getString("COD_USER"));
                us.setUser_US(rs.getString("USER_US"));
                us.setRol_Us(rs.getString("ROL_US"));
                us.setCod_empsa(rs.getString("COD_EMPSA")); // NUEVO
                us.setPsw_Us(rs.getString("PSW_US"));
                us.setNomb_Us(rs.getString("NOMB_US"));
                us.setApe_Us(rs.getString("APE_US"));
                us.setCorr_Us(rs.getString("CORR_US"));
                us.setEsdado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

  public int actualizar(User us) {
    String sql = "UPDATE PM_USUARIOS SET USER_US=?, ROL_US=?, PSW_US=?, NOMB_US=?, APE_US=?, CORR_US=?, Estado=?, COD_EMPSA=? WHERE COD_USER=?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, us.getUser_US());
        ps.setString(2, us.getRol_Us());
        ps.setString(3, us.getPsw_Us());
        ps.setString(4, us.getNomb_Us());
        ps.setString(5, us.getApe_Us());
        ps.setString(6, us.getCorr_Us());
        ps.setString(7, us.getEsdado());
        ps.setString(8, us.getCod_empsa()); // NUEVO
        ps.setString(9, us.getCod_user());
        r = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return r;
}

    
    
    // Buscar usuarios por código
public List<User> buscarPorCodigo(String codigo) {
    List<User> lista = new ArrayList<>();
    String sql = "SELECT * FROM PM_USUARIOS WHERE COD_USER LIKE ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + codigo + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            User us = new User();
            us.setID(rs.getInt("ID"));
            us.setCod_user(rs.getString("COD_USER"));
            us.setUser_US(rs.getString("USER_US"));
            us.setRol_Us(rs.getString("ROL_US"));
            us.setCod_empsa(rs.getString("COD_EMPSA")); // NUEVO
            us.setPsw_Us(rs.getString("PSW_US"));
            us.setNomb_Us(rs.getString("NOMB_US"));
            us.setApe_Us(rs.getString("APE_US"));
            us.setCorr_Us(rs.getString("CORR_US"));
            us.setEsdado(rs.getString("Estado"));
            lista.add(us);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

// Buscar usuarios por nombre de usuario
public List<User> buscarPorUsuario(String usuario) {
    List<User> lista = new ArrayList<>();
    String sql = "SELECT * FROM PM_USUARIOS WHERE USER_US LIKE ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + usuario + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            User us = new User();
            us.setID(rs.getInt("ID"));
            us.setCod_user(rs.getString("COD_USER"));
            us.setUser_US(rs.getString("USER_US"));
            us.setRol_Us(rs.getString("ROL_US"));
            us.setCod_empsa(rs.getString("COD_EMPSA")); // NUEVO
            us.setPsw_Us(rs.getString("PSW_US"));
            us.setNomb_Us(rs.getString("NOMB_US"));
            us.setApe_Us(rs.getString("APE_US"));
            us.setCorr_Us(rs.getString("CORR_US"));
            us.setEsdado(rs.getString("Estado"));
            lista.add(us);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

// Filtrar usuarios por estado
public List<User> filtrarPorEstado(String estado) {
    List<User> lista = new ArrayList<>();
    String sql = "SELECT * FROM PM_USUARIOS WHERE Estado=?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, estado);
        rs = ps.executeQuery();
        while (rs.next()) {
            User us = new User();
            us.setID(rs.getInt("ID"));
            us.setCod_user(rs.getString("COD_USER"));
            us.setUser_US(rs.getString("USER_US"));
            us.setRol_Us(rs.getString("ROL_US"));
            us.setCod_empsa(rs.getString("COD_EMPSA")); // NUEVO
            us.setPsw_Us(rs.getString("PSW_US"));
            us.setNomb_Us(rs.getString("NOMB_US"));
            us.setApe_Us(rs.getString("APE_US"));
            us.setCorr_Us(rs.getString("CORR_US"));
            us.setEsdado(rs.getString("Estado"));
            lista.add(us);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

}
