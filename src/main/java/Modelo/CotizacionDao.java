package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CotizacionDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    // Listar todas las cotizaciones
    public List<Cotizacion> listar() {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones ORDER BY ID DESC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotizaciones: " + e.getMessage());
        }
        return lista;
    }

    // Agregar nueva cotización
    public int agregar(Cotizacion cot) {
        String sql = "INSERT INTO pm_cotizaciones(COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        int resultado = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cot.getCOD_CLI());
            ps.setString(2, cot.getNUM_COT());
            ps.setString(3, cot.getID_TRANS());
            ps.setString(4, cot.getPERIODO());
            ps.setDate(5, new java.sql.Date(cot.getFEC_EMISION().getTime()));
            ps.setDate(6, new java.sql.Date(cot.getFEC_VENC().getTime()));
            ps.setDouble(7, cot.getMONTO());
            ps.setString(8, cot.getESTADO());
            
            resultado = ps.executeUpdate();
            System.out.println("Cotización insertada con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar cotización: " + e.getMessage());
        }
        return resultado;
    }

    // Listar cotización por ID
    public Cotizacion listarPorId(int id) {
        Cotizacion cot = null;
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE ID = ? ";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotización por ID: " + e.getMessage());
        }
        return cot;
    }

    // Actualizar cotización
    public int actualizar(Cotizacion cot) {
        int resultado = 0;
        String sql = "UPDATE pm_cotizaciones SET COD_CLI = ?, NUM_COT = ?, ID_TRANS = ?, PERIODO = ?, "
                + "FEC_EMISION = ?, FEC_VENC = ?, MONTO = ?, ESTADO = ? WHERE ID = ? ";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cot.getCOD_CLI());
            ps.setString(2, cot.getNUM_COT());
            ps.setString(3, cot.getID_TRANS());
            ps.setString(4, cot.getPERIODO());
            ps.setDate(5, new java.sql.Date(cot.getFEC_EMISION().getTime()));
            ps.setDate(6, new java.sql.Date(cot.getFEC_VENC().getTime()));
            ps.setDouble(7, cot.getMONTO());
            ps.setString(8, cot.getESTADO());
            ps.setInt(9, cot.getID());
            
            resultado = ps.executeUpdate();
            System.out.println("Cotización actualizada correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar cotización: " + e.getMessage());
        }
        return resultado;
    }

    // ✅ Listar cotizaciones por cliente EXACTO (para cliente logueado)
    public List<Cotizacion> listarPorCliente(String codCli) {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE COD_CLI = ? ORDER BY FEC_VENC ASC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codCli);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotizaciones por cliente: " + e.getMessage());
        }
        return lista;
    }

    // ✅ NUEVO: Buscar cotizaciones por código de cliente con LIKE (para búsquedas)
    public List<Cotizacion> buscarPorCodigoCliente(String codCli) {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE COD_CLI LIKE ? ORDER BY FEC_VENC ASC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + codCli + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al buscar cotizaciones por código de cliente: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Buscar por número de cotización con LIKE
    public List<Cotizacion> buscarPorNumCot(String numCot) {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE NUM_COT LIKE ? ORDER BY FEC_VENC ASC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + numCot + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al buscar cotización por número: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Listar cotizaciones por periodo con LIKE
    public List<Cotizacion> listarPorPeriodo(String periodo) {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE PERIODO LIKE ? ORDER BY COD_CLI";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + periodo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotizaciones por periodo: " + e.getMessage());
        }
        return lista;
    }

    // Listar solo cotizaciones activas
    public List<Cotizacion> listarActivas() {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE ESTADO = 'A' ORDER BY FEC_VENC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotizaciones activas: " + e.getMessage());
        }
        return lista;
    }

    // Filtrar por estado
    public List<Cotizacion> filtrarPorEstado(String estado) {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE ESTADO = ? ORDER BY FEC_VENC ASC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al filtrar cotizaciones por estado: " + e.getMessage());
        }
        return lista;
    }

    // Contar cotizaciones por cliente
    public int contarPorCliente(String codCli) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM pm_cotizaciones WHERE COD_CLI = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codCli);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al contar cotizaciones por cliente: " + e.getMessage());
        }
        return count;
    }

    // Contar cotizaciones activas por cliente
    public int contarActivasPorCliente(String codCli) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM pm_cotizaciones WHERE COD_CLI = ? AND ESTADO = 'A'";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codCli);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al contar cotizaciones activas: " + e.getMessage());
        }
        return count;
    }

    // Calcular deuda total de un cliente
    public double calcularDeudaCliente(String codCli) {
        double deuda = 0.0;
        String sql = "SELECT SUM(MONTO) FROM pm_cotizaciones WHERE COD_CLI = ? AND ESTADO = 'A'";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codCli);
            rs = ps.executeQuery();
            if (rs.next()) {
                deuda = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al calcular deuda del cliente: " + e.getMessage());
        }
        return deuda;
    }

    // Listar cotizaciones vencidas
    public List<Cotizacion> listarVencidas() {
        String sql = "SELECT ID, COD_CLI, NUM_COT, ID_TRANS, PERIODO, FEC_EMISION, FEC_VENC, MONTO, ESTADO, FEC_CREACION "
                + "FROM pm_cotizaciones WHERE FEC_VENC < CURDATE() AND ESTADO = 'A' ORDER BY FEC_VENC";
        List<Cotizacion> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cotizacion cot = new Cotizacion();
                cot.setID(rs.getInt(1));
                cot.setCOD_CLI(rs.getString(2));
                cot.setNUM_COT(rs.getString(3));
                cot.setID_TRANS(rs.getString(4));
                cot.setPERIODO(rs.getString(5));
                cot.setFEC_EMISION(rs.getDate(6));
                cot.setFEC_VENC(rs.getDate(7));
                cot.setMONTO(rs.getDouble(8));
                cot.setESTADO(rs.getString(9));
                cot.setFEC_CREACION(rs.getDate(10));
                lista.add(cot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar cotizaciones vencidas: " + e.getMessage());
        }
        return lista;
    }

    // Cambiar estado a Cancelada
    public int cancelarCotizacion(int id) {
        int resultado = 0;
        String sql = "UPDATE pm_cotizaciones SET ESTADO = 'C' WHERE ID = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
            System.out.println("Cotización cancelada correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cancelar cotización: " + e.getMessage());
        }
        return resultado;
    }
    
    
    // Agregar estos métodos en CotizacionDao.java

