/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.util.Date;

/**
 *
 * @author Syekh Syihabuddin AU (171023), Leomongga Oktaria Sihombing (171123), Ryandi Johannsah P (171191)
 */
public class Pesanan extends Akun{
    private String kodeBooking, namaTim, noHP, ketBooking;
    private int waktuAwal, waktuAkhir, lamaWaktu, hargaBooking, bayarBooking, sisaBooking;
    private Date tglBooking;
    private int index;

    public Pesanan(String kodeBooking, String namaTim, String noHP, String ketBooking, String kodePegawai, String namaPegawai, int waktuAwal, int waktuAkhir, int lamaWaktu, int hargaBooking, int bayarBooking, int sisaBooking, Date tglBooking, int index) {
        super(kodePegawai, namaPegawai);
        this.kodeBooking = kodeBooking;
        this.namaTim = namaTim;
        this.noHP = noHP;
        this.ketBooking = ketBooking;
        this.waktuAwal = waktuAwal;
        this.waktuAkhir = waktuAkhir;
        this.lamaWaktu = lamaWaktu;
        this.hargaBooking = hargaBooking;
        this.bayarBooking = bayarBooking;
        this.sisaBooking = sisaBooking;
        this.tglBooking = tglBooking;
        this.index = index;
    }
    
    
    public Pesanan(String waktuAwal, String waktuAkhir){
        switch(waktuAwal){
            case "07.00":
                this.waktuAwal=7;
                break;
            case "08.00":
                this.waktuAwal=8;
                break;
            case "09.00":
                this.waktuAwal=9;
                break;
            case "10.00":
                this.waktuAwal=10;
                break;
            case "11.00":
                this.waktuAwal=11;
                break;
            case "12.00":
                this.waktuAwal=12;
                break;
            case "13.00":
                this.waktuAwal=13;
                break;
            case "14.00":
                this.waktuAwal=14;
                break;
            case "15.00":
                this.waktuAwal=15;
                break;
            case "16.00":
                this.waktuAwal=16;
                break;
            case "17.00":
                this.waktuAwal=17;
                break;
            case "18.00":
                this.waktuAwal=18;
                break;
            case "19.00":
                this.waktuAwal=19;
                break;
            case "20.00":
                this.waktuAwal=20;
                break;
            case "21.00":
                this.waktuAwal=21;
                break;
            case "22.00":
                this.waktuAwal=22;
                break;
            case "23.00":
                this.waktuAwal=23;
                break;
            case "24.00":
                this.waktuAwal=24;
                break;    
        }
        switch(waktuAkhir){
            case "07.00":
                this.waktuAkhir=7;
                break;
            case "08.00":
                this.waktuAkhir=8;
                break;
            case "09.00":
                this.waktuAkhir=9;
                break;
            case "10.00":
                this.waktuAkhir=10;
                break;
            case "11.00":
                this.waktuAkhir=11;
                break;
            case "12.00":
                this.waktuAkhir=12;
                break;
            case "13.00":
                this.waktuAkhir=13;
                break;
            case "14.00":
                this.waktuAkhir=14;
                break;
            case "15.00":
                this.waktuAkhir=15;
                break;
            case "16.00":
                this.waktuAkhir=16;
                break;
            case "17.00":
                this.waktuAkhir=17;
                break;
            case "18.00":
                this.waktuAkhir=18;
                break;
            case "19.00":
                this.waktuAkhir=19;
                break;
            case "20.00":
                this.waktuAkhir=20;
                break;
            case "21.00":
                this.waktuAkhir=21;
                break;
            case "22.00":
                this.waktuAkhir=22;
                break;
            case "23.00":
                this.waktuAkhir=23;
                break;
            case "24.00":
                this.waktuAkhir=24;
                break;
        }
        lamaWaktu=this.waktuAkhir-this.waktuAwal;
    }

    public Pesanan() {
    }
    

    public String getKodeBooking() {
        return kodeBooking;
    }

    public void setKodeBooking(String kodeBooking) {
        this.kodeBooking = kodeBooking;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getKetBooking() {
        if(sisaBooking==0){
            ketBooking="LUNAS";
        }else{
            ketBooking="BELUM LUNAS";
        }
        return ketBooking;
    }

    public void setKetBooking(String ketBooking) {
        this.ketBooking = ketBooking;
    }

    public int getWaktuAwal() {
        return waktuAwal;
    }

    public void setWaktuAwal(int waktuAwal) {
        this.waktuAwal = waktuAwal;
    }

    public int getWaktuAkhir() {
        return waktuAkhir;
    }

    public void setWaktuAkhir(int waktuAkhir) {
        this.waktuAkhir = waktuAkhir;
    }

    public int getLamaWaktu() {
        return lamaWaktu;
    }

    public void setLamaWaktu(int lamaWaktu) {
        this.lamaWaktu = lamaWaktu;
    }

    public int getHargaBooking() {
        if(waktuAwal>=7&&waktuAkhir<=15){
            hargaBooking=70000*lamaWaktu;
        }else if(waktuAwal>15&&waktuAkhir<=24){
            hargaBooking=90000*lamaWaktu;
        }else{
            int jam = 0;
            int i = waktuAwal;
            for(;i<15;i++){
                jam++;
            }
            int sisa = waktuAkhir-15;
            hargaBooking=(jam*70000)+(sisa*90000);
        }
        return hargaBooking;
    }

    public void setHargaBooking(int hargaBooking) {
        this.hargaBooking = hargaBooking;
    }

    public int getBayarBooking() {
        return bayarBooking;
    }

    public void setBayarBooking(int bayarBooking) {
        this.bayarBooking = bayarBooking;
    }

    public int getSisaBooking() {
        sisaBooking=hargaBooking-bayarBooking;
        return sisaBooking;
    }

    public void setSisaBooking(int sisaBooking) {
        this.sisaBooking = sisaBooking;
    }

    public Date getTglBooking() {
        return tglBooking;
    }

    public void setTglBooking(Date tglBooking) {
        this.tglBooking = tglBooking;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
}
