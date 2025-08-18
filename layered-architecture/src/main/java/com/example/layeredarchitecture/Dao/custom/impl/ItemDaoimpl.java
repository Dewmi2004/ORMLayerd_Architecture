package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.Dao.custom.ItemDao;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDaoimpl implements ItemDao {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.exicute("SELECT * FROM Item");
        ArrayList<Item> entity = new ArrayList<>();
        while (rst.next()){
            Item item = new Item(rst.getString("code"),rst.getString("description"),rst.getBigDecimal("unitPrice"),rst.getInt("qtyOnHand"));
            entity.add(item);
        }
        return entity;
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.exicute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();
    }
    @Override
    public boolean update(Item entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }
   @Override
    public boolean Delete(String item) throws ClassNotFoundException, SQLException {
      return SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?",item);
    }
    @Override
    public boolean Save(Item entity) throws ClassNotFoundException, SQLException {
       return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }
    @Override
    public String Genaratenewid( ) throws SQLException, ClassNotFoundException {
      ResultSet rst =SQLUtil.exicute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public Item search(String newItemCode) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.exicute("SELECT * FROM Item WHERE code=?",newItemCode);
       rst.next();
        Item item = new Item(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
}
