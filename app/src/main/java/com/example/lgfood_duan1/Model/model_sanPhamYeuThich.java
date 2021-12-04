package com.example.lgfood_duan1.Model;

public class model_sanPhamYeuThich {
    String idSanPham;
    String idYeuThich;

    public model_sanPhamYeuThich() {
    }

    public model_sanPhamYeuThich(String idSanPham, String idYeuThich) {
        this.idSanPham = idSanPham;
        this.idYeuThich = idYeuThich;
    }


    public String getIdYeuThich() {
        return idYeuThich;
    }

    public void setIdYeuThich(String idYeuThich) {
        this.idYeuThich = idYeuThich;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }
}
