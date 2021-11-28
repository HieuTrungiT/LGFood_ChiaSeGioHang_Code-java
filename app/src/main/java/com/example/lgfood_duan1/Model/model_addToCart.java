package com.example.lgfood_duan1.Model;

public class model_addToCart {
    private String
    anhSp,
    tenSp,
    xuatXu,
    giaSp,
    soLuong;

    public model_addToCart(){

    }

    public model_addToCart(String anhSp, String tenSp, String xuatXu, String giaSp, String soLuong) {
        this.anhSp = anhSp;
        this.tenSp = tenSp;
        this.xuatXu = xuatXu;
        this.giaSp = giaSp;
        this.soLuong = soLuong;
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
