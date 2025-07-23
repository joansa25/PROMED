/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Empresa {
    int ID ;
    String COD_EMPSA;
    String NOMB_EMPSA;
    String DIRE_EMPSA;
    int NIT;
    String Estado;

    public Empresa() {
    }

    public Empresa(int ID, String COD_EMPSA, String NOMB_EMPSA, String DIRE_EMPSA, int NIT, String Estado) {
        this.ID = ID;
        this.COD_EMPSA = COD_EMPSA;
        this.NOMB_EMPSA = NOMB_EMPSA;
        this.DIRE_EMPSA = DIRE_EMPSA;
        this.NIT = NIT;
        this.Estado = Estado;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOD_EMPSA() {
        return COD_EMPSA;
    }

    public void setCOD_EMPSA(String COD_EMPSA) {
        this.COD_EMPSA = COD_EMPSA;
    }

    public String getNOMB_EMPSA() {
        return NOMB_EMPSA;
    }

    public void setNOMB_EMPSA(String NOMB_EMPSA) {
        this.NOMB_EMPSA = NOMB_EMPSA;
    }

    public String getDIRE_EMPSA() {
        return DIRE_EMPSA;
    }

    public void setDIRE_EMPSA(String DIRE_EMPSA) {
        this.DIRE_EMPSA = DIRE_EMPSA;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

  
    
}
