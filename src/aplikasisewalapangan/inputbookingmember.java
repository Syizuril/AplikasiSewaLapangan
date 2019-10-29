/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

/**
 *
 * @author user
 */
import java.util.ArrayList;

public class inputbookingmember {
    ArrayList<bookingmember>listbookingmember;
    
    public inputbookingmember(){
        listbookingmember = new ArrayList();
    }
    
    public void insertData(String Tanggal, String Kdoperator, String Namaoperator, String Member, String Mulai, String Selesai){
        bookingmember bookmem = new bookingmember(Tanggal, Kdoperator, Namaoperator, Member, Mulai, Selesai);
        listbookingmember.add(bookmem);
    }
    
    public ArrayList<bookingmember>getALL(){
        return listbookingmember;
    }
    
    public void deleteData(int index){
        listbookingmember.remove(index);
    }
}

