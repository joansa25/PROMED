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
public class PlanDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public List listar() {
        String sql = "SELECT COD_PLAN, COD_NOMB, COD_DESC, ID, ESTADO FROM pm_planes "
                + "ORDER BY id ASC";
        List<Plan> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setCOD_PLAN(rs.getString(1));
                plan.setCOD_NOMB(rs.getString(2));
                plan.setCOD_DESC(rs.getString(3));
                plan.setId(rs.getInt(4));
                plan.setEstado(rs.getString(5));

                System.out.println("Desde el dao: cod plan " + plan.getCOD_PLAN());
                System.out.println("Desde el dao: nombre: " + plan.getCOD_NOMB());

                lista.add(plan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Plan plan) {
        String sql = "INSERT INTO pm_planes(COD_PLAN, COD_NOMB, COD_DESC) VALUES(?,?,?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, plan.getCOD_PLAN());
            ps.setString(2, plan.getCOD_NOMB());
            ps.setString(3, plan.getCOD_DESC());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Resultado: " + resultado);
            System.out.println("Error al insertar el NUEVO PLAN: " + e.getMessage());
            return resultado;
        }
        System.out.println("Plan insertado con éxito!");
        return resultado;
    }

    public Plan ListarId(String codPlan) {
        Plan plan = null;
        String sql = "SELECT COD_PLAN, COD_NOMB, COD_DESC, ESTADO FROM pm_planes WHERE COD_PLAN = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codPlan);
            System.out.println("Cod plan DAO: " + codPlan);
            rs = ps.executeQuery();
            if (rs.next()) {
                plan = new Plan();

                plan.setCOD_PLAN(rs.getString(1));
                plan.setCOD_NOMB(rs.getString(2));
                plan.setCOD_DESC(rs.getString(3));
                plan.setEstado(rs.getString(4));
                
                String cod = plan.getCOD_PLAN();
                System.out.println("Plan encontrado: " + cod);
            }

            System.out.println("Dentro del DAO plan: " + codPlan);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar el plan por ID: " + e.getMessage());
        }
        return plan;
    }

    public int actualizar(Plan plan) {
        int resultado = 0;
        String sql = "UPDATE pm_planes SET COD_NOMB = ?, COD_DESC = ?, ESTADO = ? WHERE COD_PLAN = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            // Asignar los parámetros
            ps.setString(1, plan.getCOD_NOMB());
            ps.setString(2, plan.getCOD_DESC());
            ps.setString(3, plan.getEstado());
            ps.setString(4, plan.getCOD_PLAN()); // WHERE condition

            // Ejecutar la actualización
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar plan: " + e.getMessage());
        }
        System.out.println("Plan actualizado correctamente");

        return resultado;
    }

    public List<Plan> buscarPorCodigo(String codigo) {
        List<Plan> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_planes WHERE COD_PLAN LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("ID"));
                plan.setCOD_PLAN(rs.getString("COD_PLAN"));
                plan.setCOD_NOMB(rs.getString("COD_NOMB"));
                plan.setCOD_DESC(rs.getString("COD_DESC"));
                plan.setEstado(rs.getString("ESTADO"));
                lista.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Plan> buscarPorNombre(String nombre) {
        List<Plan> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_planes WHERE COD_NOMB LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("ID"));
                plan.setCOD_PLAN(rs.getString("COD_PLAN"));
                plan.setCOD_NOMB(rs.getString("COD_NOMB"));
                plan.setCOD_DESC(rs.getString("COD_DESC"));
                plan.setEstado(rs.getString("ESTADO"));
                lista.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Plan> filtrarPorEstado(String estado) {
        List<Plan> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_planes WHERE ESTADO = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("ID"));
                plan.setCOD_PLAN(rs.getString("COD_PLAN"));
                plan.setCOD_NOMB(rs.getString("COD_NOMB"));
                plan.setCOD_DESC(rs.getString("COD_DESC"));
                plan.setEstado(rs.getString("ESTADO"));
                lista.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List listarActivos() {
        String sql = "SELECT COD_PLAN, COD_NOMB, COD_DESC, ID, ESTADO FROM pm_planes "
                + "WHERE ESTADO = 'A' ORDER BY id ASC";
        List<Plan> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setCOD_PLAN(rs.getString(1));
                plan.setCOD_NOMB(rs.getString(2));
                plan.setCOD_DESC(rs.getString(3));
                plan.setId(rs.getInt(4));
                plan.setEstado(rs.getString(5));

                System.out.println("Desde el dao: cod plan activo " + plan.getCOD_PLAN());
                System.out.println("Desde el dao: nombre: " + plan.getCOD_NOMB());

                lista.add(plan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    // Agregar en PlanDao.java
public int contarPlanesActivos() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM pm_planes WHERE ESTADO = 'A'";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al contar planes activos: " + e.getMessage());
    }
    return count;
}

// Agregar en IncidenciaDao.java (si tienes tabla de incidencias)
public int contarIncidenciasPendientes() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM pm_incidencias WHERE ESTADO = 'P'"; // P = Pendiente
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al contar incidencias pendientes: " + e.getMessage());
    }
    return count;
}

// Si no tienes tabla de incidencias, puedes usar una consulta temporal
// o crear un método que devuelva 0 por ahora
}