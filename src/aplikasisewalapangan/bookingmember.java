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
public class bookingmember {
    
    private String Tanggal, Kdoperator, Namaoperator, Member, Mulai, Selesai;

    public bookingmember(String tanggal, String kdoperator, String namaoperator, String member){
        this.Tanggal = Tanggal;
        this.Kdoperator = Kdoperator;
        this.Namaoperator = Namaoperator;
        this.Member = Member;
        this.Mulai = Mulai;
        this.Selesai = Selesai;
    }

    bookingmember(String Tanggal, String Kdoperator, String Namaoperator, String Member, String Mulai, String Selesai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getTanggal(){
        return Tanggal; 
    }
    
    public String getKdoperator(){
        return Kdoperator; 
    }
    
    public String getNamaoperator(){
        return Namaoperator; 
    }
    
    public String getMember(){
        return Member; 
    }
    
    public String getMulai(){
        return Mulai; 
    }
    
    public String getSelesai(){
        return Selesai; 
    }

}
