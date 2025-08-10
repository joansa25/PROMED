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
public class ClienteDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public List listar() {
        String sql = "SELECT COD_CLI, DPI_CLI, NOM_CLI, APE_CLI, TEL_CLI, COR_CLI, DIR_CLI, NIT_CLI, COD_PLAN, COD_ZON, COD_USER, ID, ESTADO FROM pm_clientes "
                + "ORDER BY id ASC";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCOD_CLI(rs.getString(1));
                cli.setDPI(rs.getLong(2));           // DPI_CLI -> DPI
                cli.setNOMBRES(rs.getString(3));     // NOM_CLI -> NOMBRES
                cli.setAPELLIDOS(rs.getString(4));   // APE_CLI -> APELLIDOS
                cli.setCELULAR(rs.getInt(5));        // TEL_CLI -> CELULAR
                cli.setCORREO(rs.getString(6));      // COR_CLI -> CORREO
                cli.setDIRECCION(rs.getString(7));   // DIR_CLI -> DIRECCION
                cli.setNIT(rs.getInt(8));            // NIT_CLI -> NIT
                cli.setCOD_PLAN(rs.getString(9));    // COD_PLAN -> COD_PLAN
                cli.setCOD_ZONA(rs.getString(10));   // COD_ZON -> COD_ZONA
                cli.setCOD_USER(rs.getString(11));   // COD_USER -> COD_USER
                cli.setId(rs.getInt(12));            // ID -> ID
                cli.setESTADO(rs.getString(13));     // ESTADO -> ESTADO

                System.out.println("Desde el dao: cod cliente " + cli.getCOD_CLI());
                System.out.println("Desde el dao: dpi: " + cli.getDPI());

