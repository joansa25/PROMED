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
public class ZonaDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public List listar() {
        String sql = "SELECT COD_ZON, COD_NOMB, COD_DESC, ID, ESTADO FROM pm_zonas "
                + "ORDER BY id ASC";
        List<Zona> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setCOD_ZON(rs.getString(1));
                zona.setCOD_NOMB(rs.getString(2));
                zona.setCOD_DESC(rs.getString(3));
                zona.setId(rs.getInt(4));
                zona.setESTADO(rs.getString(5));

                System.out.println("Desde el dao: cod zona " + zona.getCOD_ZON());
                System.out.println("Desde el dao: nombre: " + zona.getCOD_NOMB());

                lista.add(zona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Zona zona) {
        String sql = "INSERT INTO pm_zonas(COD_ZON, COD_NOMB, COD_DESC) VALUES(?,?,?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, zona.getCOD_ZON());
            ps.setString(2, zona.getCOD_NOMB());
            ps.setString(3, zona.getCOD_DESC());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Resultado: " + resultado);
            System.out.println("Error al insertar la NUEVA ZONA: " + e.getMessage());
            return resultado;
        }
        System.out.println("Zona insertada con éxito!");
        return resultado;
    }

    public Zona ListarId(String codZona) {
        Zona zona = null;
        String sql = "SELECT COD_ZON, COD_NOMB, COD_DESC, ESTADO FROM pm_zonas WHERE COD_ZON = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codZona);
            System.out.println("Cod zona DAO: " + codZona);
            rs = ps.executeQuery();
            if (rs.next()) {
                zona = new Zona();

                zona.setCOD_ZON(rs.getString(1));
                zona.setCOD_NOMB(rs.getString(2));
                zona.setCOD_DESC(rs.getString(3));
                zona.setESTADO(rs.getString(4));
                
                String cod = zona.getCOD_ZON();
                System.out.println("Zona encontrada: " + cod);
            }

            System.out.println("Dentro del DAO zona: " + codZona);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar la zona por ID: " + e.getMessage());
        }
        return zona;
    }

    public int actualizar(Zona zona) {
        int resultado = 0;
        String sql = "UPDATE pm_zonas SET COD_NOMB = ?, COD_DESC = ?, ESTADO = ? WHERE COD_ZON = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            // Asignar los parámetros
            ps.setString(1, zona.getCOD_NOMB());
            ps.setString(2, zona.getCOD_DESC());
            ps.setString(3, zona.getESTADO());
            ps.setString(4, zona.getCOD_ZON()); // WHERE condition

            // Ejecutar la actualización
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar zona: " + e.getMessage());
        }
        System.out.println("Zona actualizada correctamente");

        return resultado;
    }

    public List<Zona> buscarPorCodigo(String codigo) {
        List<Zona> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_zonas WHERE COD_ZON LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setId(rs.getInt("ID"));
                zona.setCOD_ZON(rs.getString("COD_ZON"));
                zona.setCOD_NOMB(rs.getString("COD_NOMB"));
                zona.setCOD_DESC(rs.getString("COD_DESC"));
                zona.setESTADO(rs.getString("ESTADO"));
                lista.add(zona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Zona> buscarPorNombre(String nombre) {
        List<Zona> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_zonas WHERE COD_NOMB LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setId(rs.getInt("ID"));
                zona.setCOD_ZON(rs.getString("COD_ZON"));
                zona.setCOD_NOMB(rs.getString("COD_NOMB"));
                zona.setCOD_DESC(rs.getString("COD_DESC"));
                zona.setESTADO(rs.getString("ESTADO"));
                lista.add(zona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Zona> filtrarPorEstado(String estado) {
        List<Zona> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_zonas WHERE ESTADO = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setId(rs.getInt("ID"));
                zona.setCOD_ZON(rs.getString("COD_ZON"));
                zona.setCOD_NOMB(rs.getString("COD_NOMB"));
                zona.setCOD_DESC(rs.getString("COD_DESC"));
                zona.setESTADO(rs.getString("ESTADO"));
                lista.add(zona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List listarActivos() {
        String sql = "SELECT COD_ZON, COD_NOMB, COD_DESC, ID, ESTADO FROM pm_zonas "
                + "WHERE ESTADO = 'A' ORDER BY id ASC";
        List<Zona> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setCOD_ZON(rs.getString(1));
                zona.setCOD_NOMB(rs.getString(2));
                zona.setCOD_DESC(rs.getString(3));
                zona.setId(rs.getInt(4));
                zona.setESTADO(rs.getString(5));

                System.out.println("Desde el dao: cod zona activa " + zona.getCOD_ZON());
                System.out.println("Desde el dao: nombre: " + zona.getCOD_NOMB());

                lista.add(zona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}