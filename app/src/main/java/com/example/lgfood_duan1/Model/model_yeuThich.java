package com.example.lgfood_duan1.Model;

public class model_yeuThich {
    private String
    idSanPham,
    idYeuThich;
    public model_yeuThich(){

    }
    public model_yeuThich(String idSanPham, String idYeuThich) {
        this.idSanPham = idSanPham;
        this.idYeuThich = idYeuThich;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdYeuThich() {
        return idYeuThich;
    }

    public void setIdYeuThich(String idYeuThich) {
        this.idYeuThich = idYeuThich;
    }
}
