package com.example.layeredarchitecture.BO.custom.impl;

import com.example.layeredarchitecture.BO.SuperBO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    public boolean SaveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean DeleteItem( String id) throws SQLException, ClassNotFoundException;
    public boolean UpdateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean ExistItem(String id) throws SQLException, ClassNotFoundException;
    public String GetNewIdItem() throws SQLException, ClassNotFoundException;

}
