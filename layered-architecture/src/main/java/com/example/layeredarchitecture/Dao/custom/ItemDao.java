package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.Dao.CrudDao;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item> {

}
