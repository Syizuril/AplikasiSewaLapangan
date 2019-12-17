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
 * @author Syekh Syihabuddin AU (171023), Leomongga Oktaria Sihombing (171123), Ryandi Johannsah P (171191)
 */
public class InputPesan {
    ArrayList<Pesanan> listPesan;
    
    public InputPesan(){
        listPesan = new ArrayList<>();
    }
    
    public void InsertData(String kodeBooking, String namaTim, String noHP, String ketBooking, String kodePegawai, String namaPegawai, int waktuAwal, int waktuAkhir, int lamaWaktu, int hargaBooking, int bayarBooking, int sisaBooking, Date tglBooking, int index){
        Pesanan pn = new Pesanan(kodeBooking,namaTim,noHP,ketBooking,kodePegawai,namaPegawai,waktuAwal,waktuAkhir,lamaWaktu,hargaBooking,bayarBooking,sisaBooking,tglBooking,index);
        listPesan.add(pn);
    }
    
    public void setListPesanan(ArrayList<Pesanan> listPesanan) {
        this.listPesan = listPesanan;
    }
    
    public ArrayList<Pesanan> getAll(){
        return listPesan;
    } 
    
    public int getSize(){
        return listPesan.size();
    }
    
    public Pesanan get(int i){
        return listPesan.get(i);
    }
    
    public void deleteData(int index){
        listPesan.remove(index);
    }
}
