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
    
    int ID;
    String COD_CLI;
    String DPI_CLI;
    String NOM_CLI;
    String APE_CLI;
    int    TEL_CLI;
    String COR_CLI;
    String DIR_CLI;
    int    NIT_CLI;
    String COD_PLAN;
    String COD_ZON;
    String COD_USER;

    public Cliente() {
    }

    public Cliente(int ID, String COD_CLI, String DPI_CLI, String NOM_CLI, String APE_CLI, int TEL_CLI, String COR_CLI, String DIR_CLI, int NIT_CLI, String COD_PLAN, String COD_ZON, String COD_USER) {
        this.ID = ID;
        this.COD_CLI = COD_CLI;
        this.DPI_CLI = DPI_CLI;
        this.NOM_CLI = NOM_CLI;
        this.APE_CLI = APE_CLI;
        this.TEL_CLI = TEL_CLI;
        this.COR_CLI = COR_CLI;
        this.DIR_CLI = DIR_CLI;
        this.NIT_CLI = NIT_CLI;
        this.COD_PLAN = COD_PLAN;
        this.COD_ZON = COD_ZON;
        this.COD_USER = COD_USER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOD_CLI() {
        return COD_CLI;
    }

    public void setCOD_CLI(String COD_CLI) {
        this.COD_CLI = COD_CLI;
    }

    public String getDPI_CLI() {
        return DPI_CLI;
    }

    public void setDPI_CLI(String DPI_CLI) {
        this.DPI_CLI = DPI_CLI;
    }

    public String getNOM_CLI() {
        return NOM_CLI;
    }

    public void setNOM_CLI(String NOM_CLI) {
        this.NOM_CLI = NOM_CLI;
    }

    public String getAPE_CLI() {
        return APE_CLI;
    }

    public void setAPE_CLI(String APE_CLI) {
        this.APE_CLI = APE_CLI;
    }

    public int getTEL_CLI() {
        return TEL_CLI;
    }

    public void setTEL_CLI(int TEL_CLI) {
        this.TEL_CLI = TEL_CLI;
    }

    public String getCOR_CLI() {
        return COR_CLI;
    }

    public void setCOR_CLI(String COR_CLI) {
        this.COR_CLI = COR_CLI;
    }

    public String getDIR_CLI() {
        return DIR_CLI;
    }

    public void setDIR_CLI(String DIR_CLI) {
        this.DIR_CLI = DIR_CLI;
    }

    public int getNIT_CLI() {
        return NIT_CLI;
    }

    public void setNIT_CLI(int NIT_CLI) {
        this.NIT_CLI = NIT_CLI;
    }

    public String getCOD_PLAN() {
        return COD_PLAN;
    }

    public void setCOD_PLAN(String COD_PLAN) {
        this.COD_PLAN = COD_PLAN;
    }

    public String getCOD_ZON() {
        return COD_ZON;
    }

    public void setCOD_ZON(String COD_ZON) {
        this.COD_ZON = COD_ZON;
    }

    public String getCOD_USER() {
        return COD_USER;
    }

    public void setCOD_USER(String COD_USER) {
        this.COD_USER = COD_USER;
    }
    
    
    
}
