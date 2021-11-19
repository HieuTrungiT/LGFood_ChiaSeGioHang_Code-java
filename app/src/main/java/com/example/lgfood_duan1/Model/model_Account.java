package com.example.lgfood_duan1.Model;

public class model_Account {

    private String id;
    private String realName;
    private String name;
    private String password;
    private String address;
    private String email;
    private String phoneNumber;
    private String idGioHang;
    private String anhKhachHang;
    public model_Account(){

    }

    public model_Account(String id, String realName, String name, String password, String address, String email, String phoneNumber, String idGioHang, String anhKhachHang) {
        this.id = id;
        this.realName = realName;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idGioHang = idGioHang;
        this.anhKhachHang = anhKhachHang;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
    }

    public String getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(String anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }
}
