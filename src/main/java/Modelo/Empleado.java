    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class Empleado {
    int id;
    String COC_EMPD ;
    int DPI ;
    int N_IGSS;
    int NIT;
    String NOMBRES;
    String APELLIDOS;
    String COD_EMP;
    String COD_EMPSA;
    int CELULAR ;
    String Correo;
    String Estado;
    

    public Empleado() {
    }

    public Empleado(int id, String COC_EMPD, int DPI, int N_IGSS, int NIT, String NOMBRES, String APELLIDOS, String COD_EMP, String COD_EMPSA, int CELULAR, String Correo, String Estado) {
        this.id = id;
        this.COC_EMPD = COC_EMPD;
        this.DPI = DPI;
        this.N_IGSS = N_IGSS;
        this.NIT = NIT;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.COD_EMP = COD_EMP;
        this.COD_EMPSA = COD_EMPSA;
        this.CELULAR = CELULAR;
        this.Correo = Correo;
        this.Estado = Estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOC_EMPD() {
        return COC_EMPD;
    }

    public void setCOC_EMPD(String COC_EMPD) {
        this.COC_EMPD = COC_EMPD;
    }

    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public int getN_IGSS() {
        return N_IGSS;
    }

    public void setN_IGSS(int N_IGSS) {
        this.N_IGSS = N_IGSS;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
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

    public String getCOD_EMP() {
        return COD_EMP;
    }

    public void setCOD_EMP(String COD_EMP) {
        this.COD_EMP = COD_EMP;
    }

    public String getCOD_EMPSA() {
        return COD_EMPSA;
    }

    public void setCOD_EMPSA(String COD_EMPSA) {
        this.COD_EMPSA = COD_EMPSA;
    }

    public int getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(int CELULAR) {
        this.CELULAR = CELULAR;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

   
  
    
}
