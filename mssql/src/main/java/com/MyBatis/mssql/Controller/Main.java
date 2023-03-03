package com.MyBatis.mssql.Controller;

import com.MyBatis.mssql.dao.CommonDao;
import com.MyBatis.mssql.model.SinhVien;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommonDao<SinhVien> commonDao = new CommonDao<SinhVien>();
        SinhVien sv = new SinhVien();
        sv.setMaSV("001");
        List<SinhVien> list = (List<SinhVien>) commonDao.executeStorePredure("findSinhVien",sv,Object.class);
        for (SinhVien item : list
             ) {
            System.out.println(item.getMaSV());
        }
    }
}
