/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author joans
 */
public class User {
      int ID;
    String Cod_user;
    String User_US;
    String Rol_Us;
    String Psw_Us;
    String Nomb_Us;
    String Ape_Us;
    String Corr_Us;
    String Esdado;

    public User() {
    }

    public User(int ID, String Cod_user, String User_US, String Rol_Us, String Psw_Us, String Nomb_Us, String Ape_Us, String Corr_Us, String Esdado) {
        this.ID = ID;
        this.Cod_user = Cod_user;
        this.User_US = User_US;
        this.Rol_Us = Rol_Us;
        this.Psw_Us = Psw_Us;
        this.Nomb_Us = Nomb_Us;
        this.Ape_Us = Ape_Us;
        this.Corr_Us = Corr_Us;
        this.Esdado = Esdado;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCod_user() {
        return Cod_user;
    }

    public void setCod_user(String Cod_user) {
        this.Cod_user = Cod_user;
    }

    public String getUser_US() {
        return User_US;
    }

    public void setUser_US(String User_US) {
        this.User_US = User_US;
    }

    public String getRol_Us() {
        return Rol_Us;
    }

    public void setRol_Us(String Rol_Us) {
        this.Rol_Us = Rol_Us;
    }

    public String getPsw_Us() {
        return Psw_Us;
    }

    public void setPsw_Us(String Psw_Us) {
        this.Psw_Us = Psw_Us;
    }

    public String getNomb_Us() {
        return Nomb_Us;
    }

    public void setNomb_Us(String Nomb_Us) {
        this.Nomb_Us = Nomb_Us;
    }

    public String getApe_Us() {
        return Ape_Us;
    }

    public void setApe_Us(String Ape_Us) {
        this.Ape_Us = Ape_Us;
    }

    public String getCorr_Us() {
        return Corr_Us;
    }

    public void setCorr_Us(String Corr_Us) {
        this.Corr_Us = Corr_Us;
    }

    public String getEsdado() {
        return Esdado;
    }

    public void setEsdado(String Esdado) {
        this.Esdado = Esdado;
    }

    
}
