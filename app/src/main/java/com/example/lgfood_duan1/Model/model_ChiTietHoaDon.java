package com.example.lgfood_duan1.Model;

public class model_ChiTietHoaDon {
    //    chi tiết hóa đơn:  id sản phẩm, lấy giá sản phẩm, số lượng sản phẩm, id chi tiết hóa đơn
    private String
    idSanPham,
    idChiTietHoaDon;

    private int
    giaSanPham,
    soLuongSanPham;

    public model_ChiTietHoaDon(String idSanPham, String idChiTietHoaDon, int giaSanPham, int soLuongSanPham) {
        this.idSanPham = idSanPham;
        this.idChiTietHoaDon = idChiTietHoaDon;
        this.giaSanPham = giaSanPham;
        this.soLuongSanPham = soLuongSanPham;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdChiTietHoaDon() {
        return idChiTietHoaDon;
    }

    public void setIdChiTietHoaDon(String idChiTietHoaDon) {
        this.idChiTietHoaDon = idChiTietHoaDon;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }
}
