package com.MyBatis.mssql.service;

import com.MyBatis.mssql.model.SinhVien;

import java.util.List;

public interface SinhVienService {
    List<SinhVien> findAll();
    SinhVien FindOneById();
    void Save(SinhVien sv);
}
