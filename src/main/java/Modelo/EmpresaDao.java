package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public List<Empresa> listar() {
        String sql = "SELECT * FROM pm_empresa ORDER BY id ASC";
        List<Empresa> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empsa = new Empresa();
                empsa.setID(rs.getInt(1));
                empsa.setCOD_EMPSA(rs.getString(2));
                empsa.setNOMB_EMPSA(rs.getString(3));
                empsa.setDIRE_EMPSA(rs.getString(4));
                empsa.setNIT(rs.getInt(5));
                empsa.setEstado(rs.getString(6));

                lista.add(empsa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Empresa empsa) {
        String sql = "INSERT INTO pm_empresa (COD_EMPSA, NOMB_EMPSA, DIRE_EMPSA, NIT_EMPSA) VALUES (?, ?, ?, ?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, empsa.getCOD_EMPSA());
            ps.setString(2, empsa.getNOMB_EMPSA());
            ps.setString(3, empsa.getDIRE_EMPSA());
            ps.setInt(4, empsa.getNIT());
          
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Empresa ListarId(String codEmpsa) {
        Empresa empsa = null;
        String sql = "SELECT * FROM pm_empresa WHERE COD_EMPSA = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codEmpsa);
            rs = ps.executeQuery();
            if (rs.next()) {
                empsa = new Empresa();
                empsa.setID(rs.getInt(1));
                empsa.setCOD_EMPSA(rs.getString(2));
                empsa.setNOMB_EMPSA(rs.getString(3));
                empsa.setDIRE_EMPSA(rs.getString(4));
                empsa.setNIT(rs.getInt(5));
                empsa.setEstado(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empsa;
    }

    public int actualizar(Empresa empsa) {
        String sql = "UPDATE pm_empresa SET NOMB_EMPSA = ?, DIRE_EMPSA = ?, NIT_EMPSA = ?, ESTADO = ? WHERE COD_EMPSA = ?";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, empsa.getNOMB_EMPSA());
            ps.setString(2, empsa.getDIRE_EMPSA());
            ps.setInt(3, empsa.getNIT());
            ps.setString(4, empsa.getEstado());
            ps.setString(5, empsa.getCOD_EMPSA());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Empresa> buscarPorCodigo(String codigo) {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_empresa WHERE COD_EMPSA LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empsa = new Empresa();
                empsa.setID(rs.getInt(1));
                empsa.setCOD_EMPSA(rs.getString(2));
                empsa.setNOMB_EMPSA(rs.getString(3));
                empsa.setDIRE_EMPSA(rs.getString(4));
                empsa.setNIT(rs.getInt(5));
                empsa.setEstado(rs.getString(6));
                lista.add(empsa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Empresa> buscarPorNombre(String nombre) {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_empresa WHERE NOMB_EMPSA LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empsa = new Empresa();
                empsa.setID(rs.getInt(1));
                empsa.setCOD_EMPSA(rs.getString(2));
                empsa.setNOMB_EMPSA(rs.getString(3));
                empsa.setDIRE_EMPSA(rs.getString(4));
                empsa.setNIT(rs.getInt(5));
                empsa.setEstado(rs.getString(6));
                lista.add(empsa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Empresa> filtrarPorEstado(String estado) {
        String sql = "SELECT * FROM pm_empresa WHERE ESTADO = ?";
        List<Empresa> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empsa = new Empresa();
                empsa.setID(rs.getInt(1));
                empsa.setCOD_EMPSA(rs.getString(2));
                empsa.setNOMB_EMPSA(rs.getString(3));
                empsa.setDIRE_EMPSA(rs.getString(4));
                empsa.setNIT(rs.getInt(5));
                empsa.setEstado(rs.getString(6));
                lista.add(empsa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
