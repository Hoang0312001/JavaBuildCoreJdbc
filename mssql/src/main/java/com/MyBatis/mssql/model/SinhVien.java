package com.MyBatis.mssql.model;

import lombok.AllArgsConstructor;
import lombok.Data;


public class SinhVien {
    private  String MaSV;

    public SinhVien() {
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }
}