// Método para obtener datos completos del pago con JOINs
public Map<String, Object> obtenerDatosPagoCompleto(String numCot, String idTrans) {
    Map<String, Object> datos = new HashMap<>();
    String sql = "SELECT " +
                 "p.ID, p.NUM_COT, p.ID_TRANS, p.FEC_PAGO, p.MONTO_PAGADO, " +
                 "p.METODO_PAGO, p.NUM_REF, p.REGISTRADO_POR, " +
                 "c.COD_CLI, c.PERIODO, c.FEC_EMISION, c.FEC_VENC, " +
                 "cl.NOM_CLI, cl.APE_CLI, cl.DPI_CLI, cl.NIT_CLI, " +
                 "cl.DIR_CLI, cl.TEL_CLI, cl.COR_CLI, " +
                 "pl.COD_NOMB AS NOMBRE_PLAN, pl.COD_DESC AS DESC_PLAN " +
                 "FROM pm_pagos p " +
                 "INNER JOIN pm_cotizaciones c ON p.NUM_COT = c.NUM_COT AND p.ID_TRANS = c.ID_TRANS " +
                 "INNER JOIN pm_clientes cl ON c.COD_CLI = cl.COD_CLI " +
                 "INNER JOIN pm_planes pl ON cl.COD_PLAN = pl.COD_PLAN " +
                 "WHERE p.NUM_COT = ? AND p.ID_TRANS = ?";
    
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, numCot);
        ps.setString(2, idTrans);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            // Datos del pago
            datos.put("idPago", rs.getInt("ID"));
            datos.put("numCot", rs.getString("NUM_COT"));
            datos.put("idTrans", rs.getString("ID_TRANS"));
            datos.put("fecPago", rs.getDate("FEC_PAGO"));
            datos.put("montoPagado", rs.getDouble("MONTO_PAGADO"));
            datos.put("metodoPago", rs.getString("METODO_PAGO"));
            datos.put("numRef", rs.getString("NUM_REF"));
            datos.put("registradoPor", rs.getString("REGISTRADO_POR"));
            
            // Datos de la cotización
            datos.put("codCli", rs.getString("COD_CLI"));
            datos.put("periodo", rs.getString("PERIODO"));
            datos.put("fecEmision", rs.getDate("FEC_EMISION"));
            datos.put("fecVenc", rs.getDate("FEC_VENC"));
            
            // Datos del cliente
            datos.put("nombreCliente", rs.getString("NOM_CLI") + " " + rs.getString("APE_CLI"));
            datos.put("dpiCliente", rs.getString("DPI_CLI"));
            datos.put("nitCliente", rs.getString("NIT_CLI"));
            datos.put("dirCliente", rs.getString("DIR_CLI"));
            datos.put("telCliente", rs.getString("TEL_CLI"));
            datos.put("correoCliente", rs.getString("COR_CLI"));
            
            // Datos del plan
            datos.put("nombrePlan", rs.getString("NOMBRE_PLAN"));
            datos.put("descPlan", rs.getString("DESC_PLAN"));
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al obtener datos del pago: " + e.getMessage());
    }
    
    return datos;
}

// Método para verificar si existe un pago
public boolean existePago(String numCot, String idTrans) {
    boolean existe = false;
    String sql = "SELECT COUNT(*) FROM pm_pagos WHERE NUM_COT = ? AND ID_TRANS = ?";
    
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, numCot);
        ps.setString(2, idTrans);
        rs = ps.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            existe = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return existe;
}
   
    
}