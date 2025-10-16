/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author joans
 */
public class Cotizacion {
int ID;
String COD_CLI;
String NUM_COT;
String ID_TRANS;
String PERIODO;
Date FEC_EMISION;
Date FEC_VENC;
double MONTO;
String ESTADO;
Date FEC_CREACION;

    public Cotizacion() {
    }

    public Cotizacion(int ID, String COD_CLI, String NUM_COT, String ID_TRANS, String PERIODO, Date FEC_EMISION, Date FEC_VENC, double MONTO, String ESTADO, Date FEC_CREACION) {
        this.ID = ID;
        this.COD_CLI = COD_CLI;
        this.NUM_COT = NUM_COT;
        this.ID_TRANS = ID_TRANS;
        this.PERIODO = PERIODO;
        this.FEC_EMISION = FEC_EMISION;
        this.FEC_VENC = FEC_VENC;
        this.MONTO = MONTO;
        this.ESTADO = ESTADO;
        this.FEC_CREACION = FEC_CREACION;
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

    public String getNUM_COT() {
        return NUM_COT;
    }

    public void setNUM_COT(String NUM_COT) {
        this.NUM_COT = NUM_COT;
    }

    public String getID_TRANS() {
        return ID_TRANS;
    }

    public void setID_TRANS(String ID_TRANS) {
        this.ID_TRANS = ID_TRANS;
    }

    public String getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(String PERIODO) {
        this.PERIODO = PERIODO;
    }

    public Date getFEC_EMISION() {
        return FEC_EMISION;
    }

    public void setFEC_EMISION(Date FEC_EMISION) {
        this.FEC_EMISION = FEC_EMISION;
    }

    public Date getFEC_VENC() {
        return FEC_VENC;
    }

    public void setFEC_VENC(Date FEC_VENC) {
        this.FEC_VENC = FEC_VENC;
    }

    public double getMONTO() {
        return MONTO;
    }

    public void setMONTO(double MONTO) {
        this.MONTO = MONTO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public Date getFEC_CREACION() {
        return FEC_CREACION;
    }

    public void setFEC_CREACION(Date FEC_CREACION) {
        this.FEC_CREACION = FEC_CREACION;
    }
    


}
