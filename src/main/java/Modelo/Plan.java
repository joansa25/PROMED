/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Plan {
    int id;
    String COD_PLAN;
    String COD_NOMB;
    String COD_DESC;
    String Estado;

    public Plan() {
    }

    public Plan(int id, String COD_PLAN, String COD_NOMB, String COD_DESC, String Estado) {
        this.id = id;
        this.COD_PLAN = COD_PLAN;
        this.COD_NOMB = COD_NOMB;
        this.COD_DESC = COD_DESC;
        this.Estado = Estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOD_PLAN() {
        return COD_PLAN;
    }

    public void setCOD_PLAN(String COD_PLAN) {
        this.COD_PLAN = COD_PLAN;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
