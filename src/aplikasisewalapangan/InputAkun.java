/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.util.ArrayList;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class InputAkun {
    ArrayList<Akun> listAkun;
    
    public InputAkun(){
        listAkun = new ArrayList();
    }
    
    public void insertData(String username, String password){
        Akun an = new Akun(username, password);
        listAkun.add(an);
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
}
