package com.example.lgfood_duan1.Model;

public class model_HoaDon {
   // hóa đơn:  id hóa đơn"Stirng ", id chi tiết hóa đơn,  tình trạng hóa đơn, id use, ngày tạo
   private String
           idHoaDon,
           dchiTietHoaDon,
           ngayTaoHoaDon,
           idTinhTrangHoaDo,
           idKhachHang;

   public model_HoaDon(String idHoaDon, String dchiTietHoaDon, String ngayTaoHoaDon, String idTinhTrangHoaDo, String idKhachHang) {
      this.idHoaDon = idHoaDon;
      this.dchiTietHoaDon = dchiTietHoaDon;
      this.ngayTaoHoaDon = ngayTaoHoaDon;
      this.idTinhTrangHoaDo = idTinhTrangHoaDo;
      this.idKhachHang = idKhachHang;
   }

   public String getIdHoaDon() {
      return idHoaDon;
   }

   public void setIdHoaDon(String idHoaDon) {
      this.idHoaDon = idHoaDon;
   }

   public String getDchiTietHoaDon() {
      return dchiTietHoaDon;
   }

   public void setDchiTietHoaDon(String dchiTietHoaDon) {
      this.dchiTietHoaDon = dchiTietHoaDon;
   }

   public String getNgayTaoHoaDon() {
      return ngayTaoHoaDon;
   }

   public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
      this.ngayTaoHoaDon = ngayTaoHoaDon;
   }

   public String getIdTinhTrangHoaDo() {
      return idTinhTrangHoaDo;
   }

   public void setIdTinhTrangHoaDo(String idTinhTrangHoaDo) {
      this.idTinhTrangHoaDo = idTinhTrangHoaDo;
   }

   public String getIdKhachHang() {
      return idKhachHang;
   }

   public void setIdKhachHang(String idKhachHang) {
      this.idKhachHang = idKhachHang;
   }
}
