package com.example.owl.rapot;

/**
 * Created by OWL on 04/05/2016.
 */
public class Rapot {
    private String mapel,nilai;

    public Rapot(){

    }

    public Rapot(String mapel, String nilai){
        this.mapel = mapel;
        this.nilai = nilai;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
