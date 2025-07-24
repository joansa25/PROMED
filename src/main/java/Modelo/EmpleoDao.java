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

public class EmpleoDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Empleo> listar() {
        String sql = "SELECT * FROM pm_empleo ORDER BY id ASC";
        List<Empleo> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleo emp = new Empleo();
                emp.setID(rs.getInt(1));
                emp.setCOD_EMP(rs.getString(2));
                emp.setNOMB_EMP(rs.getString(3));
                emp.setSALARIO(rs.getDouble(4));
                emp.setESTADO(rs.getString(5));
                lista.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    
    public int agregar(Empleo emp) {
        String sql = "INSERT INTO pm_empleo(COD_EMP, NOMB_EMP, SALARIO) VALUES (?, ?, ?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getCOD_EMP());
            ps.setString(2, emp.getNOMB_EMP());
            ps.setDouble(3, emp.getSALARIO());
       
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Empleo ListarId(String codEmp) {
        Empleo emp = null;
        String sql = "SELECT * FROM pm_empleo WHERE COD_EMP = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codEmp);
            rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Empleo();
                emp.setID(rs.getInt(1));
                emp.setCOD_EMP(rs.getString(2));
                emp.setNOMB_EMP(rs.getString(3));
                emp.setSALARIO(rs.getDouble(4));
                emp.setESTADO(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public int actualizar(Empleo emp) {
        String sql = "UPDATE pm_empleo SET NOMB_EMP = ?, SALARIO = ?, ESTADO = ? WHERE COD_EMP = ?";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getNOMB_EMP());
            ps.setDouble(2, emp.getSALARIO());
            ps.setString(3, emp.getESTADO());
           ps.setString(4, emp.getCOD_EMP());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Empleo> buscarPorCodigo(String codigo) {
        List<Empleo> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_empleo WHERE COD_EMP LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleo emp = new Empleo();
                emp.setID(rs.getInt(1));
                emp.setCOD_EMP(rs.getString(2));
                emp.setNOMB_EMP(rs.getString(3));
                emp.setSALARIO(rs.getDouble(4));
                emp.setESTADO(rs.getString(5));
                lista.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Empleo> buscarPorNombre(String nombre) {
        List<Empleo> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_empleo WHERE NOMB_EMP LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleo emp = new Empleo();
                emp.setID(rs.getInt(1));
                emp.setCOD_EMP(rs.getString(2));
                emp.setNOMB_EMP(rs.getString(3));
                emp.setSALARIO(rs.getDouble(4));
                emp.setESTADO(rs.getString(5));
                lista.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    
    public List<Empleo> filtrarPorEstado(String estado) {
    List<Empleo> lista = new ArrayList<>();
    String sql = "SELECT * FROM pm_empleo WHERE ESTADO = ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, estado);
        rs = ps.executeQuery();
        while (rs.next()) {
            Empleo emp = new Empleo();
            emp.setID(rs.getInt(1));
            emp.setCOD_EMP(rs.getString(2));
            emp.setNOMB_EMP(rs.getString(3));
            emp.setSALARIO(rs.getDouble(4));
            emp.setESTADO(rs.getString(5));
            lista.add(emp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

}

