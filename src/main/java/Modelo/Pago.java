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
public class Pago {
    int ID;
    String NUM_COT;
    String ID_TRANS;
    Date   FEC_PAG;
    double MON_PAG;
    String MET_PAG;
    String NUM_REF;
    String URL_COMP;
    String REGISTRADO_POR;
    Date FEC_CREACION;

    public Pago() {
    }

    public Pago(int ID, String NUM_COT, String ID_TRANS, Date FEC_PAG, double MON_PAG, String MET_PAG, String NUM_REF, String URL_COMP, String REGISTRADO_POR, Date FEC_CREACION) {
        this.ID = ID;
        this.NUM_COT = NUM_COT;
        this.ID_TRANS = ID_TRANS;
        this.FEC_PAG = FEC_PAG;
        this.MON_PAG = MON_PAG;
        this.MET_PAG = MET_PAG;
        this.NUM_REF = NUM_REF;
        this.URL_COMP = URL_COMP;
        this.REGISTRADO_POR = REGISTRADO_POR;
        this.FEC_CREACION = FEC_CREACION;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Date getFEC_PAG() {
        return FEC_PAG;
    }

    public void setFEC_PAG(Date FEC_PAG) {
        this.FEC_PAG = FEC_PAG;
    }

    public double getMON_PAG() {
        return MON_PAG;
    }

    public void setMON_PAG(double MON_PAG) {
        this.MON_PAG = MON_PAG;
    }

    public String getMET_PAG() {
        return MET_PAG;
    }

    public void setMET_PAG(String MET_PAG) {
        this.MET_PAG = MET_PAG;
    }

    public String getNUM_REF() {
        return NUM_REF;
    }

    public void setNUM_REF(String NUM_REF) {
        this.NUM_REF = NUM_REF;
    }

    public String getURL_COMP() {
        return URL_COMP;
    }

    public void setURL_COMP(String URL_COMP) {
        this.URL_COMP = URL_COMP;
    }

    public String getREGISTRADO_POR() {
        return REGISTRADO_POR;
    }

    public void setREGISTRADO_POR(String REGISTRADO_POR) {
        this.REGISTRADO_POR = REGISTRADO_POR;
    }

    public Date getFEC_CREACION() {
        return FEC_CREACION;
    }

    public void setFEC_CREACION(Date FEC_CREACION) {
        this.FEC_CREACION = FEC_CREACION;
    }
    
    
    
    
}
