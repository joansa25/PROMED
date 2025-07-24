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
public class EmpleadoDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public List listar() {
        String sql = "select COD_EMPD, DPI_EM, N_IGSS,NIT, NOMBRES, APELLIDOS, COD_EMP, COD_EMPSA, ID, CELULAR, CORREO, ESTADO from pm_empleados  "
                + "order by id asc";
        List<Empleado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setCOC_EMPD(rs.getString(1));
                em.setDPI(rs.getInt(2));
                em.setN_IGSS(rs.getInt(3));
                em.setNIT(rs.getInt(4));
                em.setNOMBRES(rs.getString(5));
                em.setAPELLIDOS(rs.getString(6));
                em.setCOD_EMP(rs.getString(7));
                em.setCOD_EMPSA(rs.getString(8));
                em.setId(rs.getInt(9));
                em.setCELULAR(rs.getInt(10));
                em.setCorreo(rs.getString(11));
                em.setEstado(rs.getString(12));

                System.out.println(" desde el dao :cod emp a" + em.getCOC_EMPD());
                System.out.println("desde el dao :  dpi : " + em.getDPI());

                lista.add(em);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Empleado em) {
        String sql = "insert into PM_EMPLEADOS(COD_EMPD,NOMBRES,APELLIDOS,N_IGSS,NIT,COD_EMP,COD_EMPSA,CELULAR,CORREO) values(?,?,?,?,?,?,?,?,?)";
        int resultado = 0; // Variable para determinar el éxito o fallo
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, em.getCOC_EMPD());
            ps.setString(2, em.getNOMBRES());
            ps.setString(3, em.getAPELLIDOS());
            ps.setInt(4, em.getN_IGSS());
            ps.setInt(5, em.getNIT());
            ps.setString(6, em.getCOD_EMP());
            ps.setString(7, em.getCOD_EMPSA());
            ps.setInt(8, em.getCELULAR());
            ps.setString(9, em.getCorreo());

            resultado = ps.executeUpdate(); // Ejecuta y guarda el número de filas afectadas

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("resultado ? " + resultado);
            System.out.println("Error al insertar el NUEVO EMPLEADO: !!!!!!!!!!!!!!!!!!!!!!!!!!!! " + e.getMessage());
            return resultado;
        }
        System.out.println("se inserto con exito!!!!!!!!!!!!!!!!!!!!!!!");
        return resultado; // Devuelve el resultado, 1 si fue exitoso, 0 si no
    }

    public Empleado ListarId(String codEmp) {
        Empleado em = null;
        String sql = "select COD_EMPD,N_IGSS,NIT, NOMBRES, APELLIDOS, COD_EMP, COD_EMPSA, CELULAR, CORREO, ESTADO from pm_empleados WHERE COD_EMPD = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codEmp);
            System.out.println("cod emp daooo: " + codEmp);
            rs = ps.executeQuery();
            if (rs.next()) {
                em = new Empleado();

                em.setCOC_EMPD(rs.getString(1));
                em.setN_IGSS(rs.getInt(2));
                em.setNIT(rs.getInt(3));
                em.setNOMBRES(rs.getString(4));
                em.setAPELLIDOS(rs.getString(5));
                em.setCOD_EMP(rs.getString(6));
                em.setCOD_EMPSA(rs.getString(7));
                em.setCELULAR(rs.getInt(8));  // Asegúrate de que es INT en la BD
                em.setCorreo(rs.getString(9)); // Debe ser getString()
                em.setEstado(rs.getString(10));
                String d = em.getCOC_EMPD();
                System.out.println("edksd ----- " + d);
            }

            System.out.println("dentro del  dao empleado : " + codEmp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar el cliente por ID --------------------: " + e.getMessage());
        }
        return em;
    }

    
    public int actualizar(Empleado empleado) {
    int resultado = 0;
    String sql = "UPDATE pm_empleados SET NOMBRES = ?, APELLIDOS = ?, N_IGSS = ?, NIT = ?, COD_EMP = ?, COD_EMPSA = ?, CELULAR = ?, CORREO = ?, ESTADO = ? WHERE COD_EMPD = ?";

    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);

        // Asignar los parámetros
        ps.setString(1, empleado.getNOMBRES());
        ps.setString(2, empleado.getAPELLIDOS());
        ps.setInt(3, empleado.getN_IGSS());
        ps.setInt(4, empleado.getNIT());
        ps.setString(5, empleado.getCOD_EMP());
        ps.setString(6, empleado.getCOD_EMPSA());
        ps.setInt(7, empleado.getCELULAR());
        ps.setString(8, empleado.getCorreo());
        ps.setString(9, empleado.getEstado());
        ps.setString(10, empleado.getCOC_EMPD()); // WHERE condition

        // Ejecutar la actualización
        resultado = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al actualizar empleado: " + e.getMessage());
    }
            System.out.println("se actualizo correctamente/////////////////////////////////////////////////");

    return resultado;
}
    
    public List<Empleado> buscarPorCodigo(String codigo) {
    List<Empleado> lista = new ArrayList<>();
    String sql = "SELECT * FROM pm_empleados WHERE COD_EMPD LIKE ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + codigo + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Empleado em = new Empleado();
            em.setId(rs.getInt("ID"));
            em.setCOC_EMPD(rs.getString("COD_EMPD"));
            em.setNOMBRES(rs.getString("NOMBRES"));
            em.setAPELLIDOS(rs.getString("APELLIDOS"));
            em.setN_IGSS(rs.getInt("N_IGSS"));
            em.setNIT(rs.getInt("NIT"));
            em.setCOD_EMP(rs.getString("COD_EMP"));
            em.setCOD_EMPSA(rs.getString("COD_EMPSA"));
            em.setCELULAR(rs.getInt("CELULAR"));
            em.setCorreo(rs.getString("CORREO"));
            em.setEstado(rs.getString("ESTADO"));
            lista.add(em);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

public List<Empleado> buscarPorNombre(String nombre) {
    List<Empleado> lista = new ArrayList<>();
    String sql = "SELECT * FROM pm_empleados WHERE NOMBRES LIKE ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + nombre + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Empleado em = new Empleado();
            em.setId(rs.getInt("ID"));
            em.setCOC_EMPD(rs.getString("COD_EMPD"));
            em.setNOMBRES(rs.getString("NOMBRES"));
            em.setAPELLIDOS(rs.getString("APELLIDOS"));
            em.setN_IGSS(rs.getInt("N_IGSS"));
            em.setNIT(rs.getInt("NIT"));
            em.setCOD_EMP(rs.getString("COD_EMP"));
            em.setCOD_EMPSA(rs.getString("COD_EMPSA"));
            em.setCELULAR(rs.getInt("CELULAR"));
            em.setCorreo(rs.getString("CORREO"));
            em.setEstado(rs.getString("ESTADO"));
            lista.add(em);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

public List<Empleado> filtrarPorEstado(String estado) {
    List<Empleado> lista = new ArrayList<>();
    String sql = "SELECT * FROM pm_empleados WHERE ESTADO = ?";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, estado);
        rs = ps.executeQuery();
        while (rs.next()) {
            Empleado em = new Empleado();
            em.setId(rs.getInt("ID"));
            em.setCOC_EMPD(rs.getString("COD_EMPD"));
            em.setNOMBRES(rs.getString("NOMBRES"));
            em.setAPELLIDOS(rs.getString("APELLIDOS"));
            em.setN_IGSS(rs.getInt("N_IGSS"));
            em.setNIT(rs.getInt("NIT"));
            em.setCOD_EMP(rs.getString("COD_EMP"));
            em.setCOD_EMPSA(rs.getString("COD_EMPSA"));
            em.setCELULAR(rs.getInt("CELULAR"));
            em.setCorreo(rs.getString("CORREO"));
            em.setEstado(rs.getString("ESTADO"));
            lista.add(em);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}



    public List listarActivos() {
        String sql = "select COD_EMPD, DPI_EM, N_IGSS,NIT, NOMBRES, APELLIDOS, COD_EMP, COD_EMPSA, ID, CELULAR, CORREO, ESTADO from pm_empleados  "
                +  " where ESTADO = 'A' order by id asc";
        List<Empleado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setCOC_EMPD(rs.getString(1));
                em.setDPI(rs.getInt(2));
                em.setN_IGSS(rs.getInt(3));
                em.setNIT(rs.getInt(4));
                em.setNOMBRES(rs.getString(5));
                em.setAPELLIDOS(rs.getString(6));
                em.setCOD_EMP(rs.getString(7));
                em.setCOD_EMPSA(rs.getString(8));
                em.setId(rs.getInt(9));
                em.setCELULAR(rs.getInt(10));
                em.setCorreo(rs.getString(11));
                em.setEstado(rs.getString(12));

                System.out.println(" desde el dao :cod emp a" + em.getCOC_EMPD());
                System.out.println("desde el dao :  dpi : " + em.getDPI());

                lista.add(em);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }



}
