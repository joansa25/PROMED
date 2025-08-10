/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Zona {
    int id;
    String COD_ZON;
    String COD_NOMB;
    String COD_DESC;
    String ESTADO;

    public Zona() {
    }

    public Zona(int id, String COD_ZON, String COD_NOMB, String COD_DESC, String ESTADO) {
        this.id = id;
        this.COD_ZON = COD_ZON;
        this.COD_NOMB = COD_NOMB;
        this.COD_DESC = COD_DESC;
        this.ESTADO = ESTADO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOD_ZON() {
        return COD_ZON;
    }

    public void setCOD_ZON(String COD_ZON) {
        this.COD_ZON = COD_ZON;
    }

    public String getCOD_NOMB() {
        return COD_NOMB;
    }

    public void setCOD_NOMB(String COD_NOMB) {
        this.COD_NOMB = COD_NOMB;
    }

    public String getCOD_DESC() {
        return COD_DESC;
    }

    public void setCOD_DESC(String COD_DESC) {
        this.COD_DESC = COD_DESC;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
    
}
