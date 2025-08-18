package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.Dao.SuperDao;
import com.example.layeredarchitecture.model.CustomDTO;

import java.util.List;

public interface QuaryDao extends SuperDao {
    List<CustomDTO> getAllCustomerByOrderCount();
    List<CustomDTO> getAllItemByOrderCount();
}
