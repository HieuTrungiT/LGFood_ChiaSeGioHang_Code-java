package com.example.lgfood_duan1.Model;

public class model_viTri {

    private String idVitri;

    private String Vitri;
    private double Latitude;
    private double Longitude;
    private boolean tinhTrang;
    private String NgayTao;
    private String key;

    public model_viTri() {
    }

    public model_viTri(String idVitri, String vitri, double latitude, double longitude, boolean tinhTrang, String ngayTao, String key) {
        this.idVitri = idVitri;
        Vitri = vitri;
        Latitude = latitude;
        Longitude = longitude;
        this.tinhTrang = tinhTrang;
        NgayTao = ngayTao;
        this.key = key;
    }

    public String getIdVitri() {
        return idVitri;
    }

    public void setIdVitri(String idVitri) {
        this.idVitri = idVitri;
    }

    public String getVitri() {
        return Vitri;
    }

    public void setVitri(String vitri) {
        Vitri = vitri;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}