/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Cliente {
    
  int id;
  String COD_CLI;
  long DPI;
  String NOMBRES;
  String APELLIDOS;
  int CELULAR;
  String CORREO;
  String DIRECCION;
  int NIT;
  String COD_PLAN;
  String COD_ZONA;
  String COD_USER;
  String ESTADO;

    public Cliente() {
    }

    public Cliente(int id, String COD_CLI, long DPI, String NOMBRES, String APELLIDOS, int CELULAR, String CORREO, String DIRECCION, int NIT, String COD_PLAN, String COD_ZONA, String COD_USER, String ESTADO) {
        this.id = id;
        this.COD_CLI = COD_CLI;
        this.DPI = DPI;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.CELULAR = CELULAR;
        this.CORREO = CORREO;
        this.DIRECCION = DIRECCION;
        this.NIT = NIT;
        this.COD_PLAN = COD_PLAN;
        this.COD_ZONA = COD_ZONA;
        this.COD_USER = COD_USER;
        this.ESTADO = ESTADO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOD_CLI() {
        return COD_CLI;
    }

    public void setCOD_CLI(String COD_CLI) {
        this.COD_CLI = COD_CLI;
    }

    public long getDPI() {
        return DPI;
    }

    public void setDPI(long DPI) {
        this.DPI = DPI;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public int getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(int CELULAR) {
        this.CELULAR = CELULAR;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getCOD_PLAN() {
        return COD_PLAN;
    }

    public void setCOD_PLAN(String COD_PLAN) {
        this.COD_PLAN = COD_PLAN;
    }

    public String getCOD_ZONA() {
        return COD_ZONA;
    }

    public void setCOD_ZONA(String COD_ZONA) {
        this.COD_ZONA = COD_ZONA;
    }

    public String getCOD_USER() {
        return COD_USER;
    }

    public void setCOD_USER(String COD_USER) {
        this.COD_USER = COD_USER;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
  
  
  
  
}
