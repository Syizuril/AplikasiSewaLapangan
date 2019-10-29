/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class InputAkun {
    ArrayList<Akun> listAkun;
    
    public InputAkun(){
        listAkun = new ArrayList();
    }
    
    public void insertData(String username, String password, String kdAkun, String statusAkun, String noTelp, String alamat, Date daftar, int index){
        Akun an = new Akun(username, password, kdAkun, statusAkun, noTelp, alamat, daftar, index);
        listAkun.add(an);
    }

    public void setListAkun(ArrayList<Akun> listAkun) {
        this.listAkun = listAkun;
    }
    
    public ArrayList<Akun> getAll(){
        return listAkun;
    } 
    
    public int getSize(){
        return listAkun.size();
    }
    
    public Akun get(int i){
        return listAkun.get(i);
    }
    
    public void deleteData(int index){
        listAkun.remove(index);
    }
}
