package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.Dao.custom.OrderDao;
import com.example.layeredarchitecture.entity.Order;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;


public  class OrderDaoimpl implements OrderDao {
    @Override
    public String Genaratenewid() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.exicute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public Order search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",entity.getOrderId(),entity.getOrderDate(),entity.getCustomerId());
    }

    @Override
    public boolean update(Order customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.exicute("SELECT oid FROM `Orders` WHERE oid=?",orderId).next();

    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


}
