package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.custom.QuaryDao;
import com.example.layeredarchitecture.model.CustomDTO;

import java.util.List;

public class QuaryDaoImpl implements QuaryDao {

    @Override
    public List<CustomDTO> getAllCustomerByOrderCount() {
        //DB
        return null;
    }

    @Override
    public List<CustomDTO> getAllItemByOrderCount() {
        return List.of();
    }
}
