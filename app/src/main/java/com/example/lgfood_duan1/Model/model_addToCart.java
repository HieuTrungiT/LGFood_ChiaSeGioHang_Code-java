package com.example.lgfood_duan1.Model;

public class model_addToCart {
    private String
            idSp,
    anhSp,
    tenSp,
    xuatXu,
    giaSp,
    soLuong;

    public model_addToCart(){

    }

    public model_addToCart(String idSp, String anhSp, String tenSp, String xuatXu, String giaSp, String soLuong) {
        this.idSp = idSp;
        this.anhSp = anhSp;
        this.tenSp = tenSp;
        this.xuatXu = xuatXu;
        this.giaSp = giaSp;
        this.soLuong = soLuong;
    }

    public String getIdSp() {
        return idSp;
    }

    public void setIdSp(String idSp) {
        this.idSp = idSp;
    }

    public String getAnhSp() {
        return anhSp;
    }

    public void setAnhSp(String anhSp) {
        this.anhSp = anhSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(String giaSp) {
        this.giaSp = giaSp;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
