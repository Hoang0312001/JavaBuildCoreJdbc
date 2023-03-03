package com.MyBatis.mssql.model;

public class SinhVienDTO {
    private String MaSV;
    private String HoTen;
    private String MaLop;

    public SinhVienDTO() {

    }

    public SinhVienDTO(String maSV, String hoTen, String maLop) {
        MaSV = maSV;
        HoTen = hoTen;
        MaLop = maLop;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }
}
