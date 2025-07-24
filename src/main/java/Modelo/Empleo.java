/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Empleo {
    
    int ID;
    String COD_EMP;
    String NOMB_EMP;
    Double SALARIO;
    String ESTADO;

    public Empleo() {
    }

    public Empleo(int ID, String COD_EMP, String NOMB_EMP, Double SALARIO, String ESTADO) {
        this.ID = ID;
        this.COD_EMP = COD_EMP;
        this.NOMB_EMP = NOMB_EMP;
        this.SALARIO = SALARIO;
        this.ESTADO = ESTADO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOD_EMP() {
        return COD_EMP;
    }

    public void setCOD_EMP(String COD_EMP) {
        this.COD_EMP = COD_EMP;
    }

    public String getNOMB_EMP() {
        return NOMB_EMP;
    }

    public void setNOMB_EMP(String NOMB_EMP) {
        this.NOMB_EMP = NOMB_EMP;
    }

    public Double getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(Double SALARIO) {
        this.SALARIO = SALARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }


    
}
