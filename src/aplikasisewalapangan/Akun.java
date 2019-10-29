/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.util.Date;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class Akun {
    private String username, password, kdAkun, statusAkun, noTelp, alamat;
    private Date daftar;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public Akun(String username, String password, String kdAkun, String statusAkun, String noTelp, String alamat, Date daftar, int index) {
        this.username = username;
        this.password = password;
        this.kdAkun = kdAkun;
        this.statusAkun = statusAkun;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.daftar = daftar;
        this.index = index;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getDaftar() {
        return daftar;
    }

    public void setDaftar(Date daftar) {
        this.daftar = daftar;
    }
    
    public Akun(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKdAkun() {
        return kdAkun;
    }

    public void setKdAkun(String kdAkun) {
        this.kdAkun = kdAkun;
    }

    public String getStatusAkun() {
        return statusAkun;
    }

    public void setStatusAkun(String statusAkun) {
        this.statusAkun = statusAkun;
    }
    
    
}