                lista.add(cli);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Cliente cli) {
        String sql = "INSERT INTO pm_clientes(COD_CLI, DPI_CLI, NOM_CLI, APE_CLI, TEL_CLI, COR_CLI, DIR_CLI, NIT_CLI, COD_PLAN, COD_ZON, COD_USER) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, cli.getCOD_CLI());
            ps.setLong(2, cli.getDPI());
            ps.setString(3, cli.getNOMBRES());
            ps.setString(4, cli.getAPELLIDOS());
            ps.setInt(5, cli.getCELULAR());
            ps.setString(6, cli.getCORREO());
            ps.setString(7, cli.getDIRECCION());
            ps.setInt(8, cli.getNIT());
            ps.setString(9, cli.getCOD_PLAN());
            ps.setString(10, cli.getCOD_ZONA());
            ps.setString(11, cli.getCOD_USER());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Resultado: " + resultado);
            System.out.println("Error al insertar el NUEVO CLIENTE: " + e.getMessage());
            return resultado;
        }
        System.out.println("Cliente insertado con éxito!");
        return resultado;
    }

    public Cliente ListarId(String codCli) {
        Cliente cli = null;
        String sql = "SELECT COD_CLI, DPI_CLI, NOM_CLI, APE_CLI, TEL_CLI, COR_CLI, DIR_CLI, NIT_CLI, COD_PLAN, COD_ZON, COD_USER, ESTADO FROM pm_clientes WHERE COD_CLI = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codCli);
            System.out.println("Cod cliente DAO: " + codCli);
            rs = ps.executeQuery();
            if (rs.next()) {
                cli = new Cliente();

                cli.setCOD_CLI(rs.getString(1));
                cli.setDPI(rs.getLong(2));
                cli.setNOMBRES(rs.getString(3));
                cli.setAPELLIDOS(rs.getString(4));
                cli.setCELULAR(rs.getInt(5));
                cli.setCORREO(rs.getString(6));
                cli.setDIRECCION(rs.getString(7));
                cli.setNIT(rs.getInt(8));
                cli.setCOD_PLAN(rs.getString(9));
                cli.setCOD_ZONA(rs.getString(10));
                cli.setCOD_USER(rs.getString(11));
                cli.setESTADO(rs.getString(12));
                
                String cod = cli.getCOD_CLI();
                System.out.println("Cliente encontrado: " + cod);
            }

            System.out.println("Dentro del DAO cliente: " + codCli);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar el cliente por ID: " + e.getMessage());
        }
        return cli;
    }

    public int actualizar(Cliente cliente) {
        int resultado = 0;
        String sql = "UPDATE pm_clientes SET DPI_CLI = ?, NOM_CLI = ?, APE_CLI = ?, TEL_CLI = ?, COR_CLI = ?, DIR_CLI = ?, NIT_CLI = ?, COD_PLAN = ?, COD_ZON = ?, COD_USER = ?, ESTADO = ? WHERE COD_CLI = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            // Asignar los parámetros
            ps.setLong(1, cliente.getDPI());
            ps.setString(2, cliente.getNOMBRES());
            ps.setString(3, cliente.getAPELLIDOS());
            ps.setInt(4, cliente.getCELULAR());
            ps.setString(5, cliente.getCORREO());
            ps.setString(6, cliente.getDIRECCION());
            ps.setInt(7, cliente.getNIT());
            ps.setString(8, cliente.getCOD_PLAN());
            ps.setString(9, cliente.getCOD_ZONA());
            ps.setString(10, cliente.getCOD_USER());
            ps.setString(11, cliente.getESTADO());
            ps.setString(12, cliente.getCOD_CLI()); // WHERE condition

            // Ejecutar la actualización
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
        System.out.println("Cliente actualizado correctamente");

        return resultado;
    }

    public List<Cliente> buscarPorCodigo(String codigo) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_clientes WHERE COD_CLI LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("ID"));
                cli.setCOD_CLI(rs.getString("COD_CLI"));
                cli.setDPI(rs.getLong("DPI_CLI"));
                cli.setNOMBRES(rs.getString("NOM_CLI"));
                cli.setAPELLIDOS(rs.getString("APE_CLI"));
                cli.setCELULAR(rs.getInt("TEL_CLI"));
                cli.setCORREO(rs.getString("COR_CLI"));
                cli.setDIRECCION(rs.getString("DIR_CLI"));
                cli.setNIT(rs.getInt("NIT_CLI"));
                cli.setCOD_PLAN(rs.getString("COD_PLAN"));
                cli.setCOD_ZONA(rs.getString("COD_ZON"));
                cli.setCOD_USER(rs.getString("COD_USER"));
                cli.setESTADO(rs.getString("ESTADO"));
                lista.add(cli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_clientes WHERE NOM_CLI LIKE ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("ID"));
                cli.setCOD_CLI(rs.getString("COD_CLI"));
                cli.setDPI(rs.getLong("DPI_CLI"));
                cli.setNOMBRES(rs.getString("NOM_CLI"));
                cli.setAPELLIDOS(rs.getString("APE_CLI"));
                cli.setCELULAR(rs.getInt("TEL_CLI"));
                cli.setCORREO(rs.getString("COR_CLI"));
                cli.setDIRECCION(rs.getString("DIR_CLI"));
                cli.setNIT(rs.getInt("NIT_CLI"));
                cli.setCOD_PLAN(rs.getString("COD_PLAN"));
                cli.setCOD_ZONA(rs.getString("COD_ZON"));
                cli.setCOD_USER(rs.getString("COD_USER"));
                cli.setESTADO(rs.getString("ESTADO"));
                lista.add(cli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Cliente> filtrarPorEstado(String estado) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pm_clientes WHERE ESTADO = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("ID"));
                cli.setCOD_CLI(rs.getString("COD_CLI"));
                cli.setDPI(rs.getLong("DPI_CLI"));
                cli.setNOMBRES(rs.getString("NOM_CLI"));
                cli.setAPELLIDOS(rs.getString("APE_CLI"));
                cli.setCELULAR(rs.getInt("TEL_CLI"));
                cli.setCORREO(rs.getString("COR_CLI"));
                cli.setDIRECCION(rs.getString("DIR_CLI"));
                cli.setNIT(rs.getInt("NIT_CLI"));
                cli.setCOD_PLAN(rs.getString("COD_PLAN"));
                cli.setCOD_ZONA(rs.getString("COD_ZON"));
                cli.setCOD_USER(rs.getString("COD_USER"));
                cli.setESTADO(rs.getString("ESTADO"));
                lista.add(cli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List listarActivos() {
        String sql = "SELECT COD_CLI, DPI_CLI, NOM_CLI, APE_CLI, TEL_CLI, COR_CLI, DIR_CLI, NIT_CLI, COD_PLAN, COD_ZON, COD_USER, ID, ESTADO FROM pm_clientes "
                + "WHERE ESTADO = 'A' ORDER BY id ASC";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCOD_CLI(rs.getString(1));
                cli.setDPI(rs.getLong(2));
                cli.setNOMBRES(rs.getString(3));
                cli.setAPELLIDOS(rs.getString(4));
                cli.setCELULAR(rs.getInt(5));
                cli.setCORREO(rs.getString(6));
                cli.setDIRECCION(rs.getString(7));
                cli.setNIT(rs.getInt(8));
                cli.setCOD_PLAN(rs.getString(9));
                cli.setCOD_ZONA(rs.getString(10));
                cli.setCOD_USER(rs.getString(11));
                cli.setId(rs.getInt(12));
                cli.setESTADO(rs.getString(13));

                System.out.println("Desde el dao: cod cliente activo " + cli.getCOD_CLI());
                System.out.println("Desde el dao: dpi: " + cli.getDPI());

                lista.add(cli);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Agregar este método en tu ClienteDao.java

public int contarClientesActivos() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM pm_clientes WHERE ESTADO = 'A'";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al contar clientes activos: " + e.getMessage());
    }
    return count;
}

public int contarTotalClientes() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM pm_clientes";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}
}