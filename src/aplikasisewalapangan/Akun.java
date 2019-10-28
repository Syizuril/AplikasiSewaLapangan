/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class Akun {
    private String username, password, kdAkun, statusAkun;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Akun(String username, String password, String kdAkun, String statusAkun, int index) {
        this.username = username;
        this.password = password;
        this.kdAkun = kdAkun;
        this.statusAkun = statusAkun;
        this.index = index;
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
